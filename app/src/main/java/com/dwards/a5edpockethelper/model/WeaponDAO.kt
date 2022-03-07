package com.dwards.a5edpockethelper.model

import androidx.room.*


@Dao
interface WeaponDAO {
    @Query("SELECT * FROM Weapon")
    suspend fun getAll(): List<Weapon?>?

    @Query("SELECT * FROM Weapon WHERE id = :id")
    suspend fun getById(id: Int): Weapon?

    // Добавление в бд
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeapon(weapon: Weapon)

    // Удаление из бд
    @Delete
    suspend  fun delete(weapon: Weapon)

    // Изменение в бд
    @Update
    suspend  fun updateWeapon(weapon: Weapon)
}
