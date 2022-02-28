package com.dwards.a5edpockethelper

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        if (stringListString == "")
            return  mutableListOf()
        else
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}