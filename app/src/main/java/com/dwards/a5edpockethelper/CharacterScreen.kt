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

            val StatsSettingsdialog: CharacteristicSettingsDialog = CharacteristicSettingsDialog()

            StatsSettingsdialog.show(parentFragmentManager, "CharacteristicSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование бонуса мастерства
        binding.ProficiencyLayout.setOnLongClickListener {

            val ProficiencySettingsDialog: ProficiencySettingsDialog = ProficiencySettingsDialog()
            ProficiencySettingsDialog.show(parentFragmentManager, "ProficiencySettingsDialog")
            return@setOnLongClickListener true
        }

        return view
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    private fun refreshChar(character: Character) {
        binding.StrengthScoreValue.setText("${character.strength}")
        binding.DexterityScoreValue.setText("${character.dexterity}")
        binding.ConstitutionScoreValue.setText("${character.constitution}")
        binding.IntelligenceScoreValue.setText("${character.intelligence}")
        binding.WisdomScoreValue.setText("${character.wisdom}")
        binding.CharismaScoreValue.setText("${character.charisma}")
    }

    /*
    fun change(statMap: HashMap<String, Int>) {
        val strength = statMap.get("Strength")
        val dexterity = statMap.get("Dexterity")
        val constitution = statMap.get("Constitution")
        val intelligence = statMap.get("Intelligence")
        val wisdom = statMap.get("Wisdom")
        val charisma = statMap.get("Charisma")
        binding.StrengthScoreValue.setText("${strength}")
        binding.DexterityScoreValue.setText("${dexterity}")
        binding.ConstitutionScoreValue.setText("${constitution}")
        binding.IntelligenceScoreValue.setText("${intelligence}")
        binding.WisdomScoreValue.setText("${wisdom}")
        binding.CharismaScoreValue.setText("${charisma}")
    }
    */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


