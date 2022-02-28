package com.dwards.a5edpockethelper.model

import androidx.room.*


    @Dao
    interface CharacterDAO {
        @Query("SELECT * FROM Character")
        fun getAll(): List<Character?>?

        @Query("SELECT * FROM Character WHERE id = :id")
        suspend fun getById(id: Int): Character?

        // Добавление в бд
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertChar(character: Character)

        // Удаление из бд
        @Delete
        suspend  fun delete(character: Character)

        // Изменение в бд
        @Update
        suspend  fun update(character: Character)
    }
