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
        }

        if (flag2)  {
            character = Character()
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


        pushToDB(updatedChar)
        fetchData(updatedChar.id!!)
    }

    fun changeCharactersProficiency(proficiency: Int){
        val updatedChar = currentChar.value!!
        updatedChar.proficiency = proficiency

        pushToDB(updatedChar)
        fetchData(updatedChar.id!!)
    }

    fun changeCharactersHitDice(hitDiceCount: Int, hitDiceSize: Int){
        val updatedChar = currentChar.value!!
        updatedChar.hitDiceCount = hitDiceCount
        updatedChar.hitDiceSize = hitDiceSize

        pushToDB(updatedChar)
        fetchData(updatedChar.id!!)
    }

    fun changeCharactersMaxHP(maxHP: Int){
        val updatedChar = currentChar.value!!
        updatedChar.maxHP = maxHP
        if (updatedChar.currentHP > maxHP){
            updatedChar.currentHP = maxHP
        }
        //А если 0??? Добавить!!!
        pushToDB(updatedChar)
        fetchData(updatedChar.id!!)
    }

    fun changeCharactersCurrentHP(diffHP: Int, mode: Int){
        var changeHP = diffHP
        val updatedChar = currentChar.value!!
        when (mode){
            //дамаг
            1 -> if (updatedChar.currentHP+updatedChar.TempHP - changeHP > 0){
                //если есть темп хп
                if (updatedChar.TempHP != 0){
                    //если урон больше хп, уменьшаем урон, иначе урон 0
                    if (changeHP > updatedChar.TempHP) {
                        changeHP -= updatedChar.TempHP
                        updatedChar.TempHP = 0
                    }
                    else {
                        updatedChar.TempHP -= changeHP
                        changeHP = 0
                    }

                }
                updatedChar.currentHP -= changeHP
            }
            else updatedChar.currentHP = 0
            2 -> if (updatedChar.currentHP + changeHP < updatedChar.maxHP) updatedChar.currentHP += changeHP else updatedChar.currentHP = updatedChar.maxHP
            3 -> if (updatedChar.TempHP < changeHP) updatedChar.TempHP = changeHP else 0
        }
        pushToDB(updatedChar)
        fetchData(updatedChar.id!!)
    }

    fun changeCharactersSpeed(speed: Int, miscBonus: Int, speedType: String){
        val updatedChar = currentChar.value!!
        when(speedType){
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
        updatedChar.chosenSpeed = speedType
            pushToDB(updatedChar)
    }

    fun changeCharactersInitiative(miscBonus: Int, prof: Boolean, halfProf: Boolean, doubleProf: Boolean, additionalStat: Int){
        val updatedChar = currentChar.value!!
        updatedChar.miscInitiativeBonus = miscBonus
        updatedChar.initiativeProf = prof
        updatedChar.initiativeHalfProf = halfProf
        updatedChar.initiativeDoubleProf = doubleProf
        updatedChar.initiativeAdditionalAbility = additionalStat
        pushToDB(updatedChar)
    }

    fun changeCharactersArmor(armorBonus: Int, shieldBonus: Int, maxDex: Int, miscBonus: Int, armorType: Int, additionalStatBonus: Int){
        val updatedChar = currentChar.value!!
        updatedChar.armorBonus = armorBonus
        updatedChar.shieldBonus = shieldBonus
        updatedChar.miscArmorBonus = miscBonus
        updatedChar.armorType = armorType
        updatedChar.maxDexterityBonus = maxDex
        updatedChar.statBonusArmor = additionalStatBonus

        pushToDB(updatedChar)
    }

    fun calcArmor(armorBonus: Int, shieldBonus: Int, maxDex: Int, miscBonus: Int, armorType: Int, additionalStatBonus: Int): Int{
        val updatedChar = currentChar.value!!
        var sum: Int = armorBonus + shieldBonus
        sum += miscBonus
        if (maxDex < calcModifier(updatedChar.dexterity).toInt()) sum += maxDex else sum += calcModifier(updatedChar.dexterity).toInt()
        when (additionalStatBonus){
            0 -> 0
            1 -> sum += calcModifier(updatedChar.strength).toInt()
            2 -> sum += calcModifier(updatedChar.constitution).toInt()
            3 -> sum += calcModifier(updatedChar.intelligence).toInt()
            4 -> sum += calcModifier(updatedChar.wisdom).toInt()
            5 -> sum += calcModifier(updatedChar.charisma).toInt()
        }
        return sum
    }




    private fun pushToDB(updatedChar: Character){
        viewModelScope.launch{
            characterDao.updateChar(updatedChar)
            fetchData(updatedChar.id!!)
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
            0 -> "-5"
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

    fun calcInitiative(dexModifier: Int, miscBonus: Int, prof: Boolean, halfProf: Boolean, doubleProf: Boolean, additionalStat: Int, profBonus: Int): Int{
        var sum = dexModifier+miscBonus
        if(doubleProf){
            sum += profBonus*2
        }
        else if(prof){
            sum += profBonus
        }
        else if(halfProf){
            sum += profBonus/2
        }
        when (additionalStat){
            1 -> sum += calcModifier(currentChar.value?.strength!!).toInt()
            2 -> sum += calcModifier(currentChar.value?.dexterity!!).toInt()
            3 -> sum += calcModifier(currentChar.value?.constitution!!).toInt()
            4 -> sum += calcModifier(currentChar.value?.intelligence!!).toInt()
            5 -> sum += calcModifier(currentChar.value?.wisdom!!).toInt()
            6 -> sum += calcModifier(currentChar.value?.charisma!!).toInt()
        }
        return sum
    }

    private fun intToBoolean(b: Int): Boolean {
        return b == 1
    }

}