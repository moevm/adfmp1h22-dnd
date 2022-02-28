package com.dwards.a5edpockethelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.databinding.ActivityMainBinding
import com.dwards.a5edpockethelper.model.AppDatabase
import com.dwards.a5edpockethelper.model.CharacterDAO
import com.dwards.a5edpockethelper.model.Character


class MainActivity : AppCompatActivity() {



    var db: AppDatabase? = DnDPocketHelperApp.getInstance()?.getDatabase()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
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


        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root


        setContentView(view)

        val toolbar = binding.topAppBar
        setSupportActionBar(toolbar)

        viewModel.getCharacter().observe(this, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        binding.topAppBar.setOnLongClickListener{
            val nameClassLevelSettingsDialog: NameClassLevelSettingsDialog = NameClassLevelSettingsDialog()
            nameClassLevelSettingsDialog.show(supportFragmentManager, "NameClassLevelSettingsDialog")
            return@setOnLongClickListener true
        }
    }

    private fun refreshChar(character: Character){
        binding.topAppBar.title = character.name+", "+character.charClass+" "+character.level.toString()
    }

}
