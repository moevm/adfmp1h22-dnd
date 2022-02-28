package com.dwards.a5edpockethelper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.model.CharacterDAO

class MyViewModelFactory(private val characterDao: CharacterDAO, private val characterID: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(characterDao, characterID) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}