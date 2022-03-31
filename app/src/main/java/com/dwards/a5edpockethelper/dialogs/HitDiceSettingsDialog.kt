package com.dwards.a5edpockethelper.dialogs

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
import com.dwards.a5edpockethelper.databinding.HitdiceSettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class HitDiceSettingsDialog : DialogFragment() {

    private var _binding: HitdiceSettingsDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = HitdiceSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        binding.SaveButton.setOnClickListener {
            viewModel.changeCharactersHitDice(
                if (binding.HitDiceCountValue.text.toString().isNotBlank()) {
                    val str = binding.HitDiceCountValue.text.toString()
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
                if (binding.HitDiceSizeValue.text.toString().isNotBlank()) {
                    val str = binding.HitDiceSizeValue.text.toString()
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
            )
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
        binding.HitDiceCountValue.setText(
            if (character.hitDiceCount.toString().isNotBlank()) {
                val str = character.hitDiceCount.toString()
                val intValue = str.toIntOrNull() ?: {
                    Toast.makeText(context, "Value is too big!", Toast.LENGTH_SHORT).show()
                    "0"
                }
                if (intValue in 0..99) {
                    intValue.toString()
                } else {
                    Toast.makeText(
                        context,
                        "Value is not in correct range: [0,99]!",
                        Toast.LENGTH_SHORT
                    ).show()
                    "0"
                }
            } else {
                "0"
            }
        )
        binding.HitDiceSizeValue.setText(
            if (character.hitDiceSize.toString().isNotBlank()) {
                val str = character.hitDiceSize.toString()
                val intValue = str.toIntOrNull() ?: {
                    Toast.makeText(context, "Value is too big!", Toast.LENGTH_SHORT).show()
                    "0"
                }
                if (intValue in 0..99) {
                    intValue.toString()
                } else {
                    Toast.makeText(
                        context,
                        "Value is not in correct range: [0,99]!",
                        Toast.LENGTH_SHORT
                    ).show()
                    "0"
                }
            } else {
                Toast.makeText(context, "Value is not integer!", Toast.LENGTH_SHORT).show()
                "0"
            }
        )
    }
}
