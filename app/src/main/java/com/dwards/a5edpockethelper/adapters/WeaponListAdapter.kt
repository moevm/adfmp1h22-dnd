package com.dwards.a5edpockethelper.adapters


import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dwards.a5edpockethelper.MyViewModel
import com.dwards.a5edpockethelper.databinding.WeaponListBinding
import com.dwards.a5edpockethelper.dialogs.ToolsProficiencySettingsDialog
import com.dwards.a5edpockethelper.dialogs.WeaponNameRangeTypesDialog
import com.dwards.a5edpockethelper.interfaces.RecyclerViewClickListener
import com.dwards.a5edpockethelper.model.Weapon

class WeaponListAdapter(
    private var weaponArrayList: List<Weapon?>,
    private val listener: RecyclerViewClickListener
) : Adapter<WeaponListAdapter.WeaponViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder {
        val charBinding =
            WeaponListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeaponViewHolder(charBinding, listener)
    }

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        val weapon: Weapon? = weaponArrayList[position]
        holder.bind(weapon)
    }


    override fun getItemCount(): Int {
        return weaponArrayList.size;
    }

    class WeaponViewHolder(
        private val itemBinding: WeaponListBinding,
        private val listener: RecyclerViewClickListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        private var weaponId: Int = 0
        private var isExpanded = false

        init {
            itemBinding.apply {
                MainWeaponLayout.setOnClickListener {
                    isExpanded = !isExpanded
                    weaponDescription.isVisible = isExpanded
                }
            }

            itemBinding.MainWeaponLayout.setOnLongClickListener {
                val weaponNameRangeTypesDialog: WeaponNameRangeTypesDialog =
                    WeaponNameRangeTypesDialog()
                val args = Bundle()
                args.putInt("num", absoluteAdapterPosition)
                weaponNameRangeTypesDialog.arguments = args
                weaponNameRangeTypesDialog.show(
                    (unwrap(itemView.context) as FragmentActivity).supportFragmentManager,
                    "StatSettingsDialog"
                )
                return@setOnLongClickListener true
            }
        }



        fun bind(weapon: Weapon?) {
            itemBinding.WeaponName.text = weapon?.name
            itemBinding.DamageType.text = weapon?.damageType
            var a = weapon?.statAttackBonus!! + weapon.magicAttackBonus + weapon.miscAttackBonus + weapon.profAttackBnus
            itemBinding.AttackBonus.text = (weapon?.statAttackBonus!! + weapon.magicAttackBonus + weapon.miscAttackBonus + weapon.profAttackBnus).toString()
            itemBinding.AttackDamage.text = if (weapon.hitDice1Count != 0) ("" + weapon.hitDice1Count + "d" +  weapon.hitDice1Size) else "" +
                                            if (weapon.hitDice2Count != 0) ("+" + weapon.hitDice2Count + "d" +  weapon.hitDice2Size) else "" +
                                            if (weapon.hitDice3Count != 0) ("+" + weapon.hitDice3Count + "d" +  weapon.hitDice3Size) else "" +
                                            if (weapon.statDamageBonus + weapon.magicDamageBonus + weapon.miscDamageBonus + weapon.profAttackBnus != 0)
                                                    ("+" + (weapon.statDamageBonus + weapon.magicDamageBonus + weapon.miscDamageBonus + weapon.profAttackBnus)) else ""
            itemBinding.DamageType.text =  weapon?.reach.toString() + "ft."
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