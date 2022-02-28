package com.dwards.a5edpockethelper


import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.dwards.a5edpockethelper.databinding.FragmentCharacterScreenBinding
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




        _binding = FragmentCharacterScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        // редактирование характеристик
        binding.StatsLayout.setOnLongClickListener {
                //ТЕСТИЛ НАВИГАЦИЮ
                //view : View -> view.findNavController().navigate(R.id.action_characterScreen_to_characterSkills)
            val statsSettingsDialog: CharacteristicSettingsDialog = CharacteristicSettingsDialog()

            statsSettingsDialog.show(parentFragmentManager, "CharacteristicSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование бонуса мастерства
        binding.ProficiencyLayout.setOnLongClickListener {

            val proficiencySettingsDialog: ProficiencySettingsDialog = ProficiencySettingsDialog()
            proficiencySettingsDialog.show(parentFragmentManager, "ProficiencySettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование скорости
        binding.SpeedLayout.setOnLongClickListener {

            val speedSettingsDialog: SpeedSettingsDialog = SpeedSettingsDialog()
            speedSettingsDialog.show(parentFragmentManager, "SpeedSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование инициативы
        binding.InitiativeLayout.setOnLongClickListener {

            val initiativeSettingsDialog: InitiativeSettingsDialog = InitiativeSettingsDialog()
            initiativeSettingsDialog.show(parentFragmentManager, "InitiativeSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование хитдайсов
        binding.HitDiceLayout.setOnLongClickListener {

            val hitDiceSettingsDialog: HitDiceSettingsDialog = HitDiceSettingsDialog()
            hitDiceSettingsDialog.show(parentFragmentManager, "HitDiceSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование максимума здоровья
        binding.HPLayout.setOnLongClickListener {

            val maxHPSettingsDialog: MaxHPSettingsDialog = MaxHPSettingsDialog()
            maxHPSettingsDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование максимума здоровья
        binding.HPLayout.setOnClickListener {
            val currentHpSettingsDialog: CurrentHpSettingsDialog = CurrentHpSettingsDialog()
            currentHpSettingsDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
        }

        //редактирование Брони
        binding.ArmorLayout.setOnLongClickListener {
            val armorSettingsDialog: ArmorSettingsDialog = ArmorSettingsDialog()
            armorSettingsDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
            return@setOnLongClickListener true
        }

        //редактирование кастом блоков
        binding.CustomTableLayout.setOnLongClickListener {
            val customBlockSettingsDialog: CustomBlockSettingsDialog = CustomBlockSettingsDialog()
            customBlockSettingsDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
            return@setOnLongClickListener true
        }

        //стабилизировать персонажа
        binding.DeathsSaveLayout.setOnLongClickListener {
            val stabilizeSettingsDialog: StabilizeSettingsDialog = StabilizeSettingsDialog()
            stabilizeSettingsDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
            return@setOnLongClickListener true
        }

        //спасбросок от смерти
        binding.DeathsSaveLayout.setOnClickListener {
            val deathSavesDialog: DeathSavesDialog = DeathSavesDialog()
            deathSavesDialog.show(parentFragmentManager, "MaxHPSettingsDialog")
        }

        return view
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




    private fun refreshChar(character: Character) {
        val viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        binding.StrengthScoreValue.text = "${character.strength}"
        binding.DexterityScoreValue.text = "${character.dexterity}"
        binding.ConstitutionScoreValue.text = "${character.constitution}"
        binding.IntelligenceScoreValue.text = "${character.intelligence}"
        binding.WisdomScoreValue.text = "${character.wisdom}"
        binding.CharismaScoreValue.text = "${character.charisma}"

            if (character.proficiency > 0) binding.ProficiencyValue.text = ("+" + character.proficiency.toString())
            else binding.ProficiencyValue.text = (character.proficiency.toString())



        binding.StrengthModifierValue.text = viewModel.calcModifier(character.strength)
        binding.DexterityModifierValue.text = viewModel.calcModifier(character.dexterity)
        binding.ConstitutionModifierValue.text = viewModel.calcModifier(character.constitution)
        binding.IntelligenceModifierValue.text = viewModel.calcModifier(character.intelligence)
        binding.WisdomModifierValue.text = viewModel.calcModifier(character.wisdom)
        binding.CharismaModifierValue.text = viewModel.calcModifier(character.charisma)

        binding.StrengthSaveValue.text = viewModel.calcSave(character.strength, character.strengthSaveProf, character.strengthSaveMisc)
        binding.DexteritySaveValue.text = viewModel.calcSave(character.dexterity, character.dexteritySaveProf, character.dexteritySaveMisc)
        binding.ConstitutionSaveValue.text = viewModel.calcSave(character.constitution, character.constitutionSaveProf, character.constitutionSaveMisc)
        binding.IntelligenceSaveValue.text = viewModel.calcSave(character.intelligence, character.intelligenceSaveProf, character.intelligenceSaveMisc)
        binding.WisdomSaveValue.text = viewModel.calcSave(character.wisdom, character.wisdomSaveProf, character.wisdomSaveMisc)
        binding.CharismaSaveValue.text = viewModel.calcSave(character.charisma, character.charismaSaveProf, character.charismaSaveMisc)

        binding.HitDiceValue.text = (character.hitDiceCount.toString() + "d" + character.hitDiceSize.toString())
        binding.HPValue.text = (character.currentHP+character.tempHP).toString()
        if(character.tempHP > 0)
            binding.HPValue.setTextColor(Color.parseColor("#2f00ba"))
        else
            binding.HPValue.setTextColor(Color.parseColor("#000000"))

        when(character.chosenSpeed){
            "Walk" -> {
                binding.SpeedValue.text = (character.baseWalkSpeed+character.miscWalkSpeedBonus).toString()
                binding.SpeedText.text = "Speed"
            }
            "Fly" -> {
                binding.SpeedValue.text = (character.baseFlySpeed+character.miscFlySpeedBonus).toString()
                binding.SpeedText.text = character.chosenSpeed
            }
            "Swim" -> {
                binding.SpeedValue.text = (character.baseSwimSpeed+character.miscSwimSpeedBonus).toString()
                binding.SpeedText.text = character.chosenSpeed
            }
            "Climb" -> {
                binding.SpeedValue.text = (character.baseClimbSpeed+character.miscClimbSpeedBonus).toString()
                binding.SpeedText.text = character.chosenSpeed
            }
        }

        binding.InitiativeValue.text = viewModel.calcInitiative(viewModel.calcModifier(
            character.dexterity).toInt(),
            character.miscInitiativeBonus,
            character.initiativeProf,
            character.initiativeHalfProf,
            character.initiativeDoubleProf,
            character.initiativeAdditionalAbility,
            character.proficiency
        ).toString()

        binding.ArmorValue.text = viewModel.calcArmor(character.armorBonus,
            character.shieldBonus,
            character.maxDexterityBonus,
            character.miscArmorBonus,
            character.armorType,
            character.statBonusArmor).toString()

        binding.CustomBlock1Text.text = character.customBlock1Name
        binding.CustomBlock1ScoreValue.text = character.customBlock1Value
        binding.CustomBlock2Text.text = character.customBlock2Name
        binding.CustomBlock2ScoreValue.text = character.customBlock2Value
        binding.CustomBlock3Text.text = character.customBlock3Name
        binding.CustomBlock3ScoreValue.text = character.customBlock3Value
        binding.CustomBlock4Text.text = character.customBlock4Name
        binding.CustomBlock4ScoreValue.text = character.customBlock4Value

        if (character.currentHP <= 0){
            binding.HPLayout.visibility = View.INVISIBLE
            binding.DeathsSaveLayout.visibility = View.VISIBLE
        }
        else {
            binding.HPLayout.visibility = View.VISIBLE
            binding.DeathsSaveLayout.visibility = View.INVISIBLE
        }

        when (character.passDeathSave){
            0->{
                binding.Check1.setImageResource(R.drawable.circle)
                binding.Check2.setImageResource(R.drawable.circle)
                binding.Check3.setImageResource(R.drawable.circle)
            }
            1->{
                binding.Check1.setImageResource(R.drawable.check_mark)
                binding.Check2.setImageResource(R.drawable.circle)
                binding.Check3.setImageResource(R.drawable.circle)
            }
            2->{
                binding.Check1.setImageResource(R.drawable.check_mark)
                binding.Check2.setImageResource(R.drawable.check_mark)
                binding.Check3.setImageResource(R.drawable.circle)
            }
            3->{
                binding.Check1.setImageResource(R.drawable.check_mark)
                binding.Check2.setImageResource(R.drawable.check_mark)
                binding.Check3.setImageResource(R.drawable.check_mark)
            }
        }

        when (character.failureDeathSave){
            0->{
                binding.Cross1.setImageResource(R.drawable.circle)
                binding.Cross2.setImageResource(R.drawable.circle)
                binding.Cross3.setImageResource(R.drawable.circle)
            }
            1->{
                binding.Cross1.setImageResource(R.drawable.cross_mark)
                binding.Cross2.setImageResource(R.drawable.circle)
                binding.Cross3.setImageResource(R.drawable.circle)
            }
            2->{
                binding.Cross1.setImageResource(R.drawable.cross_mark)
                binding.Cross2.setImageResource(R.drawable.cross_mark)
                binding.Cross3.setImageResource(R.drawable.circle)
            }
            3->{
                binding.Cross1.setImageResource(R.drawable.cross_mark)
                binding.Cross2.setImageResource(R.drawable.cross_mark)
                binding.Cross3.setImageResource(R.drawable.cross_mark)
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


