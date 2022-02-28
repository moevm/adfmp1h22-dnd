package com.dwards.a5edpockethelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dwards.a5edpockethelper.databinding.LanguageproficiencyListBinding


class LanguageProficiencyList : DialogFragment(), RecyclerViewClickListener {

    private var _binding: LanguageproficiencyListBinding? = null
    private val binding get() = _binding!!


    private lateinit var languageAdapter:LanguageProficiencyListAdapter
    private val TAG = "Character List Dialog"

    private lateinit var viewModel: MyViewModel
    private lateinit var languageList: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        _binding = LanguageproficiencyListBinding.inflate(inflater, container, false)
        val view = binding.root

        languageList = binding.LanguageRecycler


        viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                languageAdapter = LanguageProficiencyListAdapter(it.languageProficiencyList, this)
                languageList.apply{
                    layoutManager = LinearLayoutManager(activity);
                    adapter = languageAdapter
                }
            }
        })

        binding.AddLanguageButton.setOnClickListener{
            viewModel.addLanguageProficiency()
        }

        return view
    }

    override fun onRecyclerViewItemClickListener(view: View, id: Int) {
        when (view.id)
        {

            R.id.DeleteIcon ->{
                viewModel.deleteLanguageProficiency(id)

            }
        }

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        //val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)


    }

}
