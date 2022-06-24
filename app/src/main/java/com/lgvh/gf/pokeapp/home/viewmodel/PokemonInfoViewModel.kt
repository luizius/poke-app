package com.lgvh.gf.pokeapp.home.viewmodel

import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.*
import com.lgvh.gf.pokeapp.RestApi.InfoPokemon.InfoPokemonResponse
import com.lgvh.gf.pokeapp.RestApi.PokeApi
import com.lgvh.gf.pokeapp.util.LoadStatus
import kotlinx.coroutines.launch

class PokemonInfoViewModel(val namePokemon: String, val urlEvolve: String): ViewModel() {

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private val _infoPokemon = MutableLiveData<InfoPokemonResponse>()
    val infoPokemon: LiveData<InfoPokemonResponse>
        get() = _infoPokemon

    private val _eggsString =  MutableLiveData<String>()
    val eggsString: LiveData<String>
        get() = _eggsString

    private val _openEvolve =  MutableLiveData<Boolean>()
    val openEvolve:  LiveData<Boolean>
        get() = _openEvolve

    private val _openAbilities =  MutableLiveData<Boolean>()
    val openAbilities:  LiveData<Boolean>
        get() = _openAbilities

/*    private val _eggsString: LiveData<List<String>> = Transformations.map(_infoPokemon) {
        it.egg_groups.map {
                egg -> egg.name
        }
    }*/

    init {
        getInfoPokemon()

    }

    private fun getInfoPokemon() {
        viewModelScope.launch {
            try {
                _status.value = LoadStatus.LOADING
                val response = PokeApi.retrofitService.getInfoPokemon(namePokemon)
                val textEggs: String = response.egg_groups.map {
                    egg -> egg.name
                }.joinToString { it + ", " }
                _eggsString.value = textEggs.substring(0, textEggs.length-2)
                /*response.egg_groups.forEach{
                    textEggs = textEggs + it.name + ", "
                }*/
                _infoPokemon.value = response
                _status.value = LoadStatus.DONE
            } catch (e: Exception) {
                Log.i("info", e.message.toString())
                _status.value = LoadStatus.ERROR
            }
        }
    }

    fun openAbilities() {
        _openAbilities.value = true
    }

    fun openAbilitiesFinish() {
        _openAbilities.value = false
    }

    fun openEvolve() {
        _openEvolve.value = true
    }

    fun openEvolveFinish() {
        _openEvolve.value = false
    }

}