package com.dwards.a5edpockethelper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwards.a5edpockethelper.model.Character
import com.dwards.a5edpockethelper.model.CharacterDAO


import kotlinx.coroutines.launch

class MyViewModel(private val characterDao: CharacterDAO, id: Int) : ViewModel() {


    var character: Character = Character()
    var currentChar: MutableLiveData<Character> = MutableLiveData()
    var characterList: MutableLiveData<List<Character?>> = MutableLiveData()

    init {

        if (character.id == null) {
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



        character = Character()
        character.id = id+1
        character.name = "Gef"
        character.strength = 10
        character.dexterity = 10
        character.constitution = 10
        character.intelligence = 10
        character.wisdom = 10
        character.charisma = 10
        viewModelScope.launch {
            characterDao.insertChar(character)
        }

        fetchData(id)
    }

    fun getCharacter() = currentChar

    fun getAllCharacters() = characterList

    private fun fetchData(id: Int)
    {
        viewModelScope.launch{
            currentChar.value = characterDao.getById(id)
            characterList.value = characterDao.getAll()
        }
    }

    fun changeCharacter(statMap: HashMap<String, Int> ){
        character.id = currentChar.value?.id
        character.strength = statMap["Strength"]!!
        character.dexterity = statMap["Dexterity"]!!
        character.constitution = statMap["Constitution"]!!
        character.intelligence = statMap["Intelligence"]!!
        character.wisdom = statMap["Wisdom"]!!
        character.charisma = statMap["Charisma"]!!
        viewModelScope.launch{
            characterDao.updateChar(character)
        }

        fetchData(character.id!!)
    }
    /*
    fun changeCharacter(statMap: HashMap<String, Int> ){
        currentChar.value?.strength = statMap["Strength"]!!
        currentChar.value?.dexterity = statMap["Dexterity"]!!
        currentChar.value?.constitution = statMap["Constitution"]!!
        currentChar.value?.intelligence = statMap["Intelligence"]!!
        currentChar.value?.wisdom = statMap["Wisdom"]!!
        currentChar.value?.charisma = statMap["Charisma"]!!
        viewModelScope.launch{
            characterDao.updateChar(currentChar.value!!)
        }

        }
     */


}