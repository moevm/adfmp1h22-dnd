package com.dwards.a5edpockethelper.model

import androidx.room.Embedded
import androidx.room.Relation

//@Entity(tableName = "CharacterWeapons") //todo это че такое? таблица?
//@TypeConverters(StringListConverter::class)
data class CharacterAndWeapons(
    @Embedded val character: Character,
    @Relation(
        parentColumn = "id",
        entityColumn = "charOwnerID"
    )
    val weapons: List<Weapon>
)

