package com.dwards.a5edpockethelper.model

import androidx.room.*
import com.dwards.a5edpockethelper.StringIntListConverter
import com.dwards.a5edpockethelper.StringListConverter


@Entity(tableName = "Character")
@TypeConverters(StringListConverter::class, StringIntListConverter::class)
data class Character(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String = "Name",
    var charClass: String = "Class",
    var level: Int = 1,

    var maxHP: Int = 10,
    var currentHP: Int = 10,
    var tempHP: Int = 0,

    var passDeathSave: Int = 0,
    var failureDeathSave: Int = 0,
    //stats
    var strength: Int = 10,
    var dexterity: Int = 10,
    var constitution: Int = 10,
    var intelligence: Int = 10,
    var wisdom: Int = 10,
    var charisma: Int = 10,
    //save proficiency
    var strengthSaveProf: Boolean = false,
    var dexteritySaveProf: Boolean = false,
    var constitutionSaveProf: Boolean = false,
    var intelligenceSaveProf: Boolean = false,
    var wisdomSaveProf: Boolean = false,
    var charismaSaveProf: Boolean = false,
    //save misc bonus
    var strengthSaveMisc: Int = 0,
    var dexteritySaveMisc: Int = 0,
    var constitutionSaveMisc: Int = 0,
    var intelligenceSaveMisc: Int = 0,
    var wisdomSaveMisc: Int = 0,
    var charismaSaveMisc: Int = 0,

    var proficiency: Int = 2,
    //armor
    var armorBonus: Int = 10,
    var shieldBonus: Int = 0,
    var miscArmorBonus: Int = 0,
    var armorType: Int = 2,
    var statBonusArmor: Int = 0,
    var maxDexterityBonus: Int = 2,

    var miscInitiativeBonus: Int = 0,
    var initiativeProf: Boolean = false,
    var initiativeHalfProf: Boolean = false,
    var initiativeDoubleProf: Boolean = false,
    var initiativeAdditionalAbility: Int = 0,

    //hit dice
    var hitDiceCount: Int = 0,
    var hitDiceSize: Int = 0,
    //speed
    var chosenSpeed: String = "Walk",
    var baseWalkSpeed: Int = 30,
    var miscWalkSpeedBonus: Int = 0,
    var baseFlySpeed: Int = 0,
    var miscFlySpeedBonus: Int = 0,
    var baseSwimSpeed: Int = 0,
    var miscSwimSpeedBonus: Int = 0,
    var baseBurrowSpeed: Int = 0,
    var miscBurrowSpeedBonus: Int = 0,
    var baseClimbSpeed: Int = 0,
    var miscClimbSpeedBonus: Int = 0,

    var customBlock1Name: String = "CustomBlock1",
    var customBlock1Value: String = "0",
    var customBlock2Name: String = "CustomBlock2",
    var customBlock2Value: String = "0",
    var customBlock3Name: String = "CustomBlock3",
    var customBlock3Value: String = "0",
    var customBlock4Name: String = "CustomBlock4",
    var customBlock4Value: String = "0",

    // athletics
    var athleticsMiscBonus: Int = 0,
    var athleticsProf: Boolean = false,
    var athleticsHalfProf: Boolean = false,
    var athleticsDoubleProf: Boolean = false,
    // acrobatics
    var acrobaticsMiscBonus: Int = 0,
    var acrobaticsProf: Boolean = false,
    var acrobaticsHalfProf: Boolean = false,
    var acrobaticsDoubleProf: Boolean = false,
    // sleight of hand
    var sleightOfHandMiscBonus: Int = 0,
    var sleightOfHandProf: Boolean = false,
    var sleightOfHandHalfProf: Boolean = false,
    var sleightOfHandDoubleProf: Boolean = false,
    // stealth
    var stealthMiscBonus: Int = 0,
    var stealthProf: Boolean = false,
    var stealthHalfProf: Boolean = false,
    var stealthDoubleProf: Boolean = false,
    // arcana
    var arcanaMiscBonus: Int = 0,
    var arcanaProf: Boolean = false,
    var arcanaHalfProf: Boolean = false,
    var arcanaDoubleProf: Boolean = false,
    // history
    var historyMiscBonus: Int = 0,
    var historyProf: Boolean = false,
    var historyHalfProf: Boolean = false,
    var historyDoubleProf: Boolean = false,
    // investigation
    var investigationMiscBonus: Int = 0,
    var investigationProf: Boolean = false,
    var investigationHalfProf: Boolean = false,
    var investigationDoubleProf: Boolean = false,
    // nature
    var natureMiscBonus: Int = 0,
    var natureProf: Boolean = false,
    var natureHalfProf: Boolean = false,
    var natureDoubleProf: Boolean = false,
    // religion
    var religionMiscBonus: Int = 0,
    var religionProf: Boolean = false,
    var religionHalfProf: Boolean = false,
    var religionDoubleProf: Boolean = false,
    // animal handling
    var animalHandlingMiscBonus: Int = 0,
    var animalHandlingProf: Boolean = false,
    var animalHandlingHalfProf: Boolean = false,
    var animalHandlingDoubleProf: Boolean = false,
    // insight
    var insightMiscBonus: Int = 0,
    var insightProf: Boolean = false,
    var insightHalfProf: Boolean = false,
    var insightDoubleProf: Boolean = false,
    // medicine
    var medicineMiscBonus: Int = 0,
    var medicineProf: Boolean = false,
    var medicineHalfProf: Boolean = false,
    var medicineDoubleProf: Boolean = false,
    // perception
    var perceptionMiscBonus: Int = 0,
    var perceptionProf: Boolean = false,
    var perceptionHalfProf: Boolean = false,
    var perceptionDoubleProf: Boolean = false,
    // survival
    var survivalMiscBonus: Int = 0,
    var survivalProf: Boolean = false,
    var survivalHalfProf: Boolean = false,
    var survivalDoubleProf: Boolean = false,
    // deception
    var deceptionMiscBonus: Int = 0,
    var deceptionProf: Boolean = false,
    var deceptionHalfProf: Boolean = false,
    var deceptionDoubleProf: Boolean = false,
    // intimidation
    var intimidationMiscBonus: Int = 0,
    var intimidationProf: Boolean = false,
    var intimidationHalfProf: Boolean = false,
    var intimidationDoubleProf: Boolean = false,
    // performance
    var performanceMiscBonus: Int = 0,
    var performanceProf: Boolean = false,
    var performanceHalfProf: Boolean = false,
    var performanceDoubleProf: Boolean = false,
    // persuasion
    var persuasionMiscBonus: Int = 0,
    var persuasionProf: Boolean = false,
    var persuasionHalfProf: Boolean = false,
    var persuasionDoubleProf: Boolean = false,

    //Tools Proficiency
    @TypeConverters(StringListConverter::class)
    var toolsProficiencyList: MutableList<String> = mutableListOf(),
    //Language Proficiency
    @TypeConverters(StringListConverter::class)
    var languageProficiencyList: MutableList<String> = mutableListOf(),
    //Weapon id list
    //@TypeConverters(StringListConverter::class)
    //var weaponList: MutableList<String> = mutableListOf(),

    @TypeConverters(StringIntListConverter::class)
    var spellsFavorite: MutableList<Int> = mutableListOf(),
    @TypeConverters(StringIntListConverter::class)
    var spellsPrepared: MutableList<Int> = mutableListOf(),
)
