package com.dwards.a5edpockethelper.model

import androidx.room.*


@Dao
interface SpellDAO {
    @Query("SELECT * FROM Spell")
    suspend fun getAll(): List<Spell?>?

    @Query("SELECT * FROM Spell WHERE id = :id")
    suspend fun getById(id: Int): Spell?

    // Добавление в бд
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpell(spell: Spell)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(spellList: List<Spell>)

    // Удаление из бд
    @Delete
    suspend fun delete(spell: Spell)

    // Изменение в бд
    @Update
    suspend fun updateSpell(spell: Spell)
}
