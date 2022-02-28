package com.dwards.a5edpockethelper

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dwards.a5edpockethelper.databinding.CharacterListRecyclerBinding
import com.dwards.a5edpockethelper.model.Character


class CharacterListAdapter(charArrayList: List<Character?>): Adapter<CharacterListAdapter.CharactersViewHolder>() {
   // private var _binding: CharacterListRecyclerBinding? = null
    //private val binding get() = _binding!!

    private var characterArrayList: List<Character?>
    init{
        characterArrayList = charArrayList


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val charBinding = CharacterListRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return CharactersViewHolder(charBinding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        var character: Character? = characterArrayList[position]
        holder.bind(character)

    }


    override fun getItemCount(): Int {
        return characterArrayList.size;
    }


    class CharactersViewHolder(private val itemBinding: CharacterListRecyclerBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        init{
            //!!!!!!!!!!!! val intent = Intent(itemBinding.root.context, MainActivity::class.java)
            //!!!!!!!!!!!! itemBinding.root.context.startActivity(intent);
            //!!!!!!!!!!!! val viewModel =  ViewModelProvider(itemBinding.root.context).get(MyViewModel::class.java)

            itemBinding.characterLayout.setOnClickListener{
                val positionIndex = bindingAdapterPosition

            }
        }


        fun bind(character: Character?) {
            itemBinding.characterName.text = (character?.name +',')
            itemBinding.characterClass.text = character?.charClass
            itemBinding.characterLevel.text = "${character?.level}"
        }


    }




}