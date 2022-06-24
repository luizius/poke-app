package com.lgvh.gf.pokeapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lgvh.gf.pokeapp.R

class AbilitiesAdapter(private val abilites: List<String>)
    : RecyclerView.Adapter<AbilitiesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_hability, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AbilitiesAdapter.ViewHolder, position: Int) {
        holder.textName.text = abilites[position]
    }

    override fun getItemCount(): Int {
        return abilites.size
    }
}