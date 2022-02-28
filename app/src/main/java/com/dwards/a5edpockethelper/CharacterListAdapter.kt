package com.dwards.a5edpockethelper

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dwards.a5edpockethelper.databinding.CharacterListRecyclerBinding
import com.dwards.a5edpockethelper.model.Character


class CharacterListAdapter(charArrayList: List<Character?>, private val listener: RecyclerViewClickListener): Adapter<CharacterListAdapter.CharactersViewHolder>() {
   // private var _binding: CharacterListRecyclerBinding? = null
    //private val binding get() = _binding!!
    private var characterArrayList: List<Character?>
    init{
        characterArrayList = charArrayList


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val charBinding = CharacterListRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return CharactersViewHolder(charBinding, listener)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        var character: Character? = characterArrayList[position]
        holder.bind(character)


    }


    override fun getItemCount(): Int {
        return characterArrayList.size;
    }


    class CharactersViewHolder(private val itemBinding: CharacterListRecyclerBinding, private val listener: RecyclerViewClickListener) : RecyclerView.ViewHolder(itemBinding.root) {
        private var characterId: Int = 0
        init{

            itemBinding.characterLayout.setOnClickListener{
                listener.onRecyclerViewItemClickListener(itemBinding.characterLayout, characterId)
            }

            itemBinding.deleteIcon.setOnClickListener{
                listener.onRecyclerViewItemClickListener(itemBinding.deleteIcon, characterId)

            }
        }


        fun bind(character: Character?) {
            itemBinding.characterName.text = (character?.name + ", ")
            itemBinding.characterClass.text = character?.charClass + " "
            itemBinding.characterLevel.text = "${character?.level}"
            characterId = character?.id!!
        }


    }




}