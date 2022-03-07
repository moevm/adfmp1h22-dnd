package com.dwards.a5edpockethelper.model

import androidx.room.Query
import androidx.room.Transaction
import androidx.room.*


@Dao
interface CharacterAndWeaponsDAO {
    @Transaction
    @Query("SELECT * FROM Character")
    suspend fun getCharacterAndWeapons(): List<CharacterAndWeapons>

    @Transaction
    @Query("SELECT * FROM Weapon WHERE charOwnerID == :id")
    suspend fun getWeaponListById(id: Int): List<Weapon>

    //@Transaction
    //@Query("SELECT * FROM CharacterWeapons WHERE id == :id")
    //suspend fun getWeaponListByCharacterId(id: Int): List<Weapon>


}
