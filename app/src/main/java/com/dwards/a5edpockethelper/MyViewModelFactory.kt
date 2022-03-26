package com.dwards.a5edpockethelper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.model.CharacterAndWeaponsDAO
import com.dwards.a5edpockethelper.model.CharacterDAO
import com.dwards.a5edpockethelper.model.SpellDAO
import com.dwards.a5edpockethelper.model.WeaponDAO

class MyViewModelFactory(private val characterAndWeaponsDao: CharacterAndWeaponsDAO, private val weaponDao: WeaponDAO, private val characterDao: CharacterDAO, private val spellDao: SpellDAO, val app: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(characterAndWeaponsDao, weaponDao, characterDao, spellDao, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

