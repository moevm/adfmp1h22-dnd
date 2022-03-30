package com.dwards.a5edpockethelper.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.SpellListNames
import com.dwards.a5edpockethelper.adapters.SpellListAdapter
import com.dwards.a5edpockethelper.databinding.FragmentCharacterSpellBinding
import com.dwards.a5edpockethelper.dialogs.CharacterListDialog
import com.dwards.a5edpockethelper.dialogs.SpellEditDialog
import com.dwards.a5edpockethelper.dialogs.SpellFilterDialog
import com.dwards.a5edpockethelper.dialogs.SpellInfoDialog
import com.dwards.a5edpockethelper.gdrive.DriveServiceHelper
import com.dwards.a5edpockethelper.gdrive.DriveServiceHelper.getGoogleDriveService
import com.dwards.a5edpockethelper.interfaces.RecyclerViewClickListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.drive.Drive.SCOPE_FILE

class CharacterSpell : Fragment(), RecyclerViewClickListener {

    private val TAG = "MainActivity"

    private var _binding: FragmentCharacterSpellBinding? = null
    private val binding get() = _binding!!
    private lateinit var spellAdapter: SpellListAdapter
    private lateinit var spellList: RecyclerView
    private lateinit var viewModel: MyViewModel

    private var mDriveServiceHelper: DriveServiceHelper? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        val applicationContext = context?.applicationContext ?: return
        val account =
            GoogleSignIn.getLastSignedInAccount(applicationContext)

        if (account == null) {
            signIn()
        } else {
            mDriveServiceHelper = DriveServiceHelper(
                getGoogleDriveService(
                    applicationContext,
                    account,
                    "appName"
                )
            )
        }
    }

    private fun signIn() {
        mGoogleSignInClient = buildGoogleSignInClient()
        val signInClient = mGoogleSignInClient
        if (signInClient != null) {
            startActivityForResult(signInClient.signInIntent, REQUEST_CODE_SIGN_IN)
        }
    }

    private fun buildGoogleSignInClient(): GoogleSignInClient? {
        val applicationContext = context?.applicationContext ?: return null

        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(SCOPE_FILE)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(
            applicationContext,
            signInOptions
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        inflater.inflate(R.menu.import_export_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.characterList -> {
                CharacterListDialog().show(parentFragmentManager, "ProficiencySettingsDialog")
                true
            }
            R.id.export_data -> {
                // TODO: add file export handling
                true
            }
            R.id.import_data -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        _binding = FragmentCharacterSpellBinding.inflate(inflater, container, false)
        spellList = binding.WeaponRecycler
        //создание вью-модел и обсервера

        viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]

        //viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
        //    it?.let {
        //refreshChar(it)
        //    }
        //})

        viewModel.getAllSpells().observe(viewLifecycleOwner) {
            it?.let {
                spellAdapter = SpellListAdapter(it.filterNotNull(), this)
                spellList.apply {
                    layoutManager = LinearLayoutManager(activity);
                    adapter = spellAdapter
                    //addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                }
                //refreshChar(it)
            }
        }

        binding.spellTopNavBlock.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && binding.spellTopNavBlock.checkBox2.isChecked) {
                viewModel.showSpells(newCurrentSpellListName = SpellListNames.FAVORITE_PREPARED)
            } else if (isChecked) {
                viewModel.showSpells(newCurrentSpellListName = SpellListNames.FAVORITE)
            } else if (binding.spellTopNavBlock.checkBox2.isChecked) {
                viewModel.showSpells(newCurrentSpellListName = SpellListNames.PREPARED)
            } else {
                viewModel.showSpells(newCurrentSpellListName = SpellListNames.ALL)
            }
        }

        binding.spellTopNavBlock.checkBox2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && binding.spellTopNavBlock.checkBox.isChecked) {
                viewModel.showSpells(newCurrentSpellListName = SpellListNames.FAVORITE_PREPARED)
            } else if (isChecked) {
                viewModel.showSpells(newCurrentSpellListName = SpellListNames.PREPARED)
            } else if (binding.spellTopNavBlock.checkBox.isChecked) {
                viewModel.showSpells(newCurrentSpellListName = SpellListNames.FAVORITE)
            } else {
                viewModel.showSpells(newCurrentSpellListName = SpellListNames.ALL)
            }
        }

        binding.spellTopNavBlock.addSpellButton.setOnClickListener {
            viewModel.addEmptySpell()
            Toast.makeText(context, "You created empty spell with HB source", Toast.LENGTH_SHORT)
                .show()
        }

        binding.spellTopNavBlock.filterButton.setOnClickListener {
            val dialog = SpellFilterDialog(viewModel.currentFilter)
            dialog.show(parentFragmentManager, "spellFilterDialog")
        }

        binding.spellTopNavBlock.searchValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.spellTopNavBlock.searchValue.text.toString().isNotEmpty()) {
                    viewModel.showSpells(newFilter = viewModel.currentFilter.copy(name = binding.spellTopNavBlock.searchValue.text.toString()))
                } else if (binding.spellTopNavBlock.searchValue.text.toString().isEmpty()) {
                    viewModel.showSpells(newFilter = viewModel.currentFilter.copy(name = null))
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecyclerViewItemClickListener(view: View, id: Int) {
        //val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        when (view.id) {
            R.id.MainSpellLayout -> {
                viewModel.getSpellById(id)?.let {
                    val dialog = SpellInfoDialog(it)
                    dialog.show(parentFragmentManager, "spellInfoDialog")
                }
            }
            R.id.favoriteIcon -> {
                if (viewModel.isFavoriteSpell(id)) {
                    viewModel.removeFavoriteSpell(id)
                    view.setBackgroundColor(Color.rgb(255, 255, 255))

                    //(view as ImageView).drawableTint()
                    //ImageViewCompat.setImageTintList(view as ImageView, ColorStateList.valueOf(Color.rgb(255,255,255)));

                } else {
                    viewModel.addFavoriteSpell(id)
                    view.setBackgroundColor(Color.rgb(255, 0, 0))
                    //(view as ImageView).setColorFilter(Color.rgb(255,0,0))
                    //(view as ImageView).backgroundTintList = ColorStateList.valueOf(Color.rgb(255,0,0))

                }
            }

            R.id.preparedIcon -> {
                if (viewModel.isPreparedSpell(id)) {
                    viewModel.removePreparedSpell(id)
                    view.setBackgroundColor(Color.rgb(255, 255, 255))
                } else {
                    viewModel.addPreparedSpell(id)
                    view.setBackgroundColor(Color.rgb(255, 0, 0))
                }
            }
        }
    }

    override fun onRecyclerViewItemLongClickListener(view: View, id: Int) {
        when (view.id) {
            R.id.MainSpellLayout -> {
                viewModel.getSpellById(id)?.let {
                    val dialog = SpellEditDialog(it)
                    dialog.show(parentFragmentManager, "spellEditDialog")
                }
            }
        }

    }

    private companion object {
        const val REQUEST_CODE_SIGN_IN = 100
    }
}


