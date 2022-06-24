package com.lgvh.gf.pokeapp.home.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lgvh.gf.pokeapp.roomdb.PokemonDBDao

class PokemonsViewModelFactory(private val pokemonDB: PokemonDBDao,
                               private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonsViewModel::class.java)) {
            return PokemonsViewModel(pokemonDB, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}