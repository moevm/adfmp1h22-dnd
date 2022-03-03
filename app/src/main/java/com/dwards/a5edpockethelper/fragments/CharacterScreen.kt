package com.dwards.a5edpockethelper.fragments


import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.databinding.FragmentCharacterScreenBinding
import com.dwards.a5edpockethelper.dialogs.*
import com.dwards.a5edpockethelper.model.Character

class CharacterScreen : Fragment() {
    private val TAG = "MainFragment"

    private var _binding: FragmentCharacterScreenBinding? = null
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
                val characterListDialog = CharacterListDialog()
                characterListDialog.show(parentFragmentManager, "ProficiencySettingsDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //создание вью-модел и обсервера

        val viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]
        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        _binding = FragmentCharacterScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        // редактирование характеристик
        binding.characteristicsBlock.StatsLayout.setOnLongClickListener {
            val statsSettingsDialog = CharacteristicSettingsDialog()

            statsSettingsDialog.show(parentFragmentManager, "CharacteristicSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование бонуса мастерства
        binding.topCharacteristicsBlock.ProficiencyLayout.setOnLongClickListener {

            val proficiencySettingsDialog = ProficiencySettingsDialog()
            proficiencySettingsDialog.show(parentFragmentManager, "ProficiencySettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование скорости
        binding.topCharacteristicsBlock.SpeedLayout.setOnLongClickListener {

            val speedSettingsDialog = SpeedSettingsDialog()
            speedSettingsDialog.show(parentFragmentManager, "SpeedSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование инициативы
        binding.topCharacteristicsBlock.InitiativeLayout.setOnLongClickListener {

            val initiativeSettingsDialog = InitiativeSettingsDialog()
            initiativeSettingsDialog.show(parentFragmentManager, "InitiativeSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование хитдайсов
        binding.mainCharacterInfoBlock.HitDiceLayout.setOnLongClickListener {
            val hitDiceSettingsDialog = HitDiceSettingsDialog()
            hitDiceSettingsDialog.show(parentFragmentManager, "HitDiceSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование максимума здоровья
        binding.mainCharacterInfoBlock.HPLayout.setOnLongClickListener {

            val maxHPSettingsDialog = MaxHPSettingsDialog()
            maxHPSettingsDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование максимума здоровья
        binding.mainCharacterInfoBlock.HPLayout.setOnClickListener {
            val currentHpSettingsDialog = CurrentHpSettingsDialog()
            currentHpSettingsDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
        }

        //редактирование Брони
        binding.mainCharacterInfoBlock.ArmorLayout.setOnLongClickListener {
            val armorSettingsDialog = ArmorSettingsDialog()
            armorSettingsDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование кастом блоков
        binding.customFieldsCharacteristics.CustomTableLayout.setOnLongClickListener {
            val customBlockSettingsDialog = CustomBlockSettingsDialog()
            customBlockSettingsDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
            return@setOnLongClickListener true
        }

        //стабилизировать персонажа
        binding.mainCharacterInfoBlock.DeathsSaveLayout.setOnLongClickListener {
            val stabilizeSettingsDialog = StabilizeSettingsDialog()
            stabilizeSettingsDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
            return@setOnLongClickListener true
        }

        //спасбросок от смерти
        binding.mainCharacterInfoBlock.DeathsSaveLayout.setOnClickListener {
            val deathSavesDialog = DeathSavesDialog()
            deathSavesDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
        }
        return view
    }

    private fun refreshChar(character: Character) {
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        binding.characteristicsBlock.StrengthScoreValue.text = "${character.strength}"
        binding.characteristicsBlock.DexterityScoreValue.text = "${character.dexterity}"
        binding.characteristicsBlock.ConstitutionScoreValue.text = "${character.constitution}"
        binding.characteristicsBlock.IntelligenceScoreValue.text = "${character.intelligence}"
        binding.characteristicsBlock.WisdomScoreValue.text = "${character.wisdom}"
        binding.characteristicsBlock.CharismaScoreValue.text = "${character.charisma}"

        if (character.proficiency > 0) binding.topCharacteristicsBlock.ProficiencyValue.text =
            ("+" + character.proficiency.toString())
        else binding.topCharacteristicsBlock.ProficiencyValue.text =
            (character.proficiency.toString())

        binding.characteristicsBlock.StrengthModifierValue.text =
            viewModel.calcModifier(character.strength)
        binding.characteristicsBlock.DexterityModifierValue.text =
            viewModel.calcModifier(character.dexterity)
        binding.characteristicsBlock.ConstitutionModifierValue.text =
            viewModel.calcModifier(character.constitution)
        binding.characteristicsBlock.IntelligenceModifierValue.text =
            viewModel.calcModifier(character.intelligence)
        binding.characteristicsBlock.WisdomModifierValue.text =
            viewModel.calcModifier(character.wisdom)
        binding.characteristicsBlock.CharismaModifierValue.text =
            viewModel.calcModifier(character.charisma)

        binding.characteristicsBlock.StrengthSaveValue.text = viewModel.calcSave(
            character.strength,
            character.strengthSaveProf,
            character.strengthSaveMisc
        )
        binding.characteristicsBlock.DexteritySaveValue.text = viewModel.calcSave(
            character.dexterity,
            character.dexteritySaveProf,
            character.dexteritySaveMisc
        )
        binding.characteristicsBlock.ConstitutionSaveValue.text = viewModel.calcSave(
            character.constitution,
            character.constitutionSaveProf,
            character.constitutionSaveMisc
        )
        binding.characteristicsBlock.IntelligenceSaveValue.text = viewModel.calcSave(
            character.intelligence,
            character.intelligenceSaveProf,
            character.intelligenceSaveMisc
        )
        binding.characteristicsBlock.WisdomSaveValue.text =
            viewModel.calcSave(character.wisdom, character.wisdomSaveProf, character.wisdomSaveMisc)
        binding.characteristicsBlock.CharismaSaveValue.text = viewModel.calcSave(
            character.charisma,
            character.charismaSaveProf,
            character.charismaSaveMisc
        )

        binding.mainCharacterInfoBlock.HitDiceValue.text =
            (character.hitDiceCount.toString() + "d" + character.hitDiceSize.toString())
        binding.mainCharacterInfoBlock.HPValue.text =
            (character.currentHP + character.tempHP).toString()
        if (character.tempHP > 0)
            binding.mainCharacterInfoBlock.HPValue.setTextColor(Color.parseColor("#2f00ba"))
        else
            binding.mainCharacterInfoBlock.HPValue.setTextColor(Color.parseColor("#000000"))

        when (character.chosenSpeed) {
            "Walk" -> {
                binding.topCharacteristicsBlock.SpeedValue.text =
                    (character.baseWalkSpeed + character.miscWalkSpeedBonus).toString()
                binding.topCharacteristicsBlock.SpeedText.text = "Speed"
            }
            "Fly" -> {
                binding.topCharacteristicsBlock.SpeedValue.text =
                    (character.baseFlySpeed + character.miscFlySpeedBonus).toString()
                binding.topCharacteristicsBlock.SpeedText.text = character.chosenSpeed
            }
            "Swim" -> {
                binding.topCharacteristicsBlock.SpeedValue.text =
                    (character.baseSwimSpeed + character.miscSwimSpeedBonus).toString()
                binding.topCharacteristicsBlock.SpeedText.text = character.chosenSpeed
            }
            "Climb" -> {
                binding.topCharacteristicsBlock.SpeedValue.text =
                    (character.baseClimbSpeed + character.miscClimbSpeedBonus).toString()
                binding.topCharacteristicsBlock.SpeedText.text = character.chosenSpeed
            }
        }

        binding.topCharacteristicsBlock.InitiativeValue.text = viewModel.calcInitiative(
            viewModel.calcModifier(
                character.dexterity
            ).toInt(),
            character.miscInitiativeBonus,
            character.initiativeProf,
            character.initiativeHalfProf,
            character.initiativeDoubleProf,
            character.initiativeAdditionalAbility,
            character.proficiency
        ).toString()

        binding.mainCharacterInfoBlock.ArmorValue.text = viewModel.calcArmor(
            character.armorBonus,
            character.shieldBonus,
            character.maxDexterityBonus,
            character.miscArmorBonus,
            character.armorType,
            character.statBonusArmor
        ).toString()

        binding.customFieldsCharacteristics.CustomBlock1Text.text = character.customBlock1Name
        binding.customFieldsCharacteristics.CustomBlock1ScoreValue.text =
            character.customBlock1Value
        binding.customFieldsCharacteristics.CustomBlock2Text.text = character.customBlock2Name
        binding.customFieldsCharacteristics.CustomBlock2ScoreValue.text =
            character.customBlock2Value
        binding.customFieldsCharacteristics.CustomBlock3Text.text = character.customBlock3Name
        binding.customFieldsCharacteristics.CustomBlock3ScoreValue.text =
            character.customBlock3Value
        binding.customFieldsCharacteristics.CustomBlock4Text.text = character.customBlock4Name
        binding.customFieldsCharacteristics.CustomBlock4ScoreValue.text =
            character.customBlock4Value

        if (character.currentHP <= 0) {
            binding.mainCharacterInfoBlock.HPLayout.visibility = View.INVISIBLE
            binding.mainCharacterInfoBlock.DeathsSaveLayout.visibility = View.VISIBLE
        } else {
            binding.mainCharacterInfoBlock.HPLayout.visibility = View.VISIBLE
            binding.mainCharacterInfoBlock.DeathsSaveLayout.visibility = View.INVISIBLE
        }

        when (character.passDeathSave) {
            0 -> {
                binding.mainCharacterInfoBlock.Check1.setImageResource(R.drawable.circle)
                binding.mainCharacterInfoBlock.Check2.setImageResource(R.drawable.circle)
                binding.mainCharacterInfoBlock.Check3.setImageResource(R.drawable.circle)
            }
            1 -> {
                binding.mainCharacterInfoBlock.Check1.setImageResource(R.drawable.check_mark)
                binding.mainCharacterInfoBlock.Check2.setImageResource(R.drawable.circle)
                binding.mainCharacterInfoBlock.Check3.setImageResource(R.drawable.circle)
            }
            2 -> {
                binding.mainCharacterInfoBlock.Check1.setImageResource(R.drawable.check_mark)
                binding.mainCharacterInfoBlock.Check2.setImageResource(R.drawable.check_mark)
                binding.mainCharacterInfoBlock.Check3.setImageResource(R.drawable.circle)
            }
            3 -> {
                binding.mainCharacterInfoBlock.Check1.setImageResource(R.drawable.check_mark)
                binding.mainCharacterInfoBlock.Check2.setImageResource(R.drawable.check_mark)
                binding.mainCharacterInfoBlock.Check3.setImageResource(R.drawable.check_mark)
            }
        }

        when (character.failureDeathSave) {
            0 -> {
                binding.mainCharacterInfoBlock.Cross1.setImageResource(R.drawable.circle)
                binding.mainCharacterInfoBlock.Cross2.setImageResource(R.drawable.circle)
                binding.mainCharacterInfoBlock.Cross3.setImageResource(R.drawable.circle)
            }
            1 -> {
                binding.mainCharacterInfoBlock.Cross1.setImageResource(R.drawable.cross_mark)
                binding.mainCharacterInfoBlock.Cross2.setImageResource(R.drawable.circle)
                binding.mainCharacterInfoBlock.Cross3.setImageResource(R.drawable.circle)
            }
            2 -> {
                binding.mainCharacterInfoBlock.Cross1.setImageResource(R.drawable.cross_mark)
                binding.mainCharacterInfoBlock.Cross2.setImageResource(R.drawable.cross_mark)
                binding.mainCharacterInfoBlock.Cross3.setImageResource(R.drawable.circle)
            }
            3 -> {
                binding.mainCharacterInfoBlock.Cross1.setImageResource(R.drawable.cross_mark)
                binding.mainCharacterInfoBlock.Cross2.setImageResource(R.drawable.cross_mark)
                binding.mainCharacterInfoBlock.Cross3.setImageResource(R.drawable.cross_mark)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


