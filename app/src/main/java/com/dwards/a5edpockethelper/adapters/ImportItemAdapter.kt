package com.dwards.a5edpockethelper.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.utils.SpellEntity

class ImportItemAdapter(
    private val spellEntities: List<SpellEntity>,
    private val itemChooseHandler: (id: String, name: String) -> Unit
) : RecyclerView.Adapter<ImportItemAdapter.ImportItemVh>() {

    class ImportItemVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ImportItemVh {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.custom_import_item, viewGroup, false)

        return ImportItemVh(view)
    }

    override fun onBindViewHolder(viewHolder: ImportItemVh, position: Int) {
        val name = spellEntities[position].name
        val id = spellEntities[position].id
        viewHolder.textView.text = name
        viewHolder.textView.setOnClickListener { itemChooseHandler.invoke(id, name) }
    }

    override fun getItemCount(): Int = spellEntities.size

}