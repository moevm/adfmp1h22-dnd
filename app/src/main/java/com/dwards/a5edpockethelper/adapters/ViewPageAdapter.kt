package com.dwards.a5edpockethelper.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dwards.a5edpockethelper.fragments.*

class ViewPageAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 -> {
                return CharacterScreen()
            }
            1 -> {
                return CharacterSkills()
            }
            2 -> {
                return CharacterWeapon()
            }
            3 -> {
                return CharacterSpell()
            }
            4 -> {
                return CharacterInventory()
            }
            else -> {
                return CharacterScreen()
            }
        }

        //return fragment
    }

}