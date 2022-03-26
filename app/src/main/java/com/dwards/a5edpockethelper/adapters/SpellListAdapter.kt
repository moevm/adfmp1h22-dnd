package com.dwards.a5edpockethelper.adapters


import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.databinding.SpellListBinding
import com.dwards.a5edpockethelper.dialogs.SpeedSettingsDialog
import com.dwards.a5edpockethelper.dialogs.SpellEditDialog
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
        private var spellId: Int = 1
        private var isExpanded = false

        init {
            itemBinding.apply {
                MainSpellLayout.setOnClickListener {
                    //isExpanded = !isExpanded
                    //spellDescription.isVisible = isExpanded
                    listener.onRecyclerViewItemClickListener(MainSpellLayout, spellId)
                }
                MainSpellLayout.setOnLongClickListener{
                    listener.onRecyclerViewItemLongClickListener(MainSpellLayout, spellId)
                    return@setOnLongClickListener true
                }


            }

            itemBinding.favoriteIcon.setOnClickListener {
                listener.onRecyclerViewItemClickListener(itemBinding.favoriteIcon, spellId)

            }
            itemBinding.preparedIcon.setOnClickListener {
                listener.onRecyclerViewItemClickListener(itemBinding.preparedIcon, spellId)

            }


        }

        fun bind(spell: Spell?) {
            itemBinding.spellName.text = spell?.name
            itemBinding.spellSchool.text = spell?.school
            itemBinding.spellCastingType.text = spell?.castingTime
            itemBinding.spellLevel.text = spell?.level.toString()
            itemBinding.spellComponents.text = spell?.components
            spellId = spell?.id!!
            val viewModel = ViewModelProvider(unwrap(itemView.context) as FragmentActivity)[MyViewModel::class.java]

            if (viewModel.isFavoriteSpell(spellId)){
                itemBinding.favoriteIcon.setBackgroundColor(Color.rgb(255,0,0))
            }
            if (viewModel.isPreparedSpell(spellId)){
                itemBinding.preparedIcon.setBackgroundColor(Color.rgb(255,0,0))
            }
        }

        private fun unwrap(context: Context): Activity? {
            var ctx: Context? = context
            while (ctx !is Activity && ctx is ContextWrapper) {
                ctx = ctx.baseContext
            }
            return ctx as Activity?
        }
    }

}