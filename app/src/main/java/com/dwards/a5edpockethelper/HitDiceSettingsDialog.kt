package com.dwards.a5edpockethelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.databinding.HitdiceSettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class HitDiceSettingsDialog : DialogFragment() {

    private var _binding: HitdiceSettingsDialogBinding? = null
    private val binding get() = _binding!!

    private val TAG = "MyCustomDialog"



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = HitdiceSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        val viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })



        binding.SaveButton.setOnClickListener {
            viewModel.changeCharactersHitDice(if (binding.HitDiceCountValue.text.toString() != "") binding.HitDiceCountValue.text.toString().toInt() else 0
                , if (binding.HitDiceSizeValue.text.toString() != "") binding.HitDiceSizeValue.text.toString().toInt() else 0)
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
        binding.HitDiceCountValue.setText(character.hitDiceCount.toString())
        binding.HitDiceSizeValue.setText(character.hitDiceSize.toString())
    }
}
