package com.dwards.a5edpockethelper.dialogs

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
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.databinding.CurrenthpSettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class CurrentHpSettingsDialog : DialogFragment() {

    private var _binding: CurrenthpSettingsDialogBinding? = null
    private val binding get() = _binding!!
    private val TAG = "MyCustomDialog"

    private var changeMode: Int = 1

    //private var damageMode: Boolean = true
    //private var healMode: Boolean = false
    //private var tempHPMode: Boolean = false
    private var changeHP: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = CurrenthpSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        binding.MinusButton.isEnabled = false
        binding.DamageCheck.isChecked = true

        binding.DamageCheck.setOnClickListener {
            binding.DamageCheck.isChecked = true
            binding.HealCheck.isChecked = false
            binding.TempHPCheck.isChecked = false
            changeMode = 1
        }

        binding.HealCheck.setOnClickListener {
            binding.DamageCheck.isChecked = false
            binding.HealCheck.isChecked = true
            binding.TempHPCheck.isChecked = false
            changeMode = 2
        }

        binding.TempHPCheck.setOnClickListener {
            binding.DamageCheck.isChecked = false
            binding.HealCheck.isChecked = false
            binding.TempHPCheck.isChecked = true
            changeMode = 3
        }

        binding.ChangeHPValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                changeHP = if (binding.ChangeHPValue.text.toString().isNotBlank()) {
                    val str = binding.ChangeHPValue.text.toString()
                    val intValue = str.toIntOrNull() ?: 0
                    if (intValue in 0..9) {
                        intValue
                    } else {
                        0
                    }
                } else {
                    0
                }
                binding.MinusButton.isEnabled = changeHP != 0
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.PlusButton.setOnClickListener {
            changeHP += 1
            //закрываем кнопку -, если 0
            binding.MinusButton.isEnabled = changeHP != 0
            binding.ChangeHPValue.setText(changeHP.toString())
        }

        binding.MinusButton.setOnClickListener {
            if (changeHP > 0)
                changeHP -= 1
            //закрываем кнопку -, если 0
            binding.MinusButton.isEnabled = changeHP != 0
            binding.ChangeHPValue.setText(changeHP.toString())
        }

        binding.SaveButton.setOnClickListener {
            //changeHP = binding.ChangeHPValue.text.toString().toInt()
            viewModel.changeCharactersCurrentHP(if (changeHP > 0) changeHP else 0, changeMode)
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

    private fun refreshChar(character: Character) {
        binding.HPValue.text = "${character.currentHP} $character.tempHP}"
        if (character.tempHP > 0) {
            binding.HPValue.setTextColor(Color.parseColor("#2f00ba"))
        } else {
            val ctx = context
            if (ctx != null) {
                binding.HPValue.setTextColor(ctx.getColor(R.color.black))
            }
        }
        binding.ChangeHPValue.setText(changeHP.toString())
    }
}
