package com.dwards.a5edpockethelper


import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.databinding.FragmentCharacterSkillsBinding
import com.dwards.a5edpockethelper.model.Character


class CharacterSkills : Fragment() {
    private val TAG = "MainFragment"

    private var _binding: FragmentCharacterSkillsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.characterList -> {
                var characterListDialog: CharacterList = CharacterList()
                characterListDialog.show(parentFragmentManager, "ProficiencySettingsDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //создание вью-модел и обсервера

        val viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })




        _binding = FragmentCharacterSkillsBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.AthleticsLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Athletics")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.AcrobaticsLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Acrobatics")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.SleightofHandLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Sleight of Hand")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.StealthLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Stealth")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.ArcanaLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Arcana")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.HistoryLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "History")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.InvestigationLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Investigation")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.NatureLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Nature")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.ReligionLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Religion")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.AnimalHandlingLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Animal Handling")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.InsightLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Insight")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.MedicineLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Medicine")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.PerceptionLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Perception")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.SurvivalLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Survival")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.DeceptionLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Deception")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.IntimidationLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Intimidation")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.PerformanceLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Performance")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.PersuasionLayout.setOnLongClickListener{
            val skillSettingDialog: SkillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Persuasion")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.ToolsProficiencyLayout.setOnClickListener {
            val toolsProficiencyListDialog: ToolsProficiencyList = ToolsProficiencyList()
            toolsProficiencyListDialog.show(parentFragmentManager, "ProficiencySettingsDialog")
        }

        return view
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




    private fun refreshChar(character: Character) {
        val viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        binding.StrengthModifier.text = viewModel.calcModifier(character.strength)
        binding.DexterityModifier.text = viewModel.calcModifier(character.dexterity)
        binding.IntelligenceModifier.text = viewModel.calcModifier(character.intelligence)
        binding.WisdomModifier.text = viewModel.calcModifier(character.wisdom)
        binding.CharismaModifier.text = viewModel.calcModifier(character.charisma)

        binding.AthleticsModifier.text = viewModel.calcStat(viewModel.calcModifier(character.strength).toInt(),character.athleticsMiscBonus, character.athleticsProf,
            character.athleticsHalfProf, character.athleticsDoubleProf).toString()
        when {
            character.athleticsProf -> binding.AthleticsLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.athleticsHalfProf -> binding.AthleticsLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.athleticsDoubleProf -> binding.AthleticsLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.AthleticsLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.AcrobaticsModifier.text = viewModel.calcStat(viewModel.calcModifier(character.dexterity).toInt(),character.acrobaticsMiscBonus, character.acrobaticsProf,
            character.acrobaticsHalfProf, character.acrobaticsDoubleProf).toString()
        when {
            character.acrobaticsProf -> binding.AcrobaticsLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.acrobaticsHalfProf -> binding.AcrobaticsLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.acrobaticsDoubleProf -> binding.AcrobaticsLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.AcrobaticsLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.SleightofHandModifier.text = viewModel.calcStat(viewModel.calcModifier(character.dexterity).toInt(),character.sleightOfHandMiscBonus, character.sleightOfHandProf,
            character.sleightOfHandHalfProf, character.sleightOfHandDoubleProf).toString()
        when {
            character.sleightOfHandProf -> binding.SleightofHandLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.sleightOfHandHalfProf -> binding.SleightofHandLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.sleightOfHandDoubleProf -> binding.SleightofHandLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.SleightofHandLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.StealthModifier.text = viewModel.calcStat(viewModel.calcModifier(character.dexterity).toInt(),character.stealthMiscBonus, character.stealthProf,
            character.stealthHalfProf, character.stealthDoubleProf).toString()
        when {
            character.stealthProf -> binding.StealthLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.stealthHalfProf -> binding.StealthLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.stealthDoubleProf -> binding.StealthLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.StealthLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.ArcanaModifier.text = viewModel.calcStat(viewModel.calcModifier(character.intelligence).toInt(),character.arcanaMiscBonus, character.arcanaProf,
            character.arcanaHalfProf, character.arcanaDoubleProf).toString()
        when {
            character.arcanaProf -> binding.ArcanaLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.arcanaHalfProf -> binding.ArcanaLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.arcanaDoubleProf -> binding.ArcanaLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.ArcanaLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.HistoryModifier.text = viewModel.calcStat(viewModel.calcModifier(character.intelligence).toInt(),character.historyMiscBonus, character.historyProf,
            character.historyHalfProf, character.historyDoubleProf).toString()
        when {
            character.historyProf -> binding.HistoryLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.historyHalfProf -> binding.HistoryLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.historyDoubleProf -> binding.HistoryLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.HistoryLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.InvestigationModifier.text = viewModel.calcStat(viewModel.calcModifier(character.intelligence).toInt(),character.investigationMiscBonus, character.investigationProf,
            character.investigationHalfProf, character.investigationDoubleProf).toString()
        when {
            character.investigationProf -> binding.InvestigationLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.investigationHalfProf -> binding.InvestigationLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.investigationDoubleProf -> binding.InvestigationLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.InvestigationLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.NatureModifier.text = viewModel.calcStat(viewModel.calcModifier(character.intelligence).toInt(),character.natureMiscBonus, character.natureProf,
            character.natureHalfProf, character.natureDoubleProf).toString()
        when {
            character.natureProf -> binding.NatureLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.natureHalfProf -> binding.NatureLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.natureDoubleProf -> binding.NatureLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.NatureLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.ReligionModifier.text = viewModel.calcStat(viewModel.calcModifier(character.intelligence).toInt(),character.religionMiscBonus, character.religionProf,
            character.religionHalfProf, character.religionDoubleProf).toString()
        when {
            character.religionProf -> binding.ReligionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.religionHalfProf -> binding.ReligionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.religionDoubleProf -> binding.ReligionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.ReligionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.AnimalHandlingModifier.text = viewModel.calcStat(viewModel.calcModifier(character.wisdom).toInt(),character.animalHandlingMiscBonus, character.animalHandlingProf,
            character.animalHandlingHalfProf, character.animalHandlingDoubleProf).toString()
        when {
            character.animalHandlingProf -> binding.AnimalHandlingLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.animalHandlingHalfProf -> binding.AnimalHandlingLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.animalHandlingDoubleProf -> binding.AnimalHandlingLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.AnimalHandlingLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.InsightModifier.text = viewModel.calcStat(viewModel.calcModifier(character.wisdom).toInt(),character.insightMiscBonus, character.insightProf,
            character.insightHalfProf, character.insightDoubleProf).toString()
        when {
            character.insightProf -> binding.InsightLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.insightHalfProf -> binding.InsightLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.insightDoubleProf -> binding.InsightLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.InsightLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.MedicineModifier.text = viewModel.calcStat(viewModel.calcModifier(character.wisdom).toInt(),character.medicineMiscBonus, character.medicineProf,
            character.medicineHalfProf, character.medicineDoubleProf).toString()
        when {
            character.medicineProf -> binding.MedicineLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.medicineHalfProf -> binding.MedicineLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.medicineDoubleProf -> binding.MedicineLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.MedicineLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.PerceptionModifier.text = viewModel.calcStat(viewModel.calcModifier(character.wisdom).toInt(),character.perceptionMiscBonus, character.perceptionProf,
            character.perceptionHalfProf, character.perceptionDoubleProf).toString()
        when {
            character.perceptionProf -> binding.PerceptionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.perceptionHalfProf -> binding.PerceptionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.perceptionDoubleProf -> binding.PerceptionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.PerceptionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.SurvivalModifier.text = viewModel.calcStat(viewModel.calcModifier(character.wisdom).toInt(),character.survivalMiscBonus, character.survivalProf,
            character.survivalHalfProf, character.survivalDoubleProf).toString()
        when {
            character.survivalProf -> binding.SurvivalLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.survivalHalfProf -> binding.SurvivalLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.survivalDoubleProf -> binding.SurvivalLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.SurvivalLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.DeceptionModifier.text = viewModel.calcStat(viewModel.calcModifier(character.charisma).toInt(),character.deceptionMiscBonus, character.deceptionProf,
            character.deceptionHalfProf, character.deceptionDoubleProf).toString()
        when {
            character.deceptionProf -> binding.DeceptionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.deceptionHalfProf -> binding.DeceptionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.deceptionDoubleProf -> binding.DeceptionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.DeceptionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.IntimidationModifier.text = viewModel.calcStat(viewModel.calcModifier(character.charisma).toInt(),character.intimidationMiscBonus, character.intimidationProf,
            character.intimidationHalfProf, character.intimidationDoubleProf).toString()
        when {
            character.intimidationProf -> binding.IntimidationLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.intimidationHalfProf -> binding.IntimidationLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.intimidationDoubleProf -> binding.IntimidationLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.IntimidationLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.PerformanceModifier.text = viewModel.calcStat(viewModel.calcModifier(character.charisma).toInt(),character.performanceMiscBonus, character.performanceProf,
            character.performanceHalfProf, character.performanceDoubleProf).toString()
        when {
            character.performanceProf -> binding.PerformanceLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.performanceHalfProf -> binding.PerformanceLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.performanceDoubleProf -> binding.PerformanceLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.PerformanceLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
        binding.PersuasionModifier.text = viewModel.calcStat(viewModel.calcModifier(character.charisma).toInt(),character.persuasionMiscBonus, character.persuasionProf,
            character.persuasionHalfProf, character.persuasionDoubleProf).toString()
        when {
            character.persuasionProf -> binding.PersuasionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_prof))
            character.persuasionHalfProf -> binding.PersuasionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_half_prof))
            character.persuasionDoubleProf -> binding.PersuasionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.skill_double_prof))
            else -> binding.PersuasionLayout.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.block_grey))
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


