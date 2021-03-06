package com.dwards.a5edpockethelper.dialogs

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.databinding.WeaponNameRangeTypesSettginsDialogBinding
import com.dwards.a5edpockethelper.model.Character
import com.dwards.a5edpockethelper.model.Weapon

class WeaponNameRangeTypesDialog : DialogFragment(), AdapterView.OnItemSelectedListener {

    private var _binding: WeaponNameRangeTypesSettginsDialogBinding? = null
    private val binding get() = _binding!!

    private val TAG = "MyCustomDialog"
    private var num: Int = 0
    private var damageType: Int = 0
    private var attackAbility: Int = 0
    private var rangedType: Int = 0
    private var handedType: Int = 0
    private val this_ = this
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val mArgs = arguments
            num = mArgs?.getInt("num")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = WeaponNameRangeTypesSettginsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)


        //грузим данные
        loadChar(viewModel.weaponList.value?.get(num)!!, viewModel.getCharacter().value!!)

        //Выпадающий список Урон
        val damageTypeList: Spinner = binding.DamageTypeSpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.DamageTypeList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            damageTypeList.adapter = adapter
        }
        damageTypeList.onItemSelectedListener = this
        damageTypeList.setSelection(damageType)


        //Выпадающий список Бонус от Характеристики
        val attackAbilityList: Spinner = binding.AttackAbilitySpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.StatsList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            attackAbilityList.adapter = adapter
        }
        attackAbilityList.onItemSelectedListener = this
        attackAbilityList.setSelection(attackAbility)


        //Выпадающий список Дальность
        val rangedTypeList: Spinner = binding.RangedTypeSpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.AttackRangeType,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            rangedTypeList.adapter = adapter
        }
        rangedTypeList.onItemSelectedListener = this
        rangedTypeList.setSelection(rangedType)


        //Выпадающий список Тип хвата
        val HandedTypeList: Spinner = binding.HandedTypeSpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.WeaponHandedType,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            HandedTypeList.adapter = adapter
        }
        HandedTypeList.onItemSelectedListener = this
        HandedTypeList.setSelection(handedType)


        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                //refreshChar(it) todo
            }
        })

        binding.SaveButton.setOnClickListener {
            viewModel.changeWeapon(
                if (binding.weaponNameValue.text.toString() != "") binding.weaponNameValue.text.toString() else "Weapon",
                if (binding.weaponRangeValue.text.toString() != "") binding.weaponRangeValue.text.toString() else "0",
                damageType,
                attackAbility,
                rangedType,
                handedType,
                getCorrectIntValueFromInput(binding.attackMagicBonusValue.text.toString()),
                getCorrectIntValueFromInput(binding.attackMiscBonusValue.text.toString()),
                binding.addProficiencyToAttackCheck.isChecked,
                getCorrectIntValueFromInput(binding.damageMagicBonusValue.text.toString()),
                getCorrectIntValueFromInput(binding.damageMiscBonusValue.text.toString()),
                binding.addAbilityModToDamage.isChecked,
                getCorrectIntValueFromInput(binding.damageDice1CountValue.text.toString()),
                getCorrectIntValueFromInput(binding.damageDice1ValueValue.text.toString()),
                binding.weaponDescription.text.toString(),
            )
            dismiss()
        }

        binding.deleteButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)

            alertDialog.apply {
                setTitle("Delete Spell")
                setMessage("Are you sure?")
                setPositiveButton("Yes") { _, _ ->
                    dismiss()
                    //this_.
                    dismiss()
                    viewModel.deleteWeapon(viewModel.weaponList.value?.get(num)!!)
                }
                setNegativeButton("No") { _, _ ->
                    dismiss()
                }
            }.create().show()

        }


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        //val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.DamageTypeSpinner -> damageType = position
            R.id.AttackAbilitySpinner -> attackAbility = position
            R.id.RangedTypeSpinner -> rangedType = position
            R.id.HandedTypeSpinner -> handedType = position
        }
        //refreshArmor()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


    private fun loadChar(weapon: Weapon, character: Character) {
        viewModel.chooseWeapon(weapon.id)
        damageType = weapon.damageTypePosition
        attackAbility = weapon.attackStatType
        rangedType = weapon.rangeType
        handedType = weapon.handedType
        binding.weaponNameValue.setText(weapon.name)
        binding.weaponRangeValue.setText(weapon.range)
        binding.attackProfBonusValue.setText(character.proficiency.toString())
        binding.attackAbilityBonusValue.setText(
            when (attackAbility) {//TODO сделать обновляемым онлайн
                1 -> viewModel.calcModifier(character.strength)
                2 -> viewModel.calcModifier(character.dexterity)
                3 -> viewModel.calcModifier(character.constitution)
                4 -> viewModel.calcModifier(character.intelligence)
                5 -> viewModel.calcModifier(character.wisdom)
                6 -> viewModel.calcModifier(character.charisma)
                else -> "0"
            }
        )
        binding.attackMagicBonusValue.setText(weapon.magicAttackBonus.toString())
        binding.attackMiscBonusValue.setText(weapon.miscAttackBonus.toString())
        if (weapon.weaponProf) {
            binding.addProficiencyToAttackCheck.isChecked = true
        }
        binding.damageProfBonusValue.setText(character.proficiency.toString())
        binding.damageMagicBonusValue.setText(weapon.magicDamageBonus.toString())
        binding.damageMiscBonusValue.setText(weapon.miscDamageBonus.toString())
        if (weapon.statApplyToDmg) {
            binding.addAbilityModToDamage.isChecked = true
        }
        binding.damageDice1CountValue.setText(weapon.damageDice1Count.toString())
        binding.damageDice1ValueValue.setText(weapon.damageDice1Size.toString())
        binding.weaponDescription.setText(weapon.description)
    }

    /* todo
    private fun refreshArmor() {
        binding.ArmorValue.text = viewModel.calcArmor(
            if (binding.ArmorBonusValue.text.toString() != "") binding.ArmorBonusValue.text.toString()
                .toInt() else 0,
            if (binding.ShieldBonusValue.text.toString() != "") binding.ShieldBonusValue.text.toString()
                .toInt() else 0,
            if (binding.MaxDexterityBonusValue.text.toString() != "") binding.MaxDexterityBonusValue.text.toString()
                .toInt() else 0,
            if (binding.MiscBonusValue.text.toString() != "") binding.MiscBonusValue.text.toString()
                .toInt() else 0,
            armorType,
            additionalStatBonus
        ).toString()
    }
     */

    private fun getCorrectIntValueFromInput(str: String): Int {
        if (str.isNotBlank()) {
            val intValue = str.toIntOrNull() ?: 0
            return if (intValue in 0..99) {
                intValue
            } else {
                Toast.makeText(
                    context,
                    "Value is not in correct range: [0,99]!",
                    Toast.LENGTH_SHORT
                ).show()
                0
            }
        } else {
            Toast.makeText(context, "Value is not integer!", Toast.LENGTH_SHORT).show()
            return 0
        }
    }
}
