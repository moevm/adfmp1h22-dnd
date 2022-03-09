package com.dwards.a5edpockethelper.adapters


import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dwards.a5edpockethelper.databinding.ToolsproficiencyListRecyclerBinding
import com.dwards.a5edpockethelper.dialogs.ToolsProficiencySettingsDialog
import com.dwards.a5edpockethelper.interfaces.RecyclerViewClickListener


class ToolsProficiencyListAdapter(
    toolsList: List<String>,
    private val listener: RecyclerViewClickListener
) : Adapter<ToolsProficiencyListAdapter.ToolsProficiencyViewHolder>() {
    private var toolsArrayList: List<String> = toolsList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolsProficiencyViewHolder {
        val toolsBinding = ToolsproficiencyListRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ToolsProficiencyViewHolder(toolsBinding, listener)
    }

    override fun onBindViewHolder(holder: ToolsProficiencyViewHolder, position: Int) {
        holder.bind(toolsArrayList[position])
    }


    override fun getItemCount(): Int {
        return toolsArrayList.size;
    }


    class ToolsProficiencyViewHolder(
        private val itemBinding: ToolsproficiencyListRecyclerBinding,
        private val listener: RecyclerViewClickListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        init {

            itemBinding.ToolsLayout.setOnLongClickListener {

                val toolsProficiencySettingsDialog: ToolsProficiencySettingsDialog =
                    ToolsProficiencySettingsDialog()
                val args = Bundle()
                args.putInt("num", absoluteAdapterPosition)
                toolsProficiencySettingsDialog.arguments = args
                toolsProficiencySettingsDialog.show(
                    (unwrap(itemView.context) as FragmentActivity).supportFragmentManager,
                    "StatSettingsDialog"
                )
                //listener.onRecyclerViewItemClickListener(itemBinding.ToolsLayout, absoluteAdapterPosition)
                return@setOnLongClickListener true
            }

            itemBinding.DeleteIcon.setOnClickListener {
                listener.onRecyclerViewItemClickListener(
                    itemBinding.DeleteIcon,
                    absoluteAdapterPosition
                )
            }
        }

        fun bind(tool: String) {
            itemBinding.ToolsText.text = tool
        }

        private fun unwrap(context: Context): Activity? {
            var context: Context? = context
            while (context !is Activity && context is ContextWrapper) {
                context = context.baseContext
            }
            return context as Activity?
        }
    }


}