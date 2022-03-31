package com.dwards.a5edpockethelper.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.databinding.SpellFilterDialogBinding
import com.dwards.a5edpockethelper.model.SpellFilter

class SpellFilterDialog(private val spellFilter: SpellFilter) : DialogFragment(),
    AdapterView.OnItemSelectedListener {
    private var _binding: SpellFilterDialogBinding? = null
    private val binding get() = _binding!!
    private var newFilter: SpellFilter = spellFilter.copy()

    private val TAG = "MyCustomDialog"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = SpellFilterDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]

        val levels = listOf(
            "All", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        )
        val schools = resources.getStringArray(R.array.SpellSchoolList).toMutableList()
        schools.add(0, "All")
        val sources = resources.getStringArray(R.array.SpellSourceList).toMutableList()
        sources.add(0, "All")
        val rituals = resources.getStringArray(R.array.SpellRitualList).toMutableList()
        rituals.add(0, "All")

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
        levelSpinner.setSelection(if (spellFilter.level != null) spellFilter.level!! + 1 else 0)


        val schoolSpinner: Spinner = binding.schoolValueSpinner
        ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item,
            schools
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            schoolSpinner.adapter = adapter
        }
        schoolSpinner.onItemSelectedListener = this
        schoolSpinner.setSelection(if (spellFilter.school != null) schools.indexOf(spellFilter.school!!) else 0)

        val ritualSpinner: Spinner = binding.ritualValueSpinner
        ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item,
            rituals
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            ritualSpinner.adapter = adapter
        }
        ritualSpinner.onItemSelectedListener = this
        ritualSpinner.setSelection(if (spellFilter.ritual == null) 0 else if (spellFilter.ritual == true) 1 else 2)

        val sourceSpinner: Spinner = binding.sourceValueSpinner
        ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item,
            sources
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            sourceSpinner.adapter = adapter
        }
        sourceSpinner.onItemSelectedListener = this
        sourceSpinner.setSelection(if (spellFilter.source != null) sources.indexOf(spellFilter.source!!) else 0)

        binding.FilterButton.setOnClickListener {
            if (spellFilter != newFilter)
                viewModel.showSpells(newFilter = newFilter)
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
        when (parent?.id) {
            R.id.levelValueSpinner -> {
                newFilter.level = if (position == 0) null else position - 1
            }
            R.id.schoolValueSpinner -> {
                if (position == 0)
                    newFilter.school = null
                else {
                    val school: String = parent.getItemAtPosition(position).toString()
                    newFilter.school = school
                }
            }
            R.id.ritualValueSpinner -> {
                if (position == 0)
                    newFilter.ritual = null
                else {
                    newFilter.ritual = position == 1
                }

            }
            R.id.sourceValueSpinner -> {
                if (position == 0)
                    newFilter.source = null
                else {
                    val source: String = parent.getItemAtPosition(position).toString()
                    newFilter.source = source
                }
            }

        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}