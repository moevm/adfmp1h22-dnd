package com.dwards.a5edpockethelper.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dwards.a5edpockethelper.databinding.CharacterListRecyclerBinding
import com.dwards.a5edpockethelper.interfaces.RecyclerViewClickListener
import com.dwards.a5edpockethelper.model.Character

class CharacterListAdapter(
    charArrayList: List<Character?>,
    private val listener: RecyclerViewClickListener
) : Adapter<CharacterListAdapter.CharactersViewHolder>() {

    private var characterArrayList: List<Character?> = charArrayList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val charBinding =
            CharacterListRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return CharactersViewHolder(charBinding, listener)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(characterArrayList[position])
    }


    override fun getItemCount(): Int {
        return characterArrayList.size;
    }

    class CharactersViewHolder(
        private val itemBinding: CharacterListRecyclerBinding,
        private val listener: RecyclerViewClickListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        private var characterId: Int = 0

        init {

            itemBinding.characterLayout.setOnClickListener {
                listener.onRecyclerViewItemClickListener(itemBinding.characterLayout, characterId)
            }

            itemBinding.deleteIcon.setOnClickListener {
                listener.onRecyclerViewItemClickListener(itemBinding.deleteIcon, characterId)

            }
        }

        fun bind(character: Character?) {
            itemBinding.characterName.text = "${character?.name}, "
            itemBinding.characterClass.text = "${character?.charClass} "
            itemBinding.characterLevel.text = "${character?.level}"
            characterId = character?.id!!
        }
    }

}