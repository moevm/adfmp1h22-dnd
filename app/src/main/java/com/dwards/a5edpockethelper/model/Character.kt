package com.dwards.a5edpockethelper.model

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase


@Entity(tableName = "character")
data class Character(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String = "",
    var charClass: String = "",
    var level: Int = 1,

    var maxHP: Int = 10,
    var currentHP: Int = 10,
    var TempHP: Int = 0,
    //stats
    var strength: Int = 0,
    var dexterity: Int = 0,
    var constitution: Int = 0,
    var intelligence: Int = 0,
    var wisdom: Int = 0,
    var charisma: Int = 0,
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

    var armorBonus: Int = 10,
    var shieldBonus: Int = 0,
    var miscArmorBonus: Int = 0,

    var miscInitiativeBonus: Int = 0,

    var hitDiceCount: Int = 0,
    var hitDiceSize: Int = 0,

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
)