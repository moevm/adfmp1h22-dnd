package com.dwards.a5edpockethelper.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.adapters.ToolsProficiencyListAdapter
import com.dwards.a5edpockethelper.databinding.ToolsproficiencyListBinding
import com.dwards.a5edpockethelper.interfaces.RecyclerViewClickListener


class ToolsProficiencyList : DialogFragment(), RecyclerViewClickListener {

    private var _binding: ToolsproficiencyListBinding? = null
    private val binding get() = _binding!!


    private lateinit var toolsAdapter: ToolsProficiencyListAdapter
    private val TAG = "Character List Dialog"

    private lateinit var viewModel: MyViewModel
    private lateinit var toolsList: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        _binding = ToolsproficiencyListBinding.inflate(inflater, container, false)
        val view = binding.root

        toolsList = binding.ToolsRecycler


        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        viewModel.getCharacter().observe(viewLifecycleOwner, Observer {
            it?.let {
                toolsAdapter = ToolsProficiencyListAdapter(it.toolsProficiencyList, this)
                toolsList.apply {
                    layoutManager = LinearLayoutManager(activity);
                    adapter = toolsAdapter
                }
            }
        })

        binding.AddToolsButton.setOnClickListener {
            viewModel.addToolsProficiency()
        }

        return view
    }

    override fun onRecyclerViewItemClickListener(view: View, id: Int) {
        when (view.id) {

            R.id.DeleteIcon -> {
                viewModel.deleteToolsProficiency(id)

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
