package com.dwards.a5edpockethelper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.model.CharacterDAO

class MyViewModelFactory(private val characterDao: CharacterDAO, val app: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(characterDao, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}