package com.dwards.a5edpockethelper.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.databinding.SpellInfoDialogBinding
import com.dwards.a5edpockethelper.model.Spell

class SpellInfoDialog(private val spell: Spell) : DialogFragment() {
    private var _binding: SpellInfoDialogBinding? = null
    private val binding get() = _binding!!

    private val TAG = "MyCustomDialog"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = SpellInfoDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        binding.spellNameText.text = spell.name
        binding.levelTextValue.text = spell.level.toString()
        binding.schoolTextValue.text = spell.school
        binding.castingTimeTextValue.text = spell.castingTime
        binding.rangeTextValue.text = spell.range
        binding.materialsTextValue.text = spell.materials
        binding.componentsTextValue.text = spell.components
        binding.durationTextValue.text = spell.duration
        binding.ritualTextValue.text = if (spell.ritual) "Yes" else "No"
        binding.sourceTextValue.text = spell.source
        binding.spellDescription.text = spell.text

        binding.CloseButton.setOnClickListener {
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
}