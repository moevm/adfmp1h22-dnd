package com.dwards.a5edpockethelper.dialogs

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.dwards.a5edpockethelper.databinding.SpellEditDialogBinding
import com.dwards.a5edpockethelper.model.Spell

class SpellEditDialog(private val spell: Spell): DialogFragment(), AdapterView.OnItemSelectedListener {
    private var _binding: SpellEditDialogBinding? = null
    private val binding get() = _binding!!
    private var editedSpell: Spell = spell.copy()
    private val this_ = this
    private val TAG = "MyCustomDialog"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = SpellEditDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]

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

        binding.spellNameText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editedSpell.name = binding.spellNameText.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.castingTimeTextValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editedSpell.castingTime = binding.castingTimeTextValue.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.rangeTextValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editedSpell.range = binding.rangeTextValue.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.materialsTextValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editedSpell.materials = binding.materialsTextValue.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.componentsTextValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editedSpell.components = binding.componentsTextValue.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.durationTextValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editedSpell.duration = binding.durationTextValue.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.spellDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editedSpell.text = binding.spellDescription.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.SaveButton.setOnClickListener {
            if (spell != editedSpell){
                viewModel.updateSpell(editedSpell)
            }
            dismiss()
        }

        binding.deleteButton.setOnClickListener{
            val alertDialog = AlertDialog.Builder(context)

            alertDialog.apply {
                setTitle("Delete Spell")
                setMessage("Are you sure?")
                setPositiveButton("Yes") { _, _ ->
                    dismiss()
                    this_.dismiss()
                    viewModel.deleteSpell(spell)
                }
                setNegativeButton("No") { _, _ ->
                    dismiss()
                }
            }.create().show()

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
                editedSpell.level = position
            }
            R.id.schoolValueSpinner -> {
                val school: String = parent.getItemAtPosition(position).toString()
                editedSpell.school = school
            }
            R.id.ritualValueSpinner -> {
                val ritual: Boolean = position == 0
                editedSpell.ritual = ritual
            }
            R.id.sourceValueSpinner -> {
                val source: String = parent.getItemAtPosition(position).toString()
                editedSpell.source = source
            }

        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}