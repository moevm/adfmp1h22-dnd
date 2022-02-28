package com.dwards.a5edpockethelper

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
import com.dwards.a5edpockethelper.databinding.CustomblockSettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class CustomBlockSettingsDialog : DialogFragment() {

    private var _binding: CustomblockSettingsDialogBinding? = null
    private val binding get() = _binding!!
    private val TAG = "MyCustomDialog"

    private var changeMode: Int = 1
    private var customBlock1Value: String = "0"
    private var customBlock2Value: String = "0"
    private var customBlock3Value: String = "0"
    private var customBlock4Value: String = "0"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = CustomblockSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        val viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })




        binding.PlusButton1.setOnClickListener{
            if (customBlock1Value.toIntOrNull() != null){
                customBlock1Value = (customBlock1Value.toInt()+1).toString()
            }
            else {
                val match = Regex("[^0-9 -]").find(customBlock1Value)
                var num = match?.range?.start?.let {customBlock1Value.substring(0, it)}
                if(num != ""){
                    customBlock1Value =customBlock1Value.drop(num?.length!!)
                    num = (num?.toInt()?.plus(1)).toString()
                    customBlock1Value = num.plus(customBlock1Value)
                }
            }
            binding.CustomBlock1Value.setText(customBlock1Value)
        }

        binding.MinusButton1.setOnClickListener{
            if (customBlock1Value.toIntOrNull() != null){
                customBlock1Value = (customBlock1Value.toInt()-1).toString()
            }
            else {
                val match = Regex("[^0-9 -]").find(customBlock1Value)
                var num = match?.range?.start?.let {customBlock1Value.substring(0, it)}
                if(num != ""){
                    customBlock1Value =customBlock1Value.drop(num?.length!!)
                    num = (num?.toInt()?.minus(1)).toString()
                    customBlock1Value = num.plus(customBlock1Value)
                }
            }
            binding.CustomBlock1Value.setText(customBlock1Value)
        }

        binding.PlusButton2.setOnClickListener{
            if (customBlock2Value.toIntOrNull() != null){
                customBlock2Value = (customBlock2Value.toInt()+1).toString()
            }
            else {
                val match = Regex("[^0-9 -]").find(customBlock2Value)
                var num = match?.range?.start?.let {customBlock2Value.substring(0, it)}
                if(num != ""){
                    customBlock2Value =customBlock2Value.drop(num?.length!!)
                    num = (num?.toInt()?.plus(1)).toString()
                    customBlock2Value = num.plus(customBlock2Value)
                }
            }
            binding.CustomBlock2Value.setText(customBlock2Value)
        }

        binding.MinusButton2.setOnClickListener{
            if (customBlock2Value.toIntOrNull() != null){
                customBlock2Value = (customBlock2Value.toInt()-1).toString()
            }
            else {
                val match = Regex("[^0-9 -]").find(customBlock2Value)
                var num = match?.range?.start?.let {customBlock2Value.substring(0, it)}
                if(num != ""){
                    customBlock2Value =customBlock2Value.drop(num?.length!!)
                    num = (num?.toInt()?.minus(1)).toString()
                    customBlock2Value = num.plus(customBlock2Value)
                }
            }
            binding.CustomBlock2Value.setText(customBlock2Value)
        }

        binding.PlusButton3.setOnClickListener{
            if (customBlock3Value.toIntOrNull() != null){
                customBlock3Value = (customBlock3Value.toInt()-1).toString()
            }
            else {
                val match = Regex("[^0-9 -]").find(customBlock3Value)
                var num = match?.range?.start?.let {customBlock3Value.substring(0, it)}
                if(num != ""){
                    customBlock3Value =customBlock3Value.drop(num?.length!!)
                    num = (num?.toInt()?.plus(1)).toString()
                    customBlock3Value = num.plus(customBlock3Value)
                }
            }
            binding.CustomBlock3Value.setText(customBlock3Value)
        }

        binding.MinusButton3.setOnClickListener{
            if (customBlock3Value.toIntOrNull() != null){
                customBlock3Value = (customBlock3Value.toInt()-1).toString()
            }
            else {
                val match = Regex("[^0-9 -]").find(customBlock3Value)
                var num = match?.range?.start?.let {customBlock3Value.substring(0, it)}
                if(num != ""){
                    customBlock3Value =customBlock3Value.drop(num?.length!!)
                    num = (num?.toInt()?.minus(1)).toString()
                    customBlock3Value = num.plus(customBlock3Value)
                }
            }
            binding.CustomBlock3Value.setText(customBlock3Value)
        }

        binding.PlusButton4.setOnClickListener{
            if (customBlock4Value.toIntOrNull() != null){
                customBlock4Value = (customBlock4Value.toInt()+1).toString()
            }
            else {
                val match = Regex("[^0-9 -]").find(customBlock4Value)
                var num = match?.range?.start?.let {customBlock4Value.substring(0, it)}
                if(num != ""){
                    customBlock4Value =customBlock4Value.drop(num?.length!!)
                    num = (num?.toInt()?.plus(1)).toString()
                    customBlock4Value = num.plus(customBlock4Value)
                }
            }
            binding.CustomBlock4Value.setText(customBlock4Value)
        }

        binding.MinusButton4.setOnClickListener{
            if (customBlock4Value.toIntOrNull() != null){
                customBlock4Value = (customBlock4Value.toInt()-1).toString()
            }
            else {
                val match = Regex("[^0-9 -]").find(customBlock4Value)
                var num = match?.range?.start?.let {customBlock4Value.substring(0, it)}
                if(num != ""){
                    customBlock4Value =customBlock4Value.drop(num?.length!!)
                    num = (num?.toInt()?.minus(1)).toString()
                    customBlock4Value = num.plus(customBlock4Value)
                }
            }
            binding.CustomBlock4Value.setText(customBlock4Value)
        }

        binding.CustomBlock1Value.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                customBlock1Value = binding.CustomBlock1Value.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.CustomBlock2Value.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                customBlock2Value = binding.CustomBlock2Value.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.CustomBlock3Value.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                customBlock3Value = binding.CustomBlock3Value.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.CustomBlock4Value.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                customBlock4Value = binding.CustomBlock4Value.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.SaveButton.setOnClickListener {
            customBlock1Value = binding.CustomBlock1Value.text.toString()
            customBlock2Value = binding.CustomBlock2Value.text.toString()
            customBlock3Value = binding.CustomBlock3Value.text.toString()
            customBlock4Value = binding.CustomBlock4Value.text.toString()
            viewModel.changeCharactersCustomBlock(
                if (customBlock1Value != "") customBlock1Value else "0",
                if (binding.CustomBlock1NameValue.text.toString() != "") binding.CustomBlock1NameValue.text.toString() else "0",
                if (customBlock2Value != "") customBlock2Value else "0",
                if (binding.CustomBlock2NameValue.text.toString() != "") binding.CustomBlock2NameValue.text.toString() else "0",
                if (customBlock3Value != "") customBlock3Value else "0",
                if (binding.CustomBlock3NameValue.text.toString() != "") binding.CustomBlock3NameValue.text.toString() else "0",
                if (customBlock4Value != "") customBlock4Value else "0",
                if (binding.CustomBlock4NameValue.text.toString() != "") binding.CustomBlock4NameValue.text.toString() else "0")
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
        binding.CustomBlock1NameValue.setText(character.customBlock1Name)
        binding.CustomBlock2NameValue.setText(character.customBlock2Name)
        binding.CustomBlock3NameValue.setText(character.customBlock3Name)
        binding.CustomBlock4NameValue.setText(character.customBlock4Name)

        binding.CustomBlock1Value.setText(character.customBlock1Value)
        binding.CustomBlock2Value.setText(character.customBlock2Value)
        binding.CustomBlock3Value.setText(character.customBlock3Value)
        binding.CustomBlock4Value.setText(character.customBlock4Value)
    }
}
