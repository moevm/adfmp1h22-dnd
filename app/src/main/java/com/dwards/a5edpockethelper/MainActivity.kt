
package com.dwards.a5edpockethelper

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dwards.a5edpockethelper.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ChangeStats {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.StatsLayout.setOnLongClickListener {

            val StatsSettingsdialog: CharacteristicSettingsDialog =  CharacteristicSettingsDialog()
            val args = Bundle()
            val stats = passageStat()
            for (i in stats){
            args.putString(i.key, i.value.toString())}
            StatsSettingsdialog.setArguments(args)
            StatsSettingsdialog.show(supportFragmentManager, "CharacteristicSettingsDialog")
            return@setOnLongClickListener true
        }




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

    override fun change(statMap:HashMap<String,Int>) {
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

}
