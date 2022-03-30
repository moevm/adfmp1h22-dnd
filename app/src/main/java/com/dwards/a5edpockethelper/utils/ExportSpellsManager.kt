package com.dwards.a5edpockethelper.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ExportSpellsManager {

    private val Context.spellsPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "adfmp1h22-dnd"
    )

    private val SPELL_ENTRIES = stringPreferencesKey("spell_entities")

    @ExperimentalSerializationApi
    suspend fun saveSpellsEntity(context: Context, id: String, name: String) {
        context.spellsPreferencesDataStore.edit { preferences ->
            val spellsString = preferences[SPELL_ENTRIES] ?: ""
            val spellEntities = if (spellsString.isNotBlank()) {
                Json.decodeFromString<List<SpellEntity>>(spellsString).toMutableList()
            } else {
                mutableListOf()
            }
            spellEntities.add(SpellEntity(id, name))
            preferences[SPELL_ENTRIES] = Json.encodeToString(spellEntities)
        }
    }

    @ExperimentalSerializationApi
    suspend fun saveSpellsToDataStore(context: Context, spellEntities: List<SpellEntity>) {
        context.spellsPreferencesDataStore.edit { preferences ->
            preferences[SPELL_ENTRIES] = Json.encodeToString(spellEntities)
        }
    }

    @ExperimentalSerializationApi
    suspend fun getSpellsFromDataStore(context: Context): Flow<List<SpellEntity>> {
        return context.spellsPreferencesDataStore.data.map { preferences ->
            val spellsString = preferences[SPELL_ENTRIES] ?: ""
            Json.decodeFromString(spellsString)
        }
    }
}

@Serializable
data class SpellEntity(
    val id: String,
    val name: String
)