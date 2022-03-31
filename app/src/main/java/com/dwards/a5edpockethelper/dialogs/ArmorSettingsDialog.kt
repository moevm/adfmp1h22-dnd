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
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.databinding.ArmorSettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class ArmorSettingsDialog : DialogFragment(), AdapterView.OnItemSelectedListener {

    private var _binding: ArmorSettingsDialogBinding? = null
    private val binding get() = _binding!!

    private val TAG = "MyCustomDialog"
    private var armorType: Int = 2
    private var additionalStatBonus: Int = 0
    private lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = ArmorSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        //грузим данные
        loadChar(viewModel.getCharacter().value!!)

        //Выпадающий список
        val armorTypeList: Spinner = binding.ArmorTypeSpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.ArmorTypeList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            armorTypeList.adapter = adapter
        }
        armorTypeList.onItemSelectedListener = this
        armorTypeList.setSelection(armorType)


        //Выпадающий список
        val additionalStatTypeList: Spinner = binding.AdditionalStatBonusSpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.StatsForArmorList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            additionalStatTypeList.adapter = adapter
        }
        additionalStatTypeList.onItemSelectedListener = this
        additionalStatTypeList.setSelection(additionalStatBonus)


        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        binding.ArmorBonusValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                refreshArmor()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.ShieldBonusValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                refreshArmor()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.MiscBonusValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                refreshArmor()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.MaxDexterityBonusValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                refreshArmor()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })



        binding.SaveButton.setOnClickListener {
            viewModel.changeCharactersArmor(
                if (binding.ArmorBonusValue.text.toString().isNotBlank()) {
                    val str = binding.ArmorBonusValue.text.toString()
                    val intValue = str.toIntOrNull() ?: 0
                    if (intValue in 0..99) {
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
                    0
                },
                if (binding.ShieldBonusValue.text.toString().isNotBlank()) {
                    val str = binding.ShieldBonusValue.text.toString()
                    val intValue = str.toIntOrNull() ?: 0
                    if (intValue in 0..99) {
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
                    0
                },
                if (binding.MaxDexterityBonusValue.text.toString().isNotBlank()) {
                    val str = binding.MaxDexterityBonusValue.text.toString()
                    val intValue = str.toIntOrNull() ?: 0
                    if (intValue in 0..99) {
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
                    0
                },
                if (binding.MiscBonusValue.text.toString().isNotBlank()) {
                    val str = binding.MiscBonusValue.text.toString()
                    val intValue = str.toIntOrNull() ?: 0
                    if (intValue in 0..99) {
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
                    0
                },
                armorType,
                additionalStatBonus
            )
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.ArmorTypeSpinner -> {
                armorType = position
                when (armorType) {
                    0 -> {
                        binding.MaxDexterityBonusValue.setText("10")
                        binding.MaxDexterityBonusValue.isEnabled = false
                    }
                    1 -> {
                        binding.MaxDexterityBonusValue.setText("5")
                        binding.MaxDexterityBonusValue.isEnabled = false
                    }
                    2 -> {
                        binding.MaxDexterityBonusValue.setText("2")
                        binding.MaxDexterityBonusValue.isEnabled = false
                    }
                    3 -> {
                        binding.MaxDexterityBonusValue.setText("0")
                        binding.MaxDexterityBonusValue.isEnabled = false
                    }
                    4 -> binding.MaxDexterityBonusValue.isEnabled = true
                }
            }
            R.id.AdditionalStatBonusSpinner -> additionalStatBonus = position
        }
        refreshArmor()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


    private fun loadChar(character: Character) {
        armorType = character.armorType
        additionalStatBonus = character.statBonusArmor


    }

    private fun refreshArmor() {
        binding.ArmorValue.text = viewModel.calcArmor(
            if (binding.ArmorBonusValue.text.toString().isNotBlank()) {
                val str = binding.ArmorBonusValue.text.toString()
                val intValue = str.toIntOrNull() ?: 0
                if (intValue in 0..99) {
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
                0
            },
            if (binding.ShieldBonusValue.text.toString().isNotBlank()) {
                val str = binding.ShieldBonusValue.text.toString()
                val intValue = str.toIntOrNull() ?: 0
                if (intValue in 0..99) {
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
                0
            },
            if (binding.MaxDexterityBonusValue.text.toString().isNotBlank()) {
                val str = binding.MaxDexterityBonusValue.text.toString()
                val intValue = str.toIntOrNull() ?: 0
                if (intValue in 0..99) {
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
                0
            },
            if (binding.MiscBonusValue.text.toString().isNotBlank()) {
                val str = binding.MiscBonusValue.text.toString()
                val intValue = str.toIntOrNull() ?: 0
                if (intValue in 0..99) {
                    Toast.makeText(
                        context,
                        "Value is not in correct range: [0,99]!",
                        Toast.LENGTH_SHORT
                    ).show()
                    intValue
                } else {
                    Toast.makeText(context, "Value is not integer!", Toast.LENGTH_SHORT).show()
                    0
                }
            } else {
                0
            },
            armorType,
            additionalStatBonus
        ).toString()
    }
}
