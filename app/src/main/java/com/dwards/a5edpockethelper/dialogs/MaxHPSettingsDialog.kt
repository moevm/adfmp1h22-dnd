package com.dwards.a5edpockethelper.dialogs

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.databinding.MaxhpSettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class MaxHPSettingsDialog : DialogFragment() {

    private var _binding: MaxhpSettingsDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = MaxhpSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        binding.SaveButton.setOnClickListener {
            viewModel.changeCharactersMaxHP(
                if (binding.MaxHPValue.text.toString().isNotBlank()) {
                    val str = binding.MaxHPValue.text.toString()
                    val intValue = str.toIntOrNull() ?: 0
                    if (intValue in 0..9999) {
                        intValue
                    } else {
                        Toast.makeText(
                            context,
                            "Value is not in correct range: [0,9999]!",
                            Toast.LENGTH_SHORT
                        ).show()
                        0
                    }
                } else {
                    Toast.makeText(context, "Value is not integer!", Toast.LENGTH_SHORT).show()
                    0
                }
            )
            dismiss()
        }

        return view
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
        binding.MaxHPValue.setText(character.maxHP.toString())
        if (character.tempHP > 0) {
            binding.HPValue.setTextColor(Color.parseColor("#2f00ba"))
        } else {
            val ctx = context
            if (ctx != null) {
                binding.HPValue.setTextColor(ctx.getColor(R.color.black))
            }
        }
        val hpSum = character.currentHP + character.tempHP
        binding.HPValue.text = hpSum.toString()
    }
}
