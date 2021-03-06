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
import com.dwards.a5edpockethelper.databinding.SpeedSettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class SpeedSettingsDialog : DialogFragment() {

    private var _binding: SpeedSettingsDialogBinding? = null
    private val binding get() = _binding!!

    private val TAG = "MyCustomDialog"
    private var speedType: String = "Walk"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = SpeedSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })
        //вылет если не выбрать персонажа
        //switchSpeed(viewModel.getCharacter().value!!, speedType)

        binding.WalkCheck.setOnClickListener {
            speedType = changeChosenSpeed(1)
            binding.FlyCheck.isChecked = false
            binding.SwimCheck.isChecked = false
            binding.ClimbCheck.isChecked = false
            switchSpeed(viewModel.getCharacter().value!!, speedType)
        }

        binding.FlyCheck.setOnClickListener {
            speedType = changeChosenSpeed(2)
            binding.WalkCheck.isChecked = false
            binding.SwimCheck.isChecked = false
            binding.ClimbCheck.isChecked = false
            switchSpeed(viewModel.getCharacter().value!!, speedType)
        }

        binding.SwimCheck.setOnClickListener {
            speedType = changeChosenSpeed(3)
            binding.FlyCheck.isChecked = false
            binding.WalkCheck.isChecked = false
            binding.ClimbCheck.isChecked = false
            switchSpeed(viewModel.getCharacter().value!!, speedType)
        }

        binding.ClimbCheck.setOnClickListener {
            speedType = changeChosenSpeed(4)
            binding.FlyCheck.isChecked = false
            binding.SwimCheck.isChecked = false
            binding.WalkCheck.isChecked = false
            switchSpeed(viewModel.getCharacter().value!!, speedType)
        }

        binding.SaveButton.setOnClickListener {
            viewModel.changeCharactersSpeed(
                if (binding.SpeedBaseValue.text.toString().isNotBlank()) {
                    val str = binding.SpeedBaseValue.text.toString()
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
                if (binding.SpeedMiscBonusValue.text.toString().isNotBlank()) {
                    val str = binding.SpeedMiscBonusValue.text.toString()
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
                speedType
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
        when (character.chosenSpeed) {
            "Walk" -> {
                binding.SpeedBaseValue.setText(character.baseWalkSpeed.toString())
                binding.SpeedMiscBonusValue.setText(character.miscWalkSpeedBonus.toString())
                binding.SpeedText.text = "Speed"
                binding.WalkCheck.isChecked = true
            }
            "Fly" -> {
                binding.SpeedBaseValue.setText(character.baseFlySpeed.toString())
                binding.SpeedMiscBonusValue.setText(character.miscFlySpeedBonus.toString())
                binding.SpeedText.text = character.chosenSpeed
                binding.FlyCheck.isChecked = true
            }
            "Swim" -> {
                binding.SpeedBaseValue.setText(character.baseSwimSpeed.toString())
                binding.SpeedMiscBonusValue.setText(character.miscSwimSpeedBonus.toString())
                binding.SpeedText.text = character.chosenSpeed
                binding.SwimCheck.isChecked = true
            }
            "Climb" -> {
                binding.SpeedBaseValue.setText(character.baseClimbSpeed.toString())
                binding.SpeedMiscBonusValue.setText(character.miscClimbSpeedBonus.toString())
                binding.SpeedText.text = character.chosenSpeed
                binding.ClimbCheck.isChecked = true
            }
            else -> {
                binding.SpeedBaseValue.setText(character.baseWalkSpeed.toString())
                binding.SpeedMiscBonusValue.setText(character.miscWalkSpeedBonus.toString())
                binding.SpeedText.text = "Speed"
                binding.WalkCheck.isChecked = true
            }
        }
    }

    private fun switchSpeed(character: Character, type: String) {
        when (type) {
            "Walk" -> {
                binding.SpeedBaseValue.setText(character.baseWalkSpeed.toString())
                binding.SpeedMiscBonusValue.setText(character.miscWalkSpeedBonus.toString())
                binding.SpeedText.text = "Speed"
                binding.WalkCheck.isChecked = true
            }

            "Fly" -> {
                binding.SpeedBaseValue.setText(character.baseFlySpeed.toString())
                binding.SpeedMiscBonusValue.setText(character.miscFlySpeedBonus.toString())
                binding.SpeedText.text = type
                binding.FlyCheck.isChecked = true
            }

            "Swim" -> {
                binding.SpeedBaseValue.setText(character.baseSwimSpeed.toString())
                binding.SpeedMiscBonusValue.setText(character.miscSwimSpeedBonus.toString())
                binding.SpeedText.text = type
                binding.SwimCheck.isChecked = true
            }

            "Climb" -> {
                binding.SpeedBaseValue.setText(character.baseClimbSpeed.toString())
                binding.SpeedMiscBonusValue.setText(character.miscClimbSpeedBonus.toString())
                binding.SpeedText.text = type
                binding.ClimbCheck.isChecked = true
            }

            "None" -> {
                binding.SpeedBaseValue.setText(character.baseClimbSpeed.toString())
                binding.SpeedMiscBonusValue.setText(character.miscClimbSpeedBonus.toString())
                binding.SpeedText.text = "Speed"
                binding.WalkCheck.isChecked = true
            }

            else -> {
                binding.SpeedBaseValue.setText(character.baseClimbSpeed.toString())
                binding.SpeedMiscBonusValue.setText(character.miscClimbSpeedBonus.toString())
                binding.SpeedText.text = "Speed"
                binding.WalkCheck.isChecked = true
            }
        }
    }

    private fun changeChosenSpeed(type: Int) = when (type) {
        1 -> "Walk"
        2 -> "Fly"
        3 -> "Swim"
        4 -> "Climb"
        else -> "Walk"
    }
}
