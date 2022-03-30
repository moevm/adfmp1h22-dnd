package com.dwards.a5edpockethelper.utils

import com.dwards.a5edpockethelper.model.Spell
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*

object ImportExportSpellsConverter {

    @ExperimentalSerializationApi
    fun convertSpellsListToJsonString(spells: List<Spell>) = Json.encodeToString(spells)

    fun getCurrentTimeBackupName(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("y-M-d-H-m-s", Locale.US)
        return "${simpleDateFormat.format(calendar.time)}_spells_backup.json"
    }

}