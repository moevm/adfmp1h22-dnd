package com.dwards.a5edpockethelper.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.adapters.CharacterListAdapter
import com.dwards.a5edpockethelper.databinding.CharacterListBinding
import com.dwards.a5edpockethelper.interfaces.RecyclerViewClickListener
import kotlinx.coroutines.runBlocking


class CharacterListDialog : DialogFragment(), RecyclerViewClickListener {

    private var _binding: CharacterListBinding? = null
    private val binding get() = _binding!!


    private lateinit var characterAdapter: CharacterListAdapter
    private val TAG = "Character List Dialog"

    private lateinit var viewModel: MyViewModel
    private lateinit var characterList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        _binding = CharacterListBinding.inflate(inflater, container, false)
        val view = binding.root

        characterList = binding.charRecycler

        viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]
        viewModel.getAllCharacters().observe(viewLifecycleOwner) {
            it?.let {
                characterAdapter = CharacterListAdapter(it, this)
                characterList.apply {
                    layoutManager = LinearLayoutManager(activity);
                    adapter = characterAdapter
                    addItemDecoration(
                        DividerItemDecoration(
                            activity,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
                //refreshChar(it)
            }
        }

        binding.AddCharacterButton.setOnClickListener {
            runBlocking {
                viewModel.addCharacter()
            }
        }

        return view
    }

    override fun onRecyclerViewItemClickListener(view: View, id: Int) {
        when (view.id) {
            R.id.characterLayout -> {
                viewModel.chooseCharacter(id)
            }

            R.id.deleteIcon -> {
                viewModel.deleteCharacter(id)

            }
        }

    }


}
