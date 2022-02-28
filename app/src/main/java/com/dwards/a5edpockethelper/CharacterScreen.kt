package com.dwards.a5edpockethelper

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.databinding.FragmentCharacterScreenBinding
import com.dwards.a5edpockethelper.model.Character


class CharacterScreen : Fragment() {
    private val TAG = "MainFragment"

    private var _binding: FragmentCharacterScreenBinding? = null
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
                var CharacterListDialog: CharacterList = CharacterList()
                CharacterListDialog.show(parentFragmentManager, "ProficiencySettingsDialog")
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




        _binding = FragmentCharacterScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        // редактирование характеристик
        binding.StatsLayout.setOnLongClickListener {

            val statsSettingsDialog: CharacteristicSettingsDialog = CharacteristicSettingsDialog()

            statsSettingsDialog.show(parentFragmentManager, "CharacteristicSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование бонуса мастерства
        binding.ProficiencyLayout.setOnLongClickListener {

            val proficiencySettingsDialog: ProficiencySettingsDialog = ProficiencySettingsDialog()
            proficiencySettingsDialog.show(parentFragmentManager, "ProficiencySettingsDialog")
            return@setOnLongClickListener true
        }

        return view
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    private fun refreshChar(character: Character) {
        binding.StrengthScoreValue.text = "${character.strength}"
        binding.DexterityScoreValue.text = "${character.dexterity}"
        binding.ConstitutionScoreValue.text = "${character.constitution}"
        binding.IntelligenceScoreValue.text = "${character.intelligence}"
        binding.WisdomScoreValue.text = "${character.wisdom}"
        binding.CharismaScoreValue.text = "${character.charisma}"
        binding.ProficiencyValue.text = ("+" + character.proficiency.toString())
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


