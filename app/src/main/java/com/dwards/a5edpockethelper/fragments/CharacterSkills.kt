package com.dwards.a5edpockethelper.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.databinding.FragmentCharacterSkillsBinding
import com.dwards.a5edpockethelper.dialogs.CharacterListDialog
import com.dwards.a5edpockethelper.dialogs.LanguageProficiencyList
import com.dwards.a5edpockethelper.dialogs.SkillSettingDialog
import com.dwards.a5edpockethelper.dialogs.ToolsProficiencyList
import com.dwards.a5edpockethelper.model.Character

class CharacterSkills : Fragment() {

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
                CharacterListDialog().show(parentFragmentManager, "ProficiencySettingsDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        _binding = FragmentCharacterSkillsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.strengthBlock.AthleticsLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Athletics")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.dexterityBlock.AcrobaticsLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Acrobatics")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.dexterityBlock.SleightofHandLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Sleight of Hand")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.dexterityBlock.StealthLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Stealth")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.intelligenceBlock.ArcanaLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Arcana")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.intelligenceBlock.HistoryLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "History")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.intelligenceBlock.InvestigationLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Investigation")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.intelligenceBlock.NatureLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Nature")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.intelligenceBlock.ReligionLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Religion")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.wisdomBlock.AnimalHandlingLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Animal Handling")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.wisdomBlock.InsightLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Insight")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.wisdomBlock.MedicineLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Medicine")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.wisdomBlock.PerceptionLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Perception")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.wisdomBlock.SurvivalLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Survival")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.charismaBlock.DeceptionLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Deception")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.charismaBlock.IntimidationLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Intimidation")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.charismaBlock.PerformanceLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Performance")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.charismaBlock.PersuasionLayout.setOnLongClickListener {
            val skillSettingDialog = SkillSettingDialog()
            val args = Bundle()
            args.putString("skill", "Persuasion")
            skillSettingDialog.arguments = args
            skillSettingDialog.show(parentFragmentManager, "StatSettingsDialog")
            return@setOnLongClickListener true
        }

        binding.secondScreenBtn.ToolsProficiencyLayout.setOnClickListener {
            val toolsProficiencyListDialog = ToolsProficiencyList()
            toolsProficiencyListDialog.show(parentFragmentManager, "ProficiencySettingsDialog")
        }

        binding.secondScreenBtn.LanguageProficiencyLayout.setOnClickListener {
            val languageProficiencyListDialog = LanguageProficiencyList()
            languageProficiencyListDialog.show(parentFragmentManager, "LanguageSettingsDialog")
        }

        return view
    }

    private fun refreshChar(character: Character) {
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        binding.strengthBlock.StrengthModifier.text = viewModel.calcModifier(character.strength)
        binding.dexterityBlock.DexterityModifier.text = viewModel.calcModifier(character.dexterity)
        binding.intelligenceBlock.IntelligenceModifier.text =
            viewModel.calcModifier(character.intelligence)
        binding.wisdomBlock.WisdomModifier.text = viewModel.calcModifier(character.wisdom)
        binding.charismaBlock.CharismaModifier.text = viewModel.calcModifier(character.charisma)

        binding.strengthBlock.AthleticsModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.strength).toInt(),
            character.athleticsMiscBonus,
            character.athleticsProf,
            character.athleticsHalfProf,
            character.athleticsDoubleProf
        ).toString()
        when {
            character.athleticsProf -> binding.strengthBlock.AthleticsLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.athleticsHalfProf -> binding.strengthBlock.AthleticsLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.athleticsDoubleProf -> binding.strengthBlock.AthleticsLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.strengthBlock.AthleticsLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.dexterityBlock.AcrobaticsModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.dexterity).toInt(),
            character.acrobaticsMiscBonus,
            character.acrobaticsProf,
            character.acrobaticsHalfProf,
            character.acrobaticsDoubleProf
        ).toString()
        when {
            character.acrobaticsProf -> binding.dexterityBlock.AcrobaticsLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.acrobaticsHalfProf -> binding.dexterityBlock.AcrobaticsLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.acrobaticsDoubleProf -> binding.dexterityBlock.AcrobaticsLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.dexterityBlock.AcrobaticsLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.dexterityBlock.SleightofHandModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.dexterity).toInt(),
            character.sleightOfHandMiscBonus,
            character.sleightOfHandProf,
            character.sleightOfHandHalfProf,
            character.sleightOfHandDoubleProf
        ).toString()
        when {
            character.sleightOfHandProf -> binding.dexterityBlock.SleightofHandLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.sleightOfHandHalfProf -> binding.dexterityBlock.SleightofHandLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.sleightOfHandDoubleProf -> binding.dexterityBlock.SleightofHandLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.dexterityBlock.SleightofHandLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.dexterityBlock.StealthModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.dexterity).toInt(),
            character.stealthMiscBonus,
            character.stealthProf,
            character.stealthHalfProf,
            character.stealthDoubleProf
        ).toString()
        when {
            character.stealthProf -> binding.dexterityBlock.StealthLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.stealthHalfProf -> binding.dexterityBlock.StealthLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.stealthDoubleProf -> binding.dexterityBlock.StealthLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.dexterityBlock.StealthLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.intelligenceBlock.ArcanaModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.intelligence).toInt(),
            character.arcanaMiscBonus,
            character.arcanaProf,
            character.arcanaHalfProf,
            character.arcanaDoubleProf
        ).toString()
        when {
            character.arcanaProf -> binding.intelligenceBlock.ArcanaLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.arcanaHalfProf -> binding.intelligenceBlock.ArcanaLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.arcanaDoubleProf -> binding.intelligenceBlock.ArcanaLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.intelligenceBlock.ArcanaLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.intelligenceBlock.HistoryModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.intelligence).toInt(),
            character.historyMiscBonus,
            character.historyProf,
            character.historyHalfProf,
            character.historyDoubleProf
        ).toString()
        when {
            character.historyProf -> binding.intelligenceBlock.HistoryLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.historyHalfProf -> binding.intelligenceBlock.HistoryLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.historyDoubleProf -> binding.intelligenceBlock.HistoryLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.intelligenceBlock.HistoryLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.intelligenceBlock.InvestigationModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.intelligence).toInt(),
            character.investigationMiscBonus,
            character.investigationProf,
            character.investigationHalfProf,
            character.investigationDoubleProf
        ).toString()
        when {
            character.investigationProf -> binding.intelligenceBlock.InvestigationLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.investigationHalfProf -> binding.intelligenceBlock.InvestigationLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.investigationDoubleProf -> binding.intelligenceBlock.InvestigationLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.intelligenceBlock.InvestigationLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.intelligenceBlock.NatureModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.intelligence).toInt(),
            character.natureMiscBonus,
            character.natureProf,
            character.natureHalfProf,
            character.natureDoubleProf
        ).toString()
        when {
            character.natureProf -> binding.intelligenceBlock.NatureLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.natureHalfProf -> binding.intelligenceBlock.NatureLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.natureDoubleProf -> binding.intelligenceBlock.NatureLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.intelligenceBlock.NatureLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.intelligenceBlock.ReligionModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.intelligence).toInt(),
            character.religionMiscBonus,
            character.religionProf,
            character.religionHalfProf,
            character.religionDoubleProf
        ).toString()
        when {
            character.religionProf -> binding.intelligenceBlock.ReligionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.religionHalfProf -> binding.intelligenceBlock.ReligionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.religionDoubleProf -> binding.intelligenceBlock.ReligionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.intelligenceBlock.ReligionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.wisdomBlock.AnimalHandlingModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.wisdom).toInt(),
            character.animalHandlingMiscBonus,
            character.animalHandlingProf,
            character.animalHandlingHalfProf,
            character.animalHandlingDoubleProf
        ).toString()
        when {
            character.animalHandlingProf -> binding.wisdomBlock.AnimalHandlingLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.animalHandlingHalfProf -> binding.wisdomBlock.AnimalHandlingLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.animalHandlingDoubleProf -> binding.wisdomBlock.AnimalHandlingLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.wisdomBlock.AnimalHandlingLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.wisdomBlock.InsightModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.wisdom).toInt(),
            character.insightMiscBonus,
            character.insightProf,
            character.insightHalfProf,
            character.insightDoubleProf
        ).toString()
        when {
            character.insightProf -> binding.wisdomBlock.InsightLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.insightHalfProf -> binding.wisdomBlock.InsightLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.insightDoubleProf -> binding.wisdomBlock.InsightLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.wisdomBlock.InsightLayout.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.block_grey
                )
            )
        }
        binding.wisdomBlock.MedicineModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.wisdom).toInt(),
            character.medicineMiscBonus,
            character.medicineProf,
            character.medicineHalfProf,
            character.medicineDoubleProf
        ).toString()
        when {
            character.medicineProf -> binding.wisdomBlock.MedicineLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.medicineHalfProf -> binding.wisdomBlock.MedicineLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.medicineDoubleProf -> binding.wisdomBlock.MedicineLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.wisdomBlock.MedicineLayout.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.block_grey
                )
            )
        }
        binding.wisdomBlock.PerceptionModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.wisdom).toInt(),
            character.perceptionMiscBonus,
            character.perceptionProf,
            character.perceptionHalfProf,
            character.perceptionDoubleProf
        ).toString()
        when {
            character.perceptionProf -> binding.wisdomBlock.PerceptionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.perceptionHalfProf -> binding.wisdomBlock.PerceptionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.perceptionDoubleProf -> binding.wisdomBlock.PerceptionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.wisdomBlock.PerceptionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.wisdomBlock.SurvivalModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.wisdom).toInt(),
            character.survivalMiscBonus,
            character.survivalProf,
            character.survivalHalfProf,
            character.survivalDoubleProf
        ).toString()
        when {
            character.survivalProf -> binding.wisdomBlock.SurvivalLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.survivalHalfProf -> binding.wisdomBlock.SurvivalLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.survivalDoubleProf -> binding.wisdomBlock.SurvivalLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.wisdomBlock.SurvivalLayout.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.block_grey
                )
            )
        }
        binding.charismaBlock.DeceptionModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.charisma).toInt(),
            character.deceptionMiscBonus,
            character.deceptionProf,
            character.deceptionHalfProf,
            character.deceptionDoubleProf
        ).toString()
        when {
            character.deceptionProf -> binding.charismaBlock.DeceptionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.deceptionHalfProf -> binding.charismaBlock.DeceptionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.deceptionDoubleProf -> binding.charismaBlock.DeceptionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.charismaBlock.DeceptionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.charismaBlock.IntimidationModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.charisma).toInt(),
            character.intimidationMiscBonus,
            character.intimidationProf,
            character.intimidationHalfProf,
            character.intimidationDoubleProf
        ).toString()
        when {
            character.intimidationProf -> binding.charismaBlock.IntimidationLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.intimidationHalfProf -> binding.charismaBlock.IntimidationLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.intimidationDoubleProf -> binding.charismaBlock.IntimidationLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.charismaBlock.IntimidationLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.charismaBlock.PerformanceModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.charisma).toInt(),
            character.performanceMiscBonus,
            character.performanceProf,
            character.performanceHalfProf,
            character.performanceDoubleProf
        ).toString()
        when {
            character.performanceProf -> binding.charismaBlock.PerformanceLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.performanceHalfProf -> binding.charismaBlock.PerformanceLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.performanceDoubleProf -> binding.charismaBlock.PerformanceLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.charismaBlock.PerformanceLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
        binding.charismaBlock.PersuasionModifier.text = viewModel.calcStat(
            viewModel.calcModifier(character.charisma).toInt(),
            character.persuasionMiscBonus,
            character.persuasionProf,
            character.persuasionHalfProf,
            character.persuasionDoubleProf
        ).toString()
        when {
            character.persuasionProf -> binding.charismaBlock.PersuasionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_prof
                    )
                )
            character.persuasionHalfProf -> binding.charismaBlock.PersuasionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_half_prof
                    )
                )
            character.persuasionDoubleProf -> binding.charismaBlock.PersuasionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.skill_double_prof
                    )
                )
            else -> binding.charismaBlock.PersuasionLayout.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.block_grey
                    )
                )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


