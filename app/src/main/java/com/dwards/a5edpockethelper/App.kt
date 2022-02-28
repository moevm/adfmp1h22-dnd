package com.dwards.a5edpockethelper

import android.app.Application
import androidx.room.Room
import com.dwards.a5edpockethelper.model.AppDatabase

class App : Application() {
    private var database: AppDatabase? = null
    private var instance: App? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }

    fun getInstance(): App? {
        return instance
    }

    fun getDatabase(): AppDatabase? {
        return database
    }
}