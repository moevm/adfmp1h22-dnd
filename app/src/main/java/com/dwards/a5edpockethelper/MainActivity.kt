package com.dwards.a5edpockethelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.dwards.a5edpockethelper.databinding.ActivityMainBinding
import com.dwards.a5edpockethelper.model.AppDatabase


class MainActivity : AppCompatActivity() {


    //var db: AppDatabase? = DnDPocketHelperApp.getInstance()?.getDatabase()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "character"
        ).build()

        setContentView(R.layout.activity_main)

    }

}
