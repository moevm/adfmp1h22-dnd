package com.dwards.a5edpockethelper.fragments

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.SpellListNames
import com.dwards.a5edpockethelper.adapters.ImportItemAdapter
import com.dwards.a5edpockethelper.adapters.SpellListAdapter
import com.dwards.a5edpockethelper.databinding.FragmentCharacterSpellBinding
import com.dwards.a5edpockethelper.dialogs.CharacterListDialog
import com.dwards.a5edpockethelper.dialogs.SpellEditDialog
import com.dwards.a5edpockethelper.dialogs.SpellFilterDialog
import com.dwards.a5edpockethelper.dialogs.SpellInfoDialog
import com.dwards.a5edpockethelper.gdrive.DriveServiceHelper
import com.dwards.a5edpockethelper.gdrive.DriveServiceHelper.*
import com.dwards.a5edpockethelper.interfaces.RecyclerViewClickListener
import com.dwards.a5edpockethelper.utils.ExportSpellsManager
import com.dwards.a5edpockethelper.utils.ImportExportSpellsConverter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.drive.Drive.SCOPE_FILE
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import java.io.File
import java.io.FileInputStream
import java.util.*


@ExperimentalSerializationApi
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
                    "adfmp1h22-dnd"
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        val applicationContext = context?.applicationContext ?: return
        when (requestCode) {
            REQUEST_CODE_SIGN_IN -> if (resultCode == Activity.RESULT_OK && resultData != null) {
                handleSignInResult(resultData, applicationContext)
            }
        }
        super.onActivityResult(requestCode, resultCode, resultData)
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
                if (mDriveServiceHelper == null) {
                    return true
                }
                uploadSpellsToGDrive()
                true
            }
            R.id.import_data -> {
                if (mDriveServiceHelper == null) {
                    return true
                }
                downloadSpellsFromGDrive()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun downloadSpellsFromGDrive() {
        val ctx = context ?: return
        val applicationContext = ctx.applicationContext

        val dialog = Dialog(ctx).apply {
            setContentView(R.layout.custom_import_dialog)
            setTitle(R.string.import_dialog_title)
            setCancelable(true)
        }
        val recycler = dialog.findViewById<RecyclerView>(R.id.recycler)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(ctx).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        val downloadChosenItem = { id: String, name: String ->
            dialog.cancel()
            Toast.makeText(
                applicationContext,
                "Download started. Please wait.",
                Toast.LENGTH_SHORT
            ).show()

            mDriveServiceHelper!!.downloadFile(
                File(
                    applicationContext.filesDir, name
                ), id
            )
                .addOnSuccessListener {
                    // example: /data/user/0/com.dwards.a5edpockethelper/files/2022-3-30-21-19-10_spells_backup.json
                    val filesDir = applicationContext.filesDir
                    val backupAsString = Scanner(File(filesDir, name)).nextLine()
                    val backupList = ImportExportSpellsConverter.covertSpellsJsonStringToList(backupAsString)
                    spellAdapter.spellArrayList = backupList
                    spellAdapter.notifyDataSetChanged()  // it's ok in this case
                    Toast.makeText(
                        applicationContext,
                        "Backup was successfully downloaded!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        applicationContext,
                        "Backup was successfully downloaded!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            Unit
        }

        viewModel.viewModelScope.launch {
            val spellEntities = ExportSpellsManager.getSpellsFromDataStore(ctx).first()
            val adapter = ImportItemAdapter(spellEntities, downloadChosenItem)
            recycler.adapter = adapter
        }
            .invokeOnCompletion {
                dialog.show()
            }
    }

    private fun uploadSpellsToGDrive() {
        val ctx = context ?: return
        val applicationContext = ctx.applicationContext

        val filename = ImportExportSpellsConverter.getCurrentTimeBackupName()
        val content =
            ImportExportSpellsConverter.convertSpellsListToJsonString(spellAdapter.spellArrayList)

        // you can provide folder id in case you want to save this file inside some folder.
        // if folder id is null, it will save file to the root
        mDriveServiceHelper?.createTextFile(filename, content, EXPORT_TYPE_JSON, null)
            ?.addOnSuccessListener { googleDriveFileHolder ->
                if (googleDriveFileHolder != null) {
                    Log.d(TAG, "onSuccess: " + Gson().toJson(googleDriveFileHolder))
                    runBlocking {
                        launch {
                            ExportSpellsManager.saveSpellsEntity(
                                ctx,
                                googleDriveFileHolder.id ?: "",
                                filename
                            )
                        }
                    }
                    Toast.makeText(
                        applicationContext,
                        "Backup successfully created!\nFile name: $filename",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            ?.addOnFailureListener { e ->
                Log.d(TAG, "onFailure: " + e.message)
                Toast.makeText(
                    applicationContext,
                    "Error occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
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
                    //view.setBackgroundColor(Color.rgb(255, 255, 255))
                    (view as ImageView).setImageDrawable(
                        ContextCompat.getDrawable(
                            view.context,
                            R.drawable.ic_star
                        )
                    )
                    //(view as ImageView).drawableTint()
                    //ImageViewCompat.setImageTintList(view as ImageView, ColorStateList.valueOf(Color.rgb(255,255,255)));

                } else {
                    viewModel.addFavoriteSpell(id)
                    (view as ImageView).setImageDrawable(
                        ContextCompat.getDrawable(
                            view.context,
                            R.drawable.ic_star_active
                        )
                    )
                    //view.setBackgroundColor(Color.rgb(255, 0, 0))
                    //(view as ImageView).setColorFilter(Color.rgb(255,0,0))
                    //(view as ImageView).backgroundTintList = ColorStateList.valueOf(Color.rgb(255,0,0))

                }
            }

            R.id.preparedIcon -> {
                if (viewModel.isPreparedSpell(id)) {
                    viewModel.removePreparedSpell(id)
                    (view as ImageView).setImageDrawable(
                        ContextCompat.getDrawable(
                            view.context,
                            R.drawable.ic_book
                        )
                    )
                    //view.setBackgroundColor(Color.rgb(255, 255, 255))
                } else {
                    viewModel.addPreparedSpell(id)
                    (view as ImageView).setImageDrawable(
                        ContextCompat.getDrawable(
                            view.context,
                            R.drawable.ic_book_active
                        )
                    )
                    //view.setBackgroundColor(Color.rgb(255, 0, 0))
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

    private fun handleSignInResult(result: Intent, ctx: Context) {
        GoogleSignIn.getSignedInAccountFromIntent(result)
            .addOnSuccessListener { googleSignInAccount ->
                Log.d(TAG, "Signed in as " + googleSignInAccount.email)
                mDriveServiceHelper = DriveServiceHelper(
                    getGoogleDriveService(
                        ctx,
                        googleSignInAccount,
                        "adfmp1h22-dnd"
                    )
                )
                Log.d(TAG, "handleSignInResult: $mDriveServiceHelper")
            }
            .addOnFailureListener { e ->
                val errMsg = "Unable to sign in.\nError: ${e.message}"
                Log.e(TAG, errMsg)
                Toast.makeText(
                    ctx,
                    errMsg,
                    Toast.LENGTH_LONG
                ).show()
            }
    }
}


