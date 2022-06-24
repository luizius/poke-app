package com.lgvh.gf.pokeapp.home.viewmodel

import androidx.lifecycle.*
import com.lgvh.gf.pokeapp.RestApi.PokeApi
import com.lgvh.gf.pokeapp.RestApi.evolve.EvolveResponse
import com.lgvh.gf.pokeapp.util.LoadStatus
import kotlinx.coroutines.launch

class EvolutionaryViewModel(val namePokemon: String, val url: String): ViewModel() {

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private val _listEvolves = MutableLiveData<EvolveResponse>()
    val listEvolve: LiveData<EvolveResponse>
        get() = _listEvolves

    private val _listStringEvolves = MutableLiveData<List<String>>()
    val listStringEvolves: LiveData<List<String>>
        get() = _listStringEvolves

    init {
        getEvolves()
    }

    private fun getEvolves() {
        viewModelScope.launch {
            try {
                _status.value = LoadStatus.LOADING
                val index = url.indexOf("chain")
                val uri = url.substring(index + "chain/".length,  url.length)

                val response = PokeApi.retrofitService.getEvolutionChain(uri)
                val list = mutableListOf<String>()
                if(response.chain.species.name != "") {
                    list.add(response.chain.species.name)
                    if(response.chain.evolves_to.isNotEmpty()){
                        response.chain.evolves_to.forEach {
                            list.add(it.species.name)
                            if (response.chain.evolves_to[0].evolves_to.isNotEmpty()) {
                                response.chain.evolves_to[0].evolves_to.forEach {
                                    list.add(it.species.name)
                                }
                            }
                        }
                    }
                }

                _listEvolves.value = response
                _listStringEvolves.value = list.toList()
                _status.value = LoadStatus.DONE
            } catch (e: Exception) {
                _status.value = LoadStatus.ERROR
            }
        }
    }

}