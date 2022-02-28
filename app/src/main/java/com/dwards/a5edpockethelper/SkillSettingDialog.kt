package com.dwards.a5edpockethelper

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.databinding.SkillSettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class SkillSettingDialog : DialogFragment() {

    private var _binding: SkillSettingsDialogBinding? = null
    private val binding get() = _binding!!

    private var prof: Boolean = false
    private var halfProf: Boolean = false
    private var doubleProf: Boolean = false
    private var miscStatBonus: Int = 0
    private var skill: String = "Athletics"

    private val TAG = "MyCustomDialog"
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val mArgs = arguments
            skill = mArgs?.getString("skill")!!
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = SkillSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        loadChar(viewModel.getCharacter().value!!)

        binding.AddProficiencyCheck.setOnClickListener {
            if (binding.AddProficiencyCheck.isChecked && !binding.SecondProficiencyCheck.isChecked) {
                binding.AddProficiencyCheck.isChecked = true
                prof = true
                halfProf = false
                doubleProf = false
                binding.SecondProficiencyCheck.text = resources.getString(R.string.doubleProf)
            } else if (binding.AddProficiencyCheck.isChecked && binding.SecondProficiencyCheck.isChecked && binding.SecondProficiencyCheck.text == resources.getString(R.string.doubleProf)){
                binding.AddProficiencyCheck.isChecked = false
                binding.SecondProficiencyCheck.isChecked = false
                prof = false
                halfProf = false
                doubleProf = false
                binding.SecondProficiencyCheck.text = resources.getString(R.string.halfProf)
            } else if (binding.AddProficiencyCheck.isChecked && binding.SecondProficiencyCheck.isChecked && binding.SecondProficiencyCheck.text == resources.getString(R.string.halfProf)){
                binding.AddProficiencyCheck.isChecked = true
                binding.SecondProficiencyCheck.isChecked = false
                prof = true
                halfProf = false
                doubleProf = false
                binding.SecondProficiencyCheck.text = resources.getString(R.string.doubleProf)
            } else {
                binding.AddProficiencyCheck.isChecked = false
                prof = false
                halfProf = false
                doubleProf = false
                binding.SecondProficiencyCheck.text = resources.getString(R.string.halfProf)
                binding.SecondProficiencyCheck.isChecked = false
            }
            refreshStat()
        }

        binding.SecondProficiencyCheck.setOnClickListener {
            if (binding.SecondProficiencyCheck.isChecked && !binding.AddProficiencyCheck.isChecked) {
                binding.SecondProficiencyCheck.isChecked = true
                prof = false
                halfProf = true
                doubleProf = false
            } else if (binding.SecondProficiencyCheck.isChecked && binding.AddProficiencyCheck.isChecked){
                binding.SecondProficiencyCheck.isChecked = true
                prof = false
                halfProf = false
                doubleProf = true
            }
            else if(!binding.SecondProficiencyCheck.isChecked && binding.AddProficiencyCheck.isChecked){
                binding.SecondProficiencyCheck.isChecked = false
                prof = true
                doubleProf = false
            }
            else if(!binding.SecondProficiencyCheck.isChecked && !binding.AddProficiencyCheck.isChecked){
                binding.SecondProficiencyCheck.isChecked = false
                halfProf = false
            }
            refreshStat()
        }

        binding.MiscBonusValue.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.MiscBonusValue.text.toString() != "") miscStatBonus = binding.MiscBonusValue.text.toString().toInt() else miscStatBonus = 0
                refreshStat()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })


        binding.SaveButton.setOnClickListener {
            viewModel.changeCharactersSkill(skill,miscStatBonus,prof,halfProf,doubleProf)
            dismiss()
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        //val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadChar(character: Character){
        when(skill){
            "Athletics"->{
                binding.SkillNameText.text = resources.getString(R.string.athletic)
                binding.StatText.text = resources.getString(R.string.strength)
                binding.StatValue.setText(viewModel.calcModifier(character.strength))
                miscStatBonus = character.athleticsMiscBonus
                prof = character.athleticsProf
                halfProf = character.athleticsHalfProf
                doubleProf = character.athleticsDoubleProf
            }
            "Acrobatics"->{
                binding.SkillNameText.text = resources.getString(R.string.acrobatics)
                binding.StatText.text = resources.getString(R.string.dexterity)
                binding.StatValue.setText(viewModel.calcModifier(character.dexterity))
                miscStatBonus = character.acrobaticsMiscBonus
                prof = character.acrobaticsProf
                halfProf = character.acrobaticsHalfProf
                doubleProf = character.acrobaticsDoubleProf
            }
            "Sleight of Hand"->{
                binding.SkillNameText.text = resources.getString(R.string.sleightOfHand)
                binding.StatText.text = resources.getString(R.string.dexterity)
                binding.StatValue.setText(viewModel.calcModifier(character.dexterity))
                miscStatBonus = character.sleightOfHandMiscBonus
                prof = character.sleightOfHandProf
                halfProf = character.sleightOfHandHalfProf
                doubleProf = character.sleightOfHandDoubleProf
            }
            "Stealth"->{
                binding.SkillNameText.text = resources.getString(R.string.stealth)
                binding.StatText.text = resources.getString(R.string.dexterity)
                binding.StatValue.setText(viewModel.calcModifier(character.dexterity))
                miscStatBonus = character.stealthMiscBonus
                prof = character.stealthProf
                halfProf = character.stealthHalfProf
                doubleProf = character.stealthDoubleProf
            }
            "Arcana"->{
                binding.SkillNameText.text = resources.getString(R.string.arcana)
                binding.StatText.text = resources.getString(R.string.intelligence)
                binding.StatValue.setText(viewModel.calcModifier(character.intelligence))
                miscStatBonus = character.arcanaMiscBonus
                prof = character.arcanaProf
                halfProf = character.arcanaHalfProf
                doubleProf = character.arcanaDoubleProf
            }
            "History"->{
                binding.SkillNameText.text = resources.getString(R.string.history)
                binding.StatText.text = resources.getString(R.string.intelligence)
                binding.StatValue.setText(viewModel.calcModifier(character.intelligence))
                miscStatBonus = character.historyMiscBonus
                prof = character.historyProf
                halfProf = character.historyHalfProf
                doubleProf = character.historyDoubleProf
            }
            "Investigation"->{
                binding.SkillNameText.text = resources.getString(R.string.investigation)
                binding.StatText.text = resources.getString(R.string.intelligence)
                binding.StatValue.setText(viewModel.calcModifier(character.intelligence))
                miscStatBonus = character.investigationMiscBonus
                prof = character.investigationProf
                halfProf = character.investigationHalfProf
                doubleProf = character.investigationDoubleProf
            }
            "Nature"->{
                binding.SkillNameText.text = resources.getString(R.string.nature)
                binding.StatText.text = resources.getString(R.string.intelligence)
                binding.StatValue.setText(viewModel.calcModifier(character.intelligence))
                miscStatBonus = character.natureMiscBonus
                prof = character.natureProf
                halfProf = character.natureHalfProf
                doubleProf = character.natureDoubleProf
            }
            "Religion"->{
                binding.SkillNameText.text = resources.getString(R.string.religion)
                binding.StatText.text = resources.getString(R.string.intelligence)
                binding.StatValue.setText(viewModel.calcModifier(character.intelligence))
                miscStatBonus = character.religionMiscBonus
                prof = character.religionProf
                halfProf = character.religionHalfProf
                doubleProf = character.religionDoubleProf
            }
            "Animal Handling"->{
                binding.SkillNameText.text = resources.getString(R.string.animalHandling)
                binding.StatText.text = resources.getString(R.string.wisdom)
                binding.StatValue.setText(viewModel.calcModifier(character.wisdom))
                miscStatBonus = character.animalHandlingMiscBonus
                prof = character.animalHandlingProf
                halfProf = character.animalHandlingHalfProf
                doubleProf = character.animalHandlingDoubleProf
            }
            "Insight"->{
                binding.SkillNameText.text = resources.getString(R.string.insight)
                binding.StatText.text = resources.getString(R.string.wisdom)
                binding.StatValue.setText(viewModel.calcModifier(character.wisdom))
                miscStatBonus = character.insightMiscBonus
                prof = character.insightProf
                halfProf = character.insightHalfProf
                doubleProf = character.insightDoubleProf
            }
            "Medicine"->{
                binding.SkillNameText.text = resources.getString(R.string.medicine)
                binding.StatText.text = resources.getString(R.string.wisdom)
                binding.StatValue.setText(viewModel.calcModifier(character.wisdom))
                miscStatBonus = character.medicineMiscBonus
                prof = character.medicineProf
                halfProf = character.medicineHalfProf
                doubleProf = character.medicineDoubleProf
            }
            "Perception"->{
                binding.SkillNameText.text = resources.getString(R.string.perception)
                binding.StatText.text = resources.getString(R.string.wisdom)
                binding.StatValue.setText(viewModel.calcModifier(character.wisdom))
                miscStatBonus = character.perceptionMiscBonus
                prof = character.perceptionProf
                halfProf = character.perceptionHalfProf
                doubleProf = character.perceptionDoubleProf
            }
            "Survival"->{
                binding.SkillNameText.text = resources.getString(R.string.survival)
                binding.StatText.text = resources.getString(R.string.wisdom)
                binding.StatValue.setText(viewModel.calcModifier(character.wisdom))
                miscStatBonus = character.survivalMiscBonus
                prof = character.survivalProf
                halfProf = character.survivalHalfProf
                doubleProf = character.survivalDoubleProf
            }
            "Deception"->{
                binding.SkillNameText.text = resources.getString(R.string.deception)
                binding.StatText.text = resources.getString(R.string.charisma)
                binding.StatValue.setText(viewModel.calcModifier(character.charisma))
                miscStatBonus = character.deceptionMiscBonus
                prof = character.deceptionProf
                halfProf = character.deceptionHalfProf
                doubleProf = character.deceptionDoubleProf
            }
            "Intimidation"->{
                binding.SkillNameText.text = resources.getString(R.string.intimidation)
                binding.StatText.text = resources.getString(R.string.charisma)
                binding.StatValue.setText(viewModel.calcModifier(character.charisma))
                miscStatBonus = character.intimidationMiscBonus
                prof = character.intimidationProf
                halfProf = character.intimidationHalfProf
                doubleProf = character.intimidationDoubleProf
            }
            "Performance"->{
                binding.SkillNameText.text = resources.getString(R.string.performance)
                binding.StatText.text = resources.getString(R.string.charisma)
                binding.StatValue.setText(viewModel.calcModifier(character.charisma))
                miscStatBonus = character.performanceMiscBonus
                prof = character.performanceProf
                halfProf = character.performanceHalfProf
                doubleProf = character.performanceDoubleProf
            }
            "Persuasion"->{
                binding.SkillNameText.text = resources.getString(R.string.persuasion)
                binding.StatText.text = resources.getString(R.string.charisma)
                binding.StatValue.setText(viewModel.calcModifier(character.charisma))
                miscStatBonus = character.persuasionMiscBonus
                prof = character.persuasionProf
                halfProf = character.persuasionHalfProf
                doubleProf = character.persuasionDoubleProf
            }

        }
        binding.MiscBonusValue.setText(miscStatBonus.toString())
        binding.ProfValue.setText(character.proficiency.toString())
        if (prof){
            binding.AddProficiencyCheck.isChecked = true
            binding.SecondProficiencyCheck.text = resources.getString(R.string.doubleProf)
        }
        if (halfProf){
            binding.SecondProficiencyCheck.isChecked = true
            binding.SecondProficiencyCheck.text = resources.getString(R.string.halfProf)
        }
        if (doubleProf){
            binding.SecondProficiencyCheck.isChecked = true
            binding.AddProficiencyCheck.isChecked = true
            binding.SecondProficiencyCheck.text = resources.getString(R.string.doubleProf)
        }
        refreshStat()
    }

    private fun refreshStat(){
        binding.SkillValue.text = (viewModel.calcStat(binding.StatValue.text.toString().toInt(), miscStatBonus,prof,halfProf,doubleProf)).toString()
    }

    private fun refreshChar(character: Character) {
       // binding.ProficiencyBonusValue.setText(character.proficiency.toString())
    }
}
