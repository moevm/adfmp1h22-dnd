package com.dwards.a5edpockethelper.model

import androidx.room.*


    @Dao
    interface CharacterDAO {
        @Query("SELECT * FROM Character")
        fun getAll(): List<Character?>?

        @Query("SELECT * FROM Character WHERE id = :id")
        fun getById(id: Int): Character?

        // Добавление в бд
        @Insert
        open fun insertChar(character: Character)

        // Удаление из бд
        @Delete
        fun delete(character: Character)

        // Изменение в бд
        @Update
        fun update(character: Character)
    }
