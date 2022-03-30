package com.dwards.a5edpockethelper.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dwards.a5edpockethelper.StringListConverter
import kotlinx.serialization.Serializable

@Entity(tableName = "Spell")
@TypeConverters(StringListConverter::class)
@Serializable
data class Spell(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String = "",
    var level: Int = 0,
    @TypeConverters(StringListConverter::class)
    var classes: MutableList<String> = mutableListOf(),
    var text: String = "",
    var school: String = "",
    var castingTime: String = "",
    var range: String = "",
    var materials: String = "",
    var components: String = "",
    var duration: String = "",
    var source: String = "",
    var ritual: Boolean = false
)