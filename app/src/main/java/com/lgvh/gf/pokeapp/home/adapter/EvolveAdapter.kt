package com.lgvh.gf.pokeapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lgvh.gf.pokeapp.R

class EvolveAdapter(private val evolves: List<String>,
                    private val onClickListener: OnClickListener
): RecyclerView.Adapter<EvolveAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EvolveAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_evolve, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EvolveAdapter.ViewHolder, position: Int) {
        holder.textName.text = evolves[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(evolves[position])
        }
    }

    override fun getItemCount(): Int {
        return evolves.size
    }

    class OnClickListener(val clickListener: (evolve: String) -> Unit) {
        fun onClick(evolve: String) = clickListener(evolve)
    }

}

