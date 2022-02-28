package com.dwards.a5edpockethelper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dwards.a5edpockethelper.model.Character
import com.dwards.a5edpockethelper.model.CharacterDAO


import kotlinx.coroutines.launch

class MyViewModel(private val characterDao: CharacterDAO, id: Int) : ViewModel() {
    //временные флаги для создания персонажей
    var flag1: Boolean = false
    var flag2: Boolean = false

    var currentId: Int = 0
    var character: Character = Character()
    var currentChar: MutableLiveData<Character> = MutableLiveData()
    var characterList: MutableLiveData<List<Character?>> = MutableLiveData()

    init {
        currentId = id



        if (flag1) {
            var character = Character()
            //character.id = currentId
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
            flag1 = false
            //currentId++
        }

        if (flag2)  {
            character = Character()
            //character.id = currentId
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
            flag2 = false
        }

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
        currentId = id
        fetchData(currentId)
    }

    fun deleteCharacter(id: Int){
        viewModelScope.launch{
            var deletedchar: Character? = characterDao.getById(id)
            characterDao.delete(deletedchar!!)
            characterList.value = characterDao.getAll()
            if (characterList.value?.size != 0 && currentId==id) {
                chooseCharacter(characterList.value?.get(0)?.id!!)
            }
        }


        //currentId--
    }

    //Пока это просто заглушка болванка для проверки добавления в БД, функционала нет
    fun addCharacter(){
        character = Character()
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

    }

    fun changeCharactersStats(statMap: HashMap<String, Int> ){
        val updatedChar = currentChar.value!!
        updatedChar.strength = statMap["Strength"]!!
        updatedChar.dexterity = statMap["Dexterity"]!!
        updatedChar.constitution = statMap["Constitution"]!!
        updatedChar.intelligence = statMap["Intelligence"]!!
        updatedChar.wisdom = statMap["Wisdom"]!!
        updatedChar.charisma = statMap["Charisma"]!!

        updatedChar.strengthSaveMisc = statMap["StrengthSaveMisc"]!!
        updatedChar.dexteritySaveMisc = statMap["DexteritySaveMisc"]!!
        updatedChar.constitutionSaveMisc = statMap["ConstitutionSaveMisc"]!!
        updatedChar.intelligenceSaveMisc = statMap["IntelligenceSaveMisc"]!!
        updatedChar.wisdomSaveMisc = statMap["WisdomSaveMisc"]!!
        updatedChar.charismaSaveMisc = statMap["CharismaSaveMisc"]!!

        updatedChar.strengthSaveProf = intToBoolean(statMap["StrengthSaveProf"]!!)
        updatedChar.dexteritySaveProf = intToBoolean(statMap["DexteritySaveProf"]!!)
        updatedChar.constitutionSaveProf = intToBoolean(statMap["ConstitutionSaveProf"]!!)
        updatedChar.intelligenceSaveProf = intToBoolean(statMap["IntelligenceSaveProf"]!!)
        updatedChar.wisdomSaveProf = intToBoolean(statMap["WisdomSaveProf"]!!)
        updatedChar.charismaSaveProf = intToBoolean(statMap["CharismaSaveProf"]!!)


        viewModelScope.launch{
            characterDao.updateChar(updatedChar)
        }
        fetchData(updatedChar.id!!)
    }

    fun changeCharactersProficiency(proficiency: Int){
        val updatedChar = currentChar.value!!
        updatedChar.proficiency = proficiency
        viewModelScope.launch{
            characterDao.updateChar(updatedChar)
        }
        fetchData(updatedChar.id!!)
    }

    fun intToBoolean(b: Int): Boolean {
        return b == 1
    }

}