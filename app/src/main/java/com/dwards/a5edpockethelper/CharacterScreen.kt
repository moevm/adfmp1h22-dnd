package com.dwards.a5edpockethelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.databinding.FragmentCharacterScreenBinding
import com.dwards.a5edpockethelper.model.Character
import com.dwards.a5edpockethelper.MyViewModelFactory

class CharacterScreen : Fragment() {
    private val TAG = "MainFragment"

    private var _binding: FragmentCharacterScreenBinding? = null
    private val binding get() = _binding!!
   // private var viewModel = 0;

    /*
    override fun sendStats(statMap: HashMap<String, Int>) {
        change(statMap)
    }
    */

    //!!!!
    //val viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        var viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                //adapter.refreshUsers(it)
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*
        var af: FragmentActivity = requireActivity()
        var viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        */

        _binding = FragmentCharacterScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        /*
        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                //adapter.refreshUsers(it)
            }
        })
        */

        binding.StatsLayout.setOnLongClickListener {

            val StatsSettingsdialog: CharacteristicSettingsDialog = CharacteristicSettingsDialog()
            val args = Bundle()
            //val stats = passageStat()
            //for (i in stats) {
            //    args.putString(i.key, i.value.toString())
            //}
            StatsSettingsdialog.setArguments(args)

            //StatsSettingsdialog.setTargetFragment(this@CharacterScreen, 1);

            StatsSettingsdialog.show(parentFragmentManager, "CharacteristicSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.ProficiencyLayout.setOnLongClickListener {

            val StatsSettingsdialog: ProficiencySettingsDialog = ProficiencySettingsDialog()
            val args = Bundle()
            //val stats = passageStat()
            //for (i in stats){
            //   args.putString(i.key, i.value.toString())}
            //StatsSettingsdialog.setArguments(args)

            //StatsSettingsdialog.setTargetFragment(this@CharacterScreen, 1);

            StatsSettingsdialog.show(parentFragmentManager, "CharacteristicSettingsDialog")
            return@setOnLongClickListener true
        }

        return view
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    /*
    fun passageStat(): HashMap<String, Int> {
        val statsMap: HashMap<String, Int> = hashMapOf()
        statsMap.put("Strength", binding.StrengthScoreValue.text.toString().toInt())
        statsMap.put("Dexterity", binding.DexterityScoreValue.text.toString().toInt())
        statsMap.put("Constitution", binding.ConstitutionScoreValue.text.toString().toInt())
        statsMap.put("Intelligence", binding.IntelligenceScoreValue.text.toString().toInt())
        statsMap.put("Wisdom", binding.WisdomScoreValue.text.toString().toInt())
        statsMap.put("Charisma", binding.CharismaScoreValue.text.toString().toInt())
        return statsMap
    }
    */

    fun refresh(character: MutableLiveData<Character>) {
        binding.StrengthScoreValue.setText("${character.value?.strength}")
        binding.DexterityScoreValue.setText("${character.value?.dexterity}")
        binding.ConstitutionScoreValue.setText("${character.value?.constitution}")
        binding.IntelligenceScoreValue.setText("${character.value?.intelligence}")
        binding.WisdomScoreValue.setText("${character.value?.wisdom}")
        binding.CharismaScoreValue.setText("${character.value?.charisma}")
    }

    /*
    fun change(statMap: HashMap<String, Int>) {
        val strength = statMap.get("Strength")
        val dexterity = statMap.get("Dexterity")
        val constitution = statMap.get("Constitution")
        val intelligence = statMap.get("Intelligence")
        val wisdom = statMap.get("Wisdom")
        val charisma = statMap.get("Charisma")
        binding.StrengthScoreValue.setText("${strength}")
        binding.DexterityScoreValue.setText("${dexterity}")
        binding.ConstitutionScoreValue.setText("${constitution}")
        binding.IntelligenceScoreValue.setText("${intelligence}")
        binding.WisdomScoreValue.setText("${wisdom}")
        binding.CharismaScoreValue.setText("${charisma}")
    }
    */

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


