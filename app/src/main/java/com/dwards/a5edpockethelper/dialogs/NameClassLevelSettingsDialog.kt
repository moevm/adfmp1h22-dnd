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
import com.dwards.a5edpockethelper.databinding.NameclasslevelSettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class NameClassLevelSettingsDialog : DialogFragment() {

    private var _binding: NameclasslevelSettingsDialogBinding? = null
    private val binding get() = _binding!!

    private val TAG = "MyCustomDialog"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = NameclasslevelSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        binding.SaveButton.setOnClickListener {
            viewModel.changeCharactersNameClassLevel(
                if (binding.NameValue.text.toString() != "") binding.NameValue.text.toString() else "0",
                if (binding.ClassValue.text.toString() != "") binding.ClassValue.text.toString() else "0",
                if (binding.LevelValue.text.toString().isNotBlank()) {
                    val str = binding.LevelValue.text.toString()
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
                }
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
        binding.NameValue.setText(character.name)
        binding.ClassValue.setText(character.charClass)
        binding.LevelValue.setText(character.level.toString())

    }
}
