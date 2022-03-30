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
import com.dwards.a5edpockethelper.R
import com.dwards.a5edpockethelper.databinding.WeaponListBinding
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
                val viewModel = ViewModelProvider(unwrap(itemView.context) as FragmentActivity).get(MyViewModel::class.java)
                val weaponNameRangeTypesDialog: WeaponNameRangeTypesDialog = WeaponNameRangeTypesDialog()
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
            itemBinding.DamageType.text = (unwrap(itemView.context) as FragmentActivity).resources.getStringArray(R.array.DamageTypeList)[weapon?.damageTypePosition!!]
            itemBinding.AttackBonus.text = if (weapon?.statAttackBonus!! + weapon.magicAttackBonus + weapon.miscAttackBonus + weapon.profAttackBnus > 0)
                                                "+"+(weapon?.statAttackBonus!! + weapon.magicAttackBonus + weapon.miscAttackBonus + weapon.profAttackBnus).toString() else
                                                (weapon?.statAttackBonus!! + weapon.magicAttackBonus + weapon.miscAttackBonus + weapon.profAttackBnus).toString()
            itemBinding.AttackDamage.text = if (weapon.damageDice1Count != 0) ("" + weapon.damageDice1Count + "d" +  weapon.damageDice1Size) else "" +
                                            if (weapon.damageDice1Count != 0) ("+" + weapon.damageDice1Count + "d" +  weapon.damageDice2Size) else "" +
                                            if (weapon.damageDice1Count != 0) ("+" + weapon.damageDice1Count + "d" +  weapon.damageDice3Size) else "" +
                                            if (weapon.statDamageBonus + weapon.magicDamageBonus + weapon.miscDamageBonus + weapon.profAttackBnus != 0)
                                                    ("+" + (weapon.statDamageBonus + weapon.magicDamageBonus + weapon.miscDamageBonus + weapon.profAttackBnus)) else "0"
            itemBinding.AttackRange.text =  weapon?.range + " ft."
            itemBinding.weaponDescription.text = weapon?.description
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