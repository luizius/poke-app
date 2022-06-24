package com.lgvh.gf.pokeapp.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EvolutionaryViewModelFactory (private val name: String, private val url: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EvolutionaryViewModel::class.java)) {
            return EvolutionaryViewModel(name, url) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}