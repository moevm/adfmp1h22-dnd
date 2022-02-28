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
import com.dwards.a5edpockethelper.databinding.CharacterListBinding


class CharacterList : DialogFragment() {

    private var _binding: CharacterListBinding? = null
    private val binding get() = _binding!!


    private lateinit var characterAdapter: CharacterListAdapter
    private val TAG = "Character List Dialog"

    private lateinit var characterList: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner);
        _binding = CharacterListBinding.inflate(inflater, container, false)
        val view = binding.root

        characterList = binding.charRecycler
        //var layoutManager: LinearLayoutManager = LinearLayoutManager(activity)


        val viewModel =  ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        viewModel.getAllCharacters().observe(viewLifecycleOwner, Observer {
            it?.let {
                characterAdapter = CharacterListAdapter(it)
                characterList.apply{
                layoutManager = LinearLayoutManager(activity);
                adapter = characterAdapter
                    addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                }
                //refreshChar(it)
            }
        })

        return view
    }


}
