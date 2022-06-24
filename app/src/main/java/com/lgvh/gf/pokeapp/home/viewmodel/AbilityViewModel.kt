package com.lgvh.gf.pokeapp.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgvh.gf.pokeapp.RestApi.PokeApi
import com.lgvh.gf.pokeapp.RestApi.abilities.AbilitiesResponse
import com.lgvh.gf.pokeapp.util.LoadStatus
import kotlinx.coroutines.launch

class AbilityViewModel(val namePokemon:String, val url: String,) : ViewModel() {

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    /*private val _listAbilities = MutableLiveData<AbilitiesResponse>()
    val listAbilities: LiveData<AbilitiesResponse>
        get() = _listAbilities*/

    private val _listStringAbilities = MutableLiveData<List<String>>()
    val listStringAbilities: LiveData<List<String>>
        get() = _listStringAbilities

    init {
        getAbilites()
    }

    private fun getAbilites() {
        viewModelScope.launch {
            try {
                _status.value = LoadStatus.LOADING
                val index = url.indexOf("pokemon")
                val uri = url.substring(index + "pokemon/".length,  url.length)

                val response = PokeApi.retrofitService.getAbilities(uri)
                _listStringAbilities.value = response.abilities.map{
                    it.ability.name
                }
                /*
                val list = mutableListOf<String>()
                response.abilities.forEach{
                    list.add(it.ability.name)
                }
                _listAbilities.value = response
                _listStringAbilities.value = list.toList()*/
                _status.value = LoadStatus.DONE
            } catch (e: Exception) {
                _status.value = LoadStatus.ERROR
            }
        }
    }

}