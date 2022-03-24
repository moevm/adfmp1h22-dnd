package com.dwards.a5edpockethelper.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.databinding.SpellEditDialogBinding
import com.dwards.a5edpockethelper.model.Spell

class SpellEditDialog(private val spell: Spell): DialogFragment(), AdapterView.OnItemSelectedListener {
    private var _binding: SpellEditDialogBinding? = null
    private val binding get() = _binding!!

    private val TAG = "MyCustomDialog"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = SpellEditDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //val viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        val levels = listOf(
            0,1,2,3,4,5,6,7,8,9
        )
        val schools = resources.getStringArray(R.array.SpellSchoolList)
        val sources = resources.getStringArray(R.array.SpellSourceList)

        binding.spellNameText.setText(spell.name)
        binding.castingTimeTextValue.setText(spell.castingTime)
        binding.rangeTextValue.setText(spell.range)
        binding.materialsTextValue.setText(spell.materials)
        binding.componentsTextValue.setText(spell.components)
        binding.durationTextValue.setText(spell.duration)
        binding.spellDescription.setText(spell.text)

        val levelSpinner: Spinner = binding.levelValueSpinner
        ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item,
            levels
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            levelSpinner.adapter = adapter
        }
        levelSpinner.onItemSelectedListener = this
        levelSpinner.setSelection(spell.level)


        val schoolSpinner: Spinner = binding.schoolValueSpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.SpellSchoolList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            schoolSpinner.adapter = adapter
        }
        schoolSpinner.onItemSelectedListener = this
        schoolSpinner.setSelection(schools.indexOf(spell.school))

        val ritualSpinner: Spinner = binding.ritualValueSpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.SpellRitualList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            ritualSpinner.adapter = adapter
        }
        ritualSpinner.onItemSelectedListener = this
        ritualSpinner.setSelection(if (spell.ritual) 0 else 1)

        val sourceSpinner: Spinner = binding.sourceValueSpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.SpellSourceList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            sourceSpinner.adapter = adapter
        }
        sourceSpinner.onItemSelectedListener = this
        sourceSpinner.setSelection(sources.indexOf(spell.source))

        binding.SaveButton.setOnClickListener {
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //parent?.getItemAtPosition(position)
        when (parent?.id) {
            R.id.levelValueSpinner -> {
                val level = position
                when (level) {
                }
            }

        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}