package com.dwards.a5edpockethelper

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dwards.a5edpockethelper.model.AppDatabase
import com.dwards.a5edpockethelper.model.Weapon
import com.dwards.a5edpockethelper.model.WeaponDAO
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var weaponDAO: WeaponDAO
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        weaponDAO = db.weaponDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val weapon = Weapon(id = 100, name = "Test")
        runBlocking { weaponDAO.insertWeapon(weapon) }
        var byId: Weapon? = null
        runBlocking {
            byId = weaponDAO.getById(100)
        }
        assertThat(byId, equalTo(weapon))
    }
}
