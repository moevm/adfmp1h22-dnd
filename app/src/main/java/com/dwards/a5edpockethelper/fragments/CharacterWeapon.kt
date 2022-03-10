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
import com.dwards.a5edpockethelper.adapters.WeaponListAdapter
import com.dwards.a5edpockethelper.databinding.FragmentCharacterWeaponBinding
import com.dwards.a5edpockethelper.dialogs.CharacterListDialog
import com.dwards.a5edpockethelper.interfaces.RecyclerViewClickListener


class CharacterWeapon : Fragment(), RecyclerViewClickListener {
    private val TAG = "MainFragment"

    private var _binding: FragmentCharacterWeaponBinding? = null
    private val binding get() = _binding!!
    private lateinit var weaponAdapter: WeaponListAdapter
    private lateinit var weaponList: RecyclerView

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
    ): View {
        _binding = FragmentCharacterWeaponBinding.inflate(inflater, container, false)
        //создание вью-модел и обсервера
        weaponList = binding.WeaponRecycler
        val viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]

        viewModel.getAllWeapons().observe(viewLifecycleOwner, Observer {
            it?.let {
                weaponAdapter = WeaponListAdapter(it, this)
                weaponList.apply {
                    layoutManager = LinearLayoutManager(activity);
                    adapter = weaponAdapter
                    //addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                }
                //refreshChar(it)
            }
        })

        binding.StatLayout.setOnClickListener {
            viewModel.addWeapon()
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



