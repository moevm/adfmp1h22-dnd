package com.dwards.a5edpockethelper

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.dwards.a5edpockethelper.databinding.FragmentCharacterScreenBinding


class CharacterScreen : Fragment(), CharacteristicSettingsDialog.StatChange{
    private val TAG = "MainFragment"


    private var _binding: FragmentCharacterScreenBinding? = null
    private val binding get() = _binding!!

    override fun sendStats(statMap:HashMap<String,Int>) {
        change(statMap)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCharacterScreenBinding.inflate(inflater, container, false)
        val view = binding.root


        //val mainLayout =binding.StrengthLayout as ConstraintLayout
        //val gd = GradientDrawable()
        //gd.setColor(Color.RED);
        //gd.setCornerRadius(10F);
        //gd.setStroke(2, Color.RED);
        //mainLayout.setBackgroundDrawable(gd);


        //var layer = binding.StrengthLayout.getBackground() as LayerDrawable
        //layer.mutate()
        //val drawable = view.background as GradientDrawable
        //drawable.setStroke(3, Color.RED) // set stroke width and stroke color

        //(resources.getColor(R.color.redColor, null))
        //layer.setDrawableByLayerId(R.id.StrengthLayout, newl)
        //layer.invalidateSelf()

        binding.StatsLayout.setOnLongClickListener {

            val StatsSettingsdialog: CharacteristicSettingsDialog =  CharacteristicSettingsDialog()
            val args = Bundle()
            val stats = passageStat()
            for (i in stats){
                args.putString(i.key, i.value.toString())}
            StatsSettingsdialog.setArguments(args)

            StatsSettingsdialog.setTargetFragment(this@CharacterScreen, 1);

            StatsSettingsdialog.show(parentFragmentManager, "CharacteristicSettingsDialog")
            return@setOnLongClickListener true
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    fun change(statMap:HashMap<String,Int>) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}