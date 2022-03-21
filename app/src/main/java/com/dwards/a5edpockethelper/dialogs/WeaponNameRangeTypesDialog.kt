package com.dwards.a5edpockethelper.dialogs

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
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
        loadChar(viewModel.weaponList.value?.get(num)!!)

        //Выпадающий список Урон
        val armorTypeList: Spinner = binding.DamageTypeSpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.DamageTypeList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            armorTypeList.adapter = adapter
        }
        armorTypeList.onItemSelectedListener = this
        armorTypeList.setSelection(damageType)


        //Выпадающий список Бонус от Характеристики
        val additionalStatTypeList: Spinner = binding.AttackAbilitySpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.StatsList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            additionalStatTypeList.adapter = adapter
        }
        additionalStatTypeList.onItemSelectedListener = this
        additionalStatTypeList.setSelection(attackAbility)


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
            additionalStatTypeList.adapter = adapter
        }
        additionalStatTypeList.onItemSelectedListener = this
        additionalStatTypeList.setSelection(rangedType)


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
            additionalStatTypeList.adapter = adapter
        }
        additionalStatTypeList.onItemSelectedListener = this
        additionalStatTypeList.setSelection(handedType)


        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                //refreshChar(it) todo
            }
        })




        binding.SaveButton.setOnClickListener {
            //todo
            dismiss()
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

    /* todo
     private fun refreshChar(character: Character) {
        binding.ArmorBonusValue.setText(character.armorBonus.toString())
        binding.ShieldBonusValue.setText(character.shieldBonus.toString())
        binding.DexterityBonusValue.setText(viewModel.calcModifier(character.dexterity))
        binding.MaxDexterityBonusValue.setText(character.maxDexterityBonus.toString())
        binding.MiscBonusValue.setText(character.miscArmorBonus.toString())
        binding.ArmorValue.setText(
            viewModel.calcArmor(
                character.armorBonus,
                character.shieldBonus,
                character.maxDexterityBonus,
                character.miscArmorBonus,
                armorType,
                additionalStatBonus
            ).toString()
        )
    }
     */


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.DamageTypeSpinner -> {
                damageType = position
            }
            R.id.AdditionalStatBonusSpinner -> damageType = position
        }
        //refreshArmor()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


    private fun loadChar(weapon: Weapon) {
        damageType = weapon.damageTypePosition
        attackAbility = weapon.statAttackBonus
        rangedType = weapon.rangeType
        handedType = weapon.handedType
        binding.weaponNameValue.setText(weapon.name)
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

}
