package com.dwards.a5edpockethelper


import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.databinding.FragmentCharacterSkillsBinding
import com.dwards.a5edpockethelper.model.Character


class CharacterSkills : Fragment() {
    private val TAG = "MainFragment"

    private var _binding: FragmentCharacterSkillsBinding? = null
    private val binding get() = _binding!!


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
                var characterListDialog: CharacterList = CharacterList()
                characterListDialog.show(parentFragmentManager, "ProficiencySettingsDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //создание вью-модел и обсервера

        val viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })




        _binding = FragmentCharacterSkillsBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




    private fun refreshChar(character: Character) {
        val viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


