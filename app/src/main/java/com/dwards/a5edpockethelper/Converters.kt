package com.dwards.a5edpockethelper

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return if (stringListString.isBlank()) {
            mutableListOf()
        } else {
            stringListString.split(",").map { it }
        }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}