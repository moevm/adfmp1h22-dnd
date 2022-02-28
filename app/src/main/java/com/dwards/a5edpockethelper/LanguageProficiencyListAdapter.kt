package com.dwards.a5edpockethelper


import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dwards.a5edpockethelper.databinding.LanguageproficiencyListRecyclerBinding


class LanguageProficiencyListAdapter(languageList: List<String>, private val listener: RecyclerViewClickListener): Adapter<LanguageProficiencyListAdapter.LanguageProficiencyViewHolder>() {
    private var languageArrayList: List<String> = languageList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageProficiencyViewHolder {
        val languageBinding = LanguageproficiencyListRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageProficiencyViewHolder(languageBinding, listener)
    }

    override fun onBindViewHolder(holder: LanguageProficiencyViewHolder, position: Int) {
        var language: String = languageArrayList[position]
        holder.bind(language)
    }


    override fun getItemCount(): Int {
        return  languageArrayList.size;
    }


    class LanguageProficiencyViewHolder(private val itemBinding: LanguageproficiencyListRecyclerBinding, private val listener: RecyclerViewClickListener) : RecyclerView.ViewHolder(itemBinding.root) {
        init{

            itemBinding.LanguageLayout.setOnLongClickListener{

                val languageProficiencySettingsDialog: LanguageProficiencySettingsDialog = LanguageProficiencySettingsDialog()
                val args = Bundle()
                args.putInt("num", absoluteAdapterPosition)
                languageProficiencySettingsDialog.arguments = args
                languageProficiencySettingsDialog.show((unwrap(itemView.context) as FragmentActivity).supportFragmentManager, "StatSettingsDialog")
                //listener.onRecyclerViewItemClickListener(itemBinding.LanguageLayout, absoluteAdapterPosition)
                return@setOnLongClickListener true
            }

            itemBinding.DeleteIcon.setOnClickListener{
                listener.onRecyclerViewItemClickListener(itemBinding.DeleteIcon, absoluteAdapterPosition)
            }
        }

        fun bind(language: String) {
            itemBinding.LanguageText.text = language
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