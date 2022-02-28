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
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner);

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

            viewModel.changeCharacter(packageStat())
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
        //val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun refreshChar(character: Character) {
        binding.StrengthScoreValue.setText("${character.strength}")
        binding.DexterityScoreValue.setText("${character.dexterity}")
        binding.ConstitutionScoreValue.setText("${character.constitution}")
        binding.IntelligenceScoreValue.setText("${character.intelligence}")
        binding.WisdomScoreValue.setText("${character.wisdom}")
        binding.CharismaScoreValue.setText("${character.charisma}")
    }

    fun packageStat(): HashMap<String, Int> {
        val statsMap: HashMap<String, Int> = hashMapOf()
        statsMap.put("Strength", binding.StrengthScoreValue.text.toString().toInt())
        statsMap.put("Dexterity", binding.DexterityScoreValue.text.toString().toInt())
        statsMap.put("Constitution", binding.ConstitutionScoreValue.text.toString().toInt())
        statsMap.put("Intelligence", binding.IntelligenceScoreValue.text.toString().toInt())
        statsMap.put("Wisdom", binding.WisdomScoreValue.text.toString().toInt())
        statsMap.put("Charisma", binding.CharismaScoreValue.text.toString().toInt())
        return statsMap
    }

}
