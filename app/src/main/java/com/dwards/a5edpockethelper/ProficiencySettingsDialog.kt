package com.dwards.a5edpockethelper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dwards.a5edpockethelper.databinding.ProficiencySettingsDialogBinding

class ProficiencySettingsDialog : DialogFragment() {

    private var _binding: ProficiencySettingsDialogBinding? = null
    private val binding get() = _binding!!

    private val TAG = "MyCustomDialog"

    interface StatChange {
        fun sendStats(statMap: HashMap<String, Int>)
    }

    var OnStatChange: StatChange? = null


    //override fun onAttach(context: Context) {
    //    super.onAttach(context)
    //    OnStatChange = targetFragment as StatChange
    // }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);

        _binding = ProficiencySettingsDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.SaveButton.setOnClickListener {
            //OnStatChange?.sendStats(passageStat());
            //changestat.change(passageStat ())
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