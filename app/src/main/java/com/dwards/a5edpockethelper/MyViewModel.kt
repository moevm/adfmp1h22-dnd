package com.dwards.a5edpockethelper


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dwards.a5edpockethelper.model.Character
import com.dwards.a5edpockethelper.model.CharacterDAO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MyViewModel(private val characterDao: CharacterDAO, application: Application) : AndroidViewModel(application) {
    //временные флаги для создания персонажей
    var flag1: Boolean = false
    var flag2: Boolean = false

    var currentId: Int = 0
    var character: Character = Character()
    var currentChar: MutableLiveData<Character> = MutableLiveData()
    var characterList: MutableLiveData<List<Character?>> = MutableLiveData()

    init {
        viewModelScope.launch{
            characterList.value = characterDao.getAll()
        }
    }

    fun startDB(){
        getCharacterID()

        viewModelScope.launch {
            val job: Job = viewModelScope.launch{fetchAll()}
            job.join()
            characterList.value = characterDao.getAll()
            if (characterList.value?.size == 0) {
                addCharacter()
                val job: Job = viewModelScope.launch{fetchAll()}
                job.join()
                chooseCharacter(characterList.value?.get(0)?.id!!)
            } else {
                if (currentId != 0) {
                    fetchData(currentId)
                    chooseCharacter(currentId)
                } else
                    chooseCharacter(characterList.value?.get(0)?.id!!)
            }
        }


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

    private fun fetchAll(){
        viewModelScope.launch{
            characterList.value = characterDao.getAll()
        }
    }



    fun chooseCharacter(id: Int){
        currentId = id
        saveCharacterID(id)
        fetchData(currentId)
    }

    private fun saveCharacterID(id: Int){
        val settings: SharedPreferences = getApplication<Application>().applicationContext.getSharedPreferences("gameSetting", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt("id", id)
        editor.apply()
    }

    private fun getCharacterID(){
        val settings: SharedPreferences = getApplication<Application>().applicationContext.getSharedPreferences("gameSetting", Context.MODE_PRIVATE)
        if (settings!=null){
            currentId = settings.getInt("id",0)
        }
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
        character.name = "Name"
        character.charClass = "Class"
        character.level = 1
        character.strength = 10
        character.dexterity = 10
        character.constitution = 10
        character.intelligence = 10
        character.wisdom = 10
        character.charisma = 10
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
            1 -> if (updatedChar.currentHP+updatedChar.tempHP - changeHP > 0){
                //если есть темп хп
                if (updatedChar.tempHP != 0){
                    //если урон больше хп, уменьшаем урон, иначе урон 0
                    if (changeHP > updatedChar.tempHP) {
                        changeHP -= updatedChar.tempHP
                        updatedChar.tempHP = 0
                    }
                    else {
                        updatedChar.tempHP -= changeHP
                        changeHP = 0
                    }

                }
                updatedChar.currentHP -= changeHP
            }
            else {
                updatedChar.currentHP = 0
                updatedChar.tempHP = 0
            }
            2 -> if (updatedChar.currentHP + changeHP < updatedChar.maxHP) updatedChar.currentHP += changeHP else updatedChar.currentHP = updatedChar.maxHP
            3 -> if (updatedChar.tempHP < changeHP) updatedChar.tempHP = changeHP else 0
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

    fun changeCharactersCustomBlock(customBlock1Value: String, customBlock1Text: String,
                                    customBlock2Value: String, customBlock2Text: String,
                                    customBlock3Value: String, customBlock3Text: String,
                                    customBlock4Value: String, customBlock4Text: String){
        val updatedChar = currentChar.value!!
        updatedChar.customBlock1Name = customBlock1Text
        updatedChar.customBlock1Value = customBlock1Value
        updatedChar.customBlock2Name = customBlock2Text
        updatedChar.customBlock2Value = customBlock2Value
        updatedChar.customBlock3Name = customBlock3Text
        updatedChar.customBlock3Value = customBlock3Value
        updatedChar.customBlock4Name = customBlock4Text
        updatedChar.customBlock4Value = customBlock4Value

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

    fun stabilizeCharacter(){
        val updatedChar = currentChar.value!!
        if(updatedChar.maxHP < 1)
            updatedChar.maxHP = 1
        updatedChar.currentHP = 1
        updatedChar.failureDeathSave = 0
        updatedChar.passDeathSave = 0

        pushToDB(updatedChar)
    }

    fun makeDeathSave(result: Boolean){
        val updatedChar = currentChar.value!!
        when (result){
            true -> if (updatedChar.passDeathSave < 3)
                updatedChar.passDeathSave += 1
            false -> if (updatedChar.failureDeathSave < 3)
                updatedChar.failureDeathSave += 1
        }
        if (updatedChar.passDeathSave >= 3){
            stabilizeCharacter()
        }
        else
            pushToDB(updatedChar)
    }

    fun changeCharactersNameClassLevel(name: String, charClass: String, level: Int){
        val updatedChar = currentChar.value!!
        updatedChar.name = name
        updatedChar.charClass = charClass
        updatedChar.level = level
        pushToDB(updatedChar)
    }

    fun changeCharactersSkill(skill: String, miscBonus: Int, prof: Boolean, halfProf: Boolean, doubleProf: Boolean){
        val updatedChar = currentChar.value!!
        when (skill) {
            "Athletics" -> {
                updatedChar.athleticsMiscBonus = miscBonus
                updatedChar.athleticsProf = prof
                updatedChar.athleticsHalfProf = halfProf
                updatedChar.athleticsDoubleProf = doubleProf
            }

            "Acrobatics" -> {
                updatedChar.acrobaticsMiscBonus = miscBonus
                updatedChar.acrobaticsProf = prof
                updatedChar.acrobaticsHalfProf = halfProf
                updatedChar.acrobaticsDoubleProf = doubleProf
            }

            "Sleight of Hand" -> {
                updatedChar.sleightOfHandMiscBonus = miscBonus
                updatedChar.sleightOfHandProf = prof
                updatedChar.sleightOfHandHalfProf = halfProf
                updatedChar.sleightOfHandDoubleProf = doubleProf

            }
            "Stealth" -> {
                updatedChar.stealthMiscBonus = miscBonus
                updatedChar.stealthProf = prof
                updatedChar.stealthHalfProf = halfProf
                updatedChar.stealthDoubleProf = doubleProf
            }
            "Arcana" -> {
                updatedChar.arcanaMiscBonus = miscBonus
                updatedChar.arcanaProf = prof
                updatedChar.arcanaHalfProf = halfProf
                updatedChar.arcanaDoubleProf = doubleProf
            }
            "History" -> {
                updatedChar.historyMiscBonus = miscBonus
                updatedChar.historyProf = prof
                updatedChar.historyHalfProf = halfProf
                updatedChar.historyDoubleProf = doubleProf
            }
            "Investigation" -> {
                updatedChar.investigationMiscBonus = miscBonus
                updatedChar.investigationProf = prof
                updatedChar.investigationHalfProf = halfProf
                updatedChar.investigationDoubleProf = doubleProf
            }
            "Nature" -> {
                updatedChar.natureMiscBonus = miscBonus
                updatedChar.natureProf = prof
                updatedChar.natureHalfProf = halfProf
                updatedChar.natureDoubleProf = doubleProf
            }
            "Religion" -> {
                updatedChar.religionMiscBonus = miscBonus
                updatedChar.religionProf = prof
                updatedChar.religionHalfProf = halfProf
                updatedChar.religionDoubleProf = doubleProf
            }
            "Animal Handling" -> {
                updatedChar.animalHandlingMiscBonus = miscBonus
                updatedChar.animalHandlingProf = prof
                updatedChar.animalHandlingHalfProf = halfProf
                updatedChar.animalHandlingDoubleProf = doubleProf
            }
            "Insight" -> {
                updatedChar.insightMiscBonus = miscBonus
                updatedChar.insightProf = prof
                updatedChar.insightHalfProf = halfProf
                updatedChar.insightDoubleProf = doubleProf
            }
            "Medicine" -> {
                updatedChar.medicineMiscBonus = miscBonus
                updatedChar.medicineProf = prof
                updatedChar.medicineHalfProf = halfProf
                updatedChar.medicineDoubleProf = doubleProf
            }
            "Perception" -> {
                updatedChar.perceptionMiscBonus = miscBonus
                updatedChar.perceptionProf = prof
                updatedChar.perceptionHalfProf = halfProf
                updatedChar.perceptionDoubleProf = doubleProf
            }
            "Survival" -> {
                updatedChar.survivalMiscBonus = miscBonus
                updatedChar.survivalProf = prof
                updatedChar.survivalHalfProf = halfProf
                updatedChar.survivalDoubleProf = doubleProf
            }
            "Deception" -> {
                updatedChar.deceptionMiscBonus = miscBonus
                updatedChar.deceptionProf = prof
                updatedChar.deceptionHalfProf = halfProf
                updatedChar.deceptionDoubleProf = doubleProf
            }
            "Intimidation" -> {
                updatedChar.intimidationMiscBonus = miscBonus
                updatedChar.intimidationProf = prof
                updatedChar.intimidationHalfProf = halfProf
                updatedChar.intimidationDoubleProf = doubleProf
            }
            "Performance" -> {
                updatedChar.performanceMiscBonus = miscBonus
                updatedChar.performanceProf = prof
                updatedChar.performanceHalfProf = halfProf
                updatedChar.performanceDoubleProf = doubleProf
            }
            "Persuasion" -> {
                updatedChar.persuasionMiscBonus = miscBonus
                updatedChar.persuasionProf = prof
                updatedChar.persuasionHalfProf = halfProf
                updatedChar.persuasionDoubleProf = doubleProf
            }
        }
        pushToDB(updatedChar)
    }

    fun deleteToolsProficiency(id: Int){
        val updatedChar = currentChar.value!!
        updatedChar.toolsProficiencyList.removeAt(id)
        pushToDB(updatedChar)
    }

    fun addToolsProficiency(){
        val updatedChar = currentChar.value!!
        updatedChar.toolsProficiencyList.add("Name")
        pushToDB(updatedChar)
    }

    fun changeToolsProficiency(num: Int, value: String){
        val updatedChar = currentChar.value!!
        updatedChar.toolsProficiencyList[num] = value
        pushToDB(updatedChar)
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

    fun calcStat(statMod: Int, miscBonus: Int, prof: Boolean, halfProf: Boolean, doubleProf: Boolean): Int{
        var sum = statMod+miscBonus
        if(doubleProf){
            sum += character.proficiency*2
        }
        else if(prof){
            sum += character.proficiency
        }
        else if(halfProf){
            sum += character.proficiency/2
        }
        return sum
    }

    private fun intToBoolean(b: Int): Boolean {
        return b == 1
    }

}

