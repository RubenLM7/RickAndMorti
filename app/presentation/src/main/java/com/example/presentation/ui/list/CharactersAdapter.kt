package com.example.presentation.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Characters
import com.example.presentation.databinding.ItemCharactersListBinding

class CharactersAdapter(context: Context, private val listener: (Characters) -> Unit) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    private val character = arrayListOf<Characters>()

    private val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val itemBinding = ItemCharactersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(character[position])
    }

    override fun getItemCount(): Int = character.size

    fun setCharacters(list: List<Characters>) {
        character.clear()
        character.addAll(list)
        notifyDataSetChanged()
    }


    inner class CharactersViewHolder(private val itemBinding: ItemCharactersListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(characters: Characters) {
            Glide.with(context).load(characters.image).into(itemBinding.imCharacter)
            itemBinding.tvName.text = characters.name

            itemBinding.lytItem.setOnClickListener {
                listener.invoke(characters)
            }
        }
    }


}