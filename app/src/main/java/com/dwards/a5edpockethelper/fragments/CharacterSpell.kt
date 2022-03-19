package com.dwards.a5edpockethelper.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.adapters.SpellListAdapter
import com.dwards.a5edpockethelper.adapters.WeaponListAdapter
import com.dwards.a5edpockethelper.databinding.FragmentCharacterSpellBinding
import com.dwards.a5edpockethelper.databinding.FragmentCharacterWeaponBinding
import com.dwards.a5edpockethelper.dialogs.CharacterListDialog
import com.dwards.a5edpockethelper.interfaces.RecyclerViewClickListener

class CharacterSpell : Fragment(), RecyclerViewClickListener {
    private val TAG = "MainFragment"

    private var _binding: FragmentCharacterSpellBinding? = null
    private val binding get() = _binding!!
    private lateinit var spellAdapter: SpellListAdapter
    private lateinit var spellList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.characterList -> {
                CharacterListDialog().show(parentFragmentManager, "ProficiencySettingsDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCharacterSpellBinding.inflate(inflater, container, false)
        spellList = binding.WeaponRecycler
        //создание вью-модел и обсервера

        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        //viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
        //    it?.let {
                //refreshChar(it)
        //    }
        //})

        viewModel.getAllSpells().observe(viewLifecycleOwner, Observer {
            it?.let {
                spellAdapter = SpellListAdapter(it, this)
                spellList.apply {
                    layoutManager = LinearLayoutManager(activity);
                    adapter = spellAdapter
                    //addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                }
                //refreshChar(it)
            }
        })

        binding.spellTopNavBlock.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && binding.spellTopNavBlock.checkBox2.isChecked) {
                viewModel.showFavoritePreparedSpells()
            }
            else if (isChecked) {
                viewModel.showFavoriteSpells()
            }
            else {
                viewModel.showAllSpells()
            }
        }

        binding.spellTopNavBlock.checkBox2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && binding.spellTopNavBlock.checkBox.isChecked) {
                viewModel.showFavoritePreparedSpells()
            }
            else if (isChecked) {
                viewModel.showPreparedSpells()
            }
            else {
                viewModel.showAllSpells()
            }
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecyclerViewItemClickListener(view: View, id: Int) {
        TODO("Not yet implemented")
    }

}


