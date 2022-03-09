package com.dwards.a5edpockethelper

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.dwards.a5edpockethelper.adapters.ViewPageAdapter
import com.dwards.a5edpockethelper.databinding.ActivityMainBinding
import com.dwards.a5edpockethelper.dialogs.NameClassLevelSettingsDialog
import com.dwards.a5edpockethelper.model.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {


    var db: AppDatabase? = DnDPocketHelperApp.getInstance()?.getDatabase()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelFactory: MyViewModelFactory

    private lateinit var navController: NavController

    private lateinit var adapter: ViewPageAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // создание БД
        //var db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "character").build()

        // создание ДАО
        val weaponDao: WeaponDAO = db!!.weaponDao()
        val characterAndWeaponsDao: CharacterAndWeaponsDAO = db!!.characterAndWeaponsDao()
        val characterDao: CharacterDAO = db!!.characterDao()
        var charID: Int = 0

        // создание ViewModel через фабрику
        viewModelFactory = MyViewModelFactory(characterAndWeaponsDao, weaponDao, characterDao, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)
        viewModel.startDB()

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root


        setContentView(view)

        adapter = ViewPageAdapter(this)
        binding.pager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> {
                    val view1: View = layoutInflater.inflate(R.layout.custom_tab, null)
                    view1.findViewById<View>(R.id.icon)
                        .setBackgroundResource(R.drawable.ic_character)
                    tab.customView = view1
                }
                1 -> {
                    val view2: View = layoutInflater.inflate(R.layout.custom_tab, null)
                    view2.findViewById<View>(R.id.icon).setBackgroundResource(R.drawable.ic_skills)
                    tab.customView = view2
                }
                2 -> {
                    val view3: View = layoutInflater.inflate(R.layout.custom_tab, null)
                    view3.findViewById<View>(R.id.icon).setBackgroundResource(R.drawable.ic_weapon)
                    tab.customView = view3
                }
                3 -> {
                    val view4: View = layoutInflater.inflate(R.layout.custom_tab, null)
                    view4.findViewById<View>(R.id.icon).setBackgroundResource(R.drawable.ic_magic)
                    tab.customView = view4
                }
                4 -> {
                    val view5: View = layoutInflater.inflate(R.layout.custom_tab, null)
                    view5.findViewById<View>(R.id.icon).setBackgroundResource(R.drawable.ic_inventory)
                    tab.customView = view5
                }
            }
        }.attach()

        val toolbar = binding.topAppBar


        setSupportActionBar(toolbar)
        viewModel.getCharacter().observe(this, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        binding.topAppBar.setOnLongClickListener {
            val nameClassLevelSettingsDialog: NameClassLevelSettingsDialog =
                NameClassLevelSettingsDialog()
            nameClassLevelSettingsDialog.show(
                supportFragmentManager,
                "NameClassLevelSettingsDialog"
            )
            return@setOnLongClickListener true
        }


    }


    private fun refreshChar(character: Character) {
        binding.topAppBar.title =
            character.name + ", " + character.charClass + " " + character.level.toString()
    }

}








