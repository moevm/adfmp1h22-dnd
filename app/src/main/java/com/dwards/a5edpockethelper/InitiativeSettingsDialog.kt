package com.dwards.a5edpockethelper

import android.annotation.SuppressLint
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dwards.a5edpockethelper.databinding.InitiativeSettingsDialogBinding
import com.dwards.a5edpockethelper.model.Character

class InitiativeSettingsDialog : DialogFragment(), AdapterView.OnItemSelectedListener {

    private var _binding: InitiativeSettingsDialogBinding? = null
    private val binding get() = _binding!!

    private val TAG = "MyCustomDialog"

    private var prof: Boolean = false
    private var halfProf: Boolean = false
    private var doubleProf: Boolean = false
    private var additionalStatBonus: Int = 0
    private lateinit var viewModel: MyViewModel




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = InitiativeSettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        //создание вью-модел и добавление обсервера
        viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)


        //грузим данные
        loadChar(viewModel.getCharacter().value!!)


        //Выпадающий список
        val additionalStatList: Spinner = binding.StatsSpinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.StatsList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            additionalStatList.adapter = adapter
        }
        additionalStatList.onItemSelectedListener = this
        additionalStatList.setSelection(additionalStatBonus)


        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                refreshChar(it)
            }
        })

        binding.AddProficiencyCheck.setOnClickListener {
            if (binding.AddProficiencyCheck.isChecked && !binding.SecondProficiencyCheck.isChecked) {
                binding.AddProficiencyCheck.isChecked = true
                prof = true
                halfProf = false
                doubleProf = false
                binding.SecondProficiencyCheck.text = resources.getString(R.string.doubleProf)
            } else if (binding.AddProficiencyCheck.isChecked && binding.SecondProficiencyCheck.isChecked && binding.SecondProficiencyCheck.text == resources.getString(R.string.doubleProf)){
                binding.AddProficiencyCheck.isChecked = false
                binding.SecondProficiencyCheck.isChecked = false
                prof = false
                halfProf = false
                doubleProf = false
                binding.SecondProficiencyCheck.text = resources.getString(R.string.halfProf)
            } else if (binding.AddProficiencyCheck.isChecked && binding.SecondProficiencyCheck.isChecked && binding.SecondProficiencyCheck.text == resources.getString(R.string.halfProf)){
                binding.AddProficiencyCheck.isChecked = true
                binding.SecondProficiencyCheck.isChecked = false
                prof = true
                halfProf = false
                doubleProf = false
                binding.SecondProficiencyCheck.text = resources.getString(R.string.doubleProf)
            } else {
                binding.AddProficiencyCheck.isChecked = false
                prof = false
                halfProf = false
                doubleProf = false
                binding.SecondProficiencyCheck.text = resources.getString(R.string.halfProf)
                binding.SecondProficiencyCheck.isChecked = false
            }
            refreshInitiative(viewModel.getCharacter().value!!)
        }

        binding.SecondProficiencyCheck.setOnClickListener {
            if (binding.SecondProficiencyCheck.isChecked && !binding.AddProficiencyCheck.isChecked) {
                binding.SecondProficiencyCheck.isChecked = true
                prof = false
                halfProf = true
                doubleProf = false
            } else if (binding.SecondProficiencyCheck.isChecked && binding.AddProficiencyCheck.isChecked){
                binding.SecondProficiencyCheck.isChecked = true
                prof = false
                halfProf = false
                doubleProf = true
            }
            else if(!binding.SecondProficiencyCheck.isChecked && binding.AddProficiencyCheck.isChecked){
                binding.SecondProficiencyCheck.isChecked = false
                prof = true
                doubleProf = false
            }
            else if(!binding.SecondProficiencyCheck.isChecked && !binding.AddProficiencyCheck.isChecked){
                binding.SecondProficiencyCheck.isChecked = false
                halfProf = false
            }
            refreshInitiative(viewModel.getCharacter().value!!)
        }

        binding.MiscBonusValue.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                refreshInitiative(viewModel.getCharacter().value!!)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })


        binding.SaveButton.setOnClickListener {
            //val test: Int = binding.MiscBonusValue.text.toString().toInt()
            viewModel.changeCharactersInitiative(if (binding.MiscBonusValue.text.toString() != "")
                binding.MiscBonusValue.text.toString().toInt() else 0, prof, halfProf, doubleProf, additionalStatBonus)
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
        binding.BaseInitiativeValue.setText((viewModel.calcModifier(character.dexterity)))
        binding.MiscBonusValue.setText(character.miscInitiativeBonus.toString())

    }

    private fun refreshInitiative(character: Character){

        binding.InitiativeValue.text = viewModel.calcInitiative(viewModel.calcModifier(
            character.dexterity).toInt(),
            //ИСПРАВИТЬ ВЕЗДЕ ЕСЛИ ПУСТОТА ТО КРАШ
            if(binding.MiscBonusValue.text.toString() != "") binding.MiscBonusValue.text.toString().toInt() else 0,
            prof,
            halfProf,
            doubleProf,
            additionalStatBonus,
            character.proficiency
        ).toString()
    }


    private fun loadChar(character: Character) {
        prof = character.initiativeProf
        halfProf = character.initiativeHalfProf
        doubleProf = character.initiativeDoubleProf
        additionalStatBonus = character.initiativeAdditionalAbility


        if (prof){
            binding.AddProficiencyCheck.isChecked = true
            binding.SecondProficiencyCheck.text = resources.getString(R.string.doubleProf)
        }
        if (halfProf){
            binding.SecondProficiencyCheck.isChecked = true
            binding.SecondProficiencyCheck.text = resources.getString(R.string.halfProf)
        }
        if (doubleProf){
            binding.SecondProficiencyCheck.isChecked = true
            binding.AddProficiencyCheck.isChecked = true
            binding.SecondProficiencyCheck.text = resources.getString(R.string.doubleProf)
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        additionalStatBonus = position
        refreshInitiative(viewModel.getCharacter().value!!)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}
