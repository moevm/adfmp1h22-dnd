package com.dwards.a5edpockethelper

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dwards.a5edpockethelper.databinding.CharacteristicSettingsDialogBinding


class CharacteristicSettingsDialog : DialogFragment(){

    private var _binding: CharacteristicSettingsDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var changestat: ChangeStats
    override fun onAttach(context: Context){
        super.onAttach(context)
        changestat = context as ChangeStats
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = CharacteristicSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.SaveButton.setOnClickListener {

            changestat.change(passageStat ())
            dismiss()
           }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val strength = arguments?.getString("Strength")
        val dexterity = arguments?.getString("Dexterity")
        val constitution = arguments?.getString("Constitution")
        val intelligence = arguments?.getString("Intelligence")
        val wisdom = arguments?.getString("Wisdom")
        val charisma = arguments?.getString("Charisma")
        binding.StrengthScoreValue.setText("${strength}")
        binding.DexterityScoreValue.setText("${dexterity}")
        binding.ConstitutionScoreValue.setText("${constitution}")
        binding.IntelligenceScoreValue.setText("${intelligence}")
        binding.WisdomScoreValue.setText("${wisdom}")
        binding.CharismaScoreValue.setText("${charisma}")
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun passageStat ():HashMap<String,Int>{
        val statsMap:HashMap<String,Int> = hashMapOf()
        statsMap.put("Strength",binding.StrengthScoreValue.text.toString().toInt())
        statsMap.put("Dexterity",binding.DexterityScoreValue.text.toString().toInt())
        statsMap.put("Constitution",binding.ConstitutionScoreValue.text.toString().toInt())
        statsMap.put("Intelligence",binding.IntelligenceScoreValue.text.toString().toInt())
        statsMap.put("Wisdom",binding.WisdomScoreValue.text.toString().toInt())
        statsMap.put("Charisma",binding.CharismaScoreValue.text.toString().toInt())
        return statsMap
    }

}
