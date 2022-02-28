package com.dwards.a5edpockethelper

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dwards.a5edpockethelper.model.Character

class ViewPageAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        when(position){
            0 -> {
                return CharacterScreen()
            }
            1 -> {
                return CharacterSkills()
            }
            2 -> {
                return CharacterWeapon()
            }
            else ->{
                return CharacterScreen()
            }
        }

        //return fragment
    }

}