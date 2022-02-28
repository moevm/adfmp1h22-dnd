package com.dwards.a5edpockethelper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwards.a5edpockethelper.model.Character
import com.dwards.a5edpockethelper.model.CharacterDAO


import kotlinx.coroutines.launch

class MyViewModel(private val characterDao: CharacterDAO, id: Int) : ViewModel() {

    var currentId: Int = 0
    var character: Character = Character()
    var currentChar: MutableLiveData<Character> = MutableLiveData()
    var characterList: MutableLiveData<List<Character?>> = MutableLiveData()

    init {
        currentId = id

        if (character.id == null) {
            var character = Character()
            character.id = currentId
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
            currentId++
        }



        character = Character()
        character.id = currentId
        character.name = "Kostoprav"
        character.strength = 10
        character.dexterity = 10
        character.constitution = 10
        character.intelligence = 10
        character.wisdom = 10
        character.charisma = 10
        viewModelScope.launch {
            characterDao.insertChar(character)
        }
        currentId++
        fetchData(0)
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

    fun chooseCharacter(id: Int){
        fetchData(id)
    }

    fun deleteCharacter(id: Int){
        viewModelScope.launch{
            var deletedchar: Character? = characterDao.getById(id)
            characterDao.delete(deletedchar!!)
            characterList.value = characterDao.getAll()
        }
        if (id!=0)
            chooseCharacter(id-1)
        else
            chooseCharacter(id+1)
        currentId--
    }

    //Пока это просто заглушка болванка для проверки добавления в БД, функционала нет
    fun addCharacter(){
        character = Character()
        character.id = currentId
        character.name = "Molchun"
        character.strength = 18
        character.dexterity = 14
        character.constitution = 11
        character.intelligence = 7
        character.wisdom = 15
        character.charisma = 13
        viewModelScope.launch {
            characterDao.insertChar(character)
            characterList.value = characterDao.getAll()
        }
        currentId++
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



}