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
    var Strength: Int = 0,
    var Dexterity: Int = 0,
    var Constitution: Int = 0,
    var Intelligence: Int = 0,
    var Wisdom: Int = 0,
    var Charisma: Int = 0
)