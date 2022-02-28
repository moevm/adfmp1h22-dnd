package com.dwards.a5edpockethelper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwards.a5edpockethelper.model.Character
import com.dwards.a5edpockethelper.model.CharacterDAO
import kotlinx.coroutines.ExperimentalCoroutinesApi

import kotlinx.coroutines.launch

class MyViewModel(private val characterDao: CharacterDAO, id: Int) : ViewModel() {


    var character: Character? = null
    var currentChar: MutableLiveData<Character> = MutableLiveData()

    init {
        if (character == null) {
            var character = Character()
            character.id = id
            character.name = "Arno"
            character.strength = 20
            character.dexterity = 20
            character.constitution = 20
            character.intelligence = 20
            character.wisdom = 20
            character.charisma = 20
            viewModelScope.launch{
                characterDao.insertChar(character)
            }
        }

        fetchData(id)
    }

    fun getCharacter() = currentChar

    private fun fetchData(id: Int)
    {
        viewModelScope.launch{
            currentChar.value = characterDao.getById(id)
        }
    }
}