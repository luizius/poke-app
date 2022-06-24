package com.lgvh.gf.pokeapp.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PokemonsInfoModelFactory (private val namePokemon: String,
                                private val urlEvolve: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonInfoViewModel::class.java)) {
            return PokemonInfoViewModel(namePokemon, urlEvolve) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}