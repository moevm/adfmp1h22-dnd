package com.dwards.a5edpockethelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.dwards.a5edpockethelper.databinding.ActivityMainBinding
import com.dwards.a5edpockethelper.model.AppDatabase
import com.dwards.a5edpockethelper.model.CharacterDAO


class MainActivity : AppCompatActivity() {



    var db: AppDatabase? = DnDPocketHelperApp.getInstance()?.getDatabase()
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelFactory: MyViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // создание БД
        //var db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "character").build()

        // создание ДАО
        val characterDao: CharacterDAO = db!!.characterDao()
        var charID: Int = 0

        // создание ViewModel через фабрику
        viewModelFactory = MyViewModelFactory(characterDao, charID)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)



        setContentView(R.layout.activity_main)

    }

}
