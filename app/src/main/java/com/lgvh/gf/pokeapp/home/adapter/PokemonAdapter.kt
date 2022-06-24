package com.lgvh.gf.pokeapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lgvh.gf.pokeapp.R
import com.lgvh.gf.pokeapp.home.model.Pokemon
import com.lgvh.gf.pokeapp.roomdb.PokemonTable

class PokemonAdapter(private val pokemons: List<Pokemon>,
                     private val onClickListener: OnClickListener
): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_pokemon, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textName.text = pokemons[position].name
        holder.itemView.setOnClickListener {
            onClickListener.onClick(pokemons[position])
        }
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    class OnClickListener(val clickListener: (pokemon: Pokemon) -> Unit) {
        fun onClick(pokemon:Pokemon) = clickListener(pokemon)
    }

    /*fun searchPokemon(name: String, boolVal: Boolean) : Int {
        val filtered = pokemons.filter{ name.contains(it.name) }
        if(filtered.isEmpty()) return 0
        val xx = filtered[0]
        val indexPoke = pokemons.indexOf(xx)
        val prefix = when(boolVal) {
            true -> "Favorito - "
            false -> "Fail - "
        }
        pokemons[indexPoke].name = prefix +  name
        notifyItemChanged(indexPoke)
        return  indexPoke
    }

    fun resetPokemon(name: String, index : Int) {
        if (index == 0) return
        pokemons[index].name = name
        notifyItemChanged(index)
    }*/

}

