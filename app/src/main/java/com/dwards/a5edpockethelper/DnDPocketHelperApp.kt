package com.dwards.a5edpockethelper

import android.app.Application
import androidx.room.Room
import com.dwards.a5edpockethelper.model.AppDatabase

class DnDPocketHelperApp : Application() {
    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }

    fun getDatabase(): AppDatabase? {
        return database
    }

    companion object {
        private var instance: DnDPocketHelperApp? = null
        fun getInstance(): DnDPocketHelperApp? {
            return instance
        }
    }
}