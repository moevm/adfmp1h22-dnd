package com.dwards.a5edpockethelper.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dwards.a5edpockethelper.databinding.WeaponListBinding
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

//            itemBinding.deleteIcon.setOnClickListener {
//                listener.onRecyclerViewItemClickListener(itemBinding.deleteIcon, characterId)
//
//            }
        }

        fun bind(weapon: Weapon?) {
            itemBinding.WeaponName.text = weapon?.name
            itemBinding.DamageType.text = weapon?.damageType
        }
    }

}