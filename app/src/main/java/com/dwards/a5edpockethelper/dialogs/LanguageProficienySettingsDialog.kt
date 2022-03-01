package com.dwards.a5edpockethelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.databinding.LanguageproficiencySettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class LanguageProficiencySettingsDialog : DialogFragment() {

    private var _binding: LanguageproficiencySettingsDialogBinding? = null
    private val binding get() = _binding!!
    private var num: Int = 0
    private val TAG = "MyCustomDialog"

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
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = LanguageproficiencySettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        loadString(viewModel.getCharacter().value!!)


        binding.SaveButton.setOnClickListener {
            viewModel.changeLanguageProficiency(num, binding.ProficiencyName.text.toString())
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

    private fun loadString(character: Character) {
        binding.ProficiencyName.setText(character.languageProficiencyList[num])

    }
}
