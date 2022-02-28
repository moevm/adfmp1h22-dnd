package com.dwards.a5edpockethelper

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.databinding.CharacteristicSettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class CharacteristicSettingsDialog : DialogFragment() {

    private var _binding: CharacteristicSettingsDialogBinding? = null
    private val binding get() = _binding!!

    private val TAG = "MyCustomDialog"



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = CharacteristicSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        val viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        binding.SaveButton.setOnClickListener {

            viewModel.changeCharactersStats(packageStat())
            dismiss()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.65).toInt()
        dialog!!.window?.setLayout(width, height)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun refreshChar(character: Character) {
        binding.StrengthScoreValue.setText("${character.strength}")
        binding.DexterityScoreValue.setText("${character.dexterity}")
        binding.ConstitutionScoreValue.setText("${character.constitution}")
        binding.IntelligenceScoreValue.setText("${character.intelligence}")
        binding.WisdomScoreValue.setText("${character.wisdom}")
        binding.CharismaScoreValue.setText("${character.charisma}")

        binding.StrengthSaveMiscValue.setText("${character.strengthSaveMisc}")
        binding.DexteritySaveMiscValue.setText("${character.dexteritySaveMisc}")
        binding.ConstitutionSaveMiscValue.setText("${character.constitutionSaveMisc}")
        binding.IntelligenceSaveMiscValue.setText("${character.intelligenceSaveMisc}")
        binding.WisdomSaveMiscValue.setText("${character.wisdomSaveMisc}")
        binding.CharismaSaveMiscValue.setText("${character.charismaSaveMisc}")

        binding.StrengthSaveProf.isChecked = character.strengthSaveProf
        binding.DexteritySaveProf.isChecked = character.dexteritySaveProf
        binding.ConstitutionSaveProf.isChecked = character.constitutionSaveProf
        binding.IntelligenceSaveProf.isChecked = character.intelligenceSaveProf
        binding.WisdomSaveProf.isChecked = character.wisdomSaveProf
        binding.CharismaSaveProf.isChecked = character.charismaSaveProf
    }

    private fun packageStat(): HashMap<String, Int> {
        val statsMap: HashMap<String, Int> = hashMapOf()
        statsMap["Strength"] = binding.StrengthScoreValue.text.toString().toInt()
        statsMap["Dexterity"] = binding.DexterityScoreValue.text.toString().toInt()
        statsMap["Constitution"] = binding.ConstitutionScoreValue.text.toString().toInt()
        statsMap["Intelligence"] = binding.IntelligenceScoreValue.text.toString().toInt()
        statsMap["Wisdom"] = binding.WisdomScoreValue.text.toString().toInt()
        statsMap["Charisma"] = binding.CharismaScoreValue.text.toString().toInt()

        statsMap["StrengthSaveMisc"] = binding.StrengthSaveMiscValue.text.toString().toInt()
        statsMap["DexteritySaveMisc"] = binding.DexteritySaveMiscValue.text.toString().toInt()
        statsMap["ConstitutionSaveMisc"] = binding.ConstitutionSaveMiscValue.text.toString().toInt()
        statsMap["IntelligenceSaveMisc"] = binding.IntelligenceSaveMiscValue.text.toString().toInt()
        statsMap["WisdomSaveMisc"] = binding.WisdomSaveMiscValue.text.toString().toInt()
        statsMap["CharismaSaveMisc"] = binding.CharismaSaveMiscValue.text.toString().toInt()

        statsMap["StrengthSaveProf"] = binding.StrengthSaveProf.isChecked.compareTo(false)
        statsMap["DexteritySaveProf"] = binding.DexteritySaveProf.isChecked.compareTo(false)
        statsMap["ConstitutionSaveProf"] = binding.ConstitutionSaveProf.isChecked.compareTo(false)
        statsMap["IntelligenceSaveProf"] = binding.IntelligenceSaveProf.isChecked.compareTo(false)
        statsMap["WisdomSaveProf"] = binding.WisdomSaveProf.isChecked.compareTo(false)
        statsMap["CharismaSaveProf"] = binding.CharismaSaveProf.isChecked.compareTo(false)
        return statsMap
    }

}
