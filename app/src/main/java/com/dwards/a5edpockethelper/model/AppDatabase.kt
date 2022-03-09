package com.dwards.a5edpockethelper.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Character::class, Weapon::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDAO
    abstract fun weaponDao(): WeaponDAO
    abstract fun characterAndWeaponsDao(): CharacterAndWeaponsDAO
}
