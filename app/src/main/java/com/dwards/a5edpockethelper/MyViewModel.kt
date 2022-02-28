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

    fun changeCharactersSpeed(speed: Int, miscBonus: Int){
        val updatedChar = currentChar.value!!
        when(updatedChar.chosenSpeed){
            "Walk" -> {
                updatedChar.baseWalkSpeed = speed
                updatedChar.miscWalkSpeedBonus = miscBonus
            }
            "Fly" -> {
                updatedChar.baseFlySpeed = speed
                updatedChar.miscFlySpeedBonus = miscBonus
            }
            "Swim" -> {
                updatedChar.baseSwimSpeed = speed
                updatedChar.miscSwimSpeedBonus = miscBonus
            }
            "Climb" -> {
                updatedChar.baseClimbSpeed = speed
                updatedChar.miscClimbSpeedBonus = miscBonus
            }
        }
        viewModelScope.launch{
            characterDao.updateChar(updatedChar)
        }
        fetchData(updatedChar.id!!)
    }

    fun changeChosenSpeed(type: Int){
        val updatedChar = currentChar.value!!
        when(type){
            1 -> updatedChar.chosenSpeed = "Walk"
            2 -> updatedChar.chosenSpeed = "Fly"
            3 -> updatedChar.chosenSpeed = "Swim"
            4 -> updatedChar.chosenSpeed = "Climb"
        }
    }

    fun calcSave(value: Int, saveProf: Boolean, misc: Int): String{
        var sum: Int = calcModifier(value).toInt()
        if (saveProf)
            sum+=currentChar.value?.proficiency!!
            sum+= misc
        return if (sum>0)
            "+$sum"
        else
            "$sum"
    }

    fun calcModifier(value: Int) = when (value) {
            1, 2 -> "-4"
            3, 4 -> "-3"
            5, 6 -> "-2"
            7, 8 -> "-1"
            9, 10 -> "0"
            11, 12 -> "+1"
            13, 14 -> "+2"
            15, 16 -> "+3"
            17, 18 -> "+4"
            19, 20 -> "+5"
            21, 22 -> "+6"
            23, 24 -> "+7"
            25, 26 -> "+8"
            27, 28 -> "+9"
            29, 30 -> "+10"
            else -> "0"
    }

    fun calcSpeed(base: Int, misc: Int): Int{
        return base+misc
    }

    private fun intToBoolean(b: Int): Boolean {
        return b == 1
    }

}