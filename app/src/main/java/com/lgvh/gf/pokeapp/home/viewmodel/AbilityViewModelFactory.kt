package com.lgvh.gf.pokeapp.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
class AbilityViewModelFactory (private val namePokemon:String, private val url: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AbilityViewModel::class.java)) {
            return AbilityViewModel(namePokemon, url) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}