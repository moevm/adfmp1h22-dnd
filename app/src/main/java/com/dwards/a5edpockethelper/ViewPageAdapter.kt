package com.dwards.a5edpockethelper

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dwards.a5edpockethelper.model.Character

class ViewPageAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        when(position){
            0 -> {
                return CharacterScreen()
            }
            1 -> {
                return CharacterSkills()
            }
            else ->{
                return CharacterScreen()
            }
        }

        //return fragment
    }

}