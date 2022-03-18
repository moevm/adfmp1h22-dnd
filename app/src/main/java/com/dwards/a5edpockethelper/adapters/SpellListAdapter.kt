package com.dwards.a5edpockethelper.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dwards.a5edpockethelper.databinding.SpellListBinding
import com.dwards.a5edpockethelper.interfaces.RecyclerViewClickListener
import com.dwards.a5edpockethelper.model.Spell


class SpellListAdapter(
    private var spellArrayList: List<Spell?>,
    private val listener: RecyclerViewClickListener
) : Adapter<SpellListAdapter.SpellViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val charBinding =
            SpellListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpellViewHolder(charBinding, listener)
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val spell: Spell? = spellArrayList[position]
        holder.bind(spell)
    }


    override fun getItemCount(): Int {
        return spellArrayList.size;
    }

    class SpellViewHolder(
        private val itemBinding: SpellListBinding,
        private val listener: RecyclerViewClickListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        private var spellId: Int = 0
        private var isExpanded = false

        init {
            itemBinding.apply {
                MainSpellLayout.setOnClickListener {
                    isExpanded = !isExpanded
                    spellDescription.isVisible = isExpanded
                }
            }

//            itemBinding.deleteIcon.setOnClickListener {
//                listener.onRecyclerViewItemClickListener(itemBinding.deleteIcon, characterId)
//
//            }
        }

        fun bind(spell: Spell?) {
            itemBinding.SpellName.text = spell?.name
            itemBinding.DamageType.text = spell?.school
        }
    }

}