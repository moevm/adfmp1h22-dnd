
package com.dwards.a5edpockethelper

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dwards.a5edpockethelper.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ChangeStats{

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun change(statMap:HashMap<String,Int>) {
        val strength = statMap.get("Strength")
        val dexterity = statMap.get("Dexterity")
        val constitution = statMap.get("Constitution")
        val intelligence = statMap.get("Intelligence")
        val wisdom = statMap.get("Wisdom")
        val charisma = statMap.get("Charisma")
    }





}
