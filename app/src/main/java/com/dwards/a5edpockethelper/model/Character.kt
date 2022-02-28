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
    var strength: Int = 0,
    var dexterity: Int = 0,
    var constitution: Int = 0,
    var intelligence: Int = 0,
    var wisdom: Int = 0,
    var charisma: Int = 0
)