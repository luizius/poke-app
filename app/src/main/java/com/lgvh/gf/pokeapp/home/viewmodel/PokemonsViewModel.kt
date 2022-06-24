package com.lgvh.gf.pokeapp.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.lgvh.gf.pokeapp.RestApi.PokeApi
import com.lgvh.gf.pokeapp.home.model.FavPokemon
import com.lgvh.gf.pokeapp.home.model.Pokemon
import com.lgvh.gf.pokeapp.roomdb.PokemonDBDao
import com.lgvh.gf.pokeapp.roomdb.PokemonTable
import com.lgvh.gf.pokeapp.util.LoadStatus
import com.lgvh.gf.pokeapp.util.Prefs
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random


class PokemonsViewModel(val pokemonDB: PokemonDBDao,
                        application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val prefs = Prefs(application)

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private var _allPokemons: List<Pokemon>
    val allPokemons: List<Pokemon>
        get() = _allPokemons

    private val _listPokemons = MutableLiveData<List<Pokemon>>()
    val listPokemon: LiveData<List<Pokemon>>
        get() = _listPokemons

    private val _navigateToDetail = MutableLiveData<Pokemon?>()
    val navigateToDetail: LiveData<Pokemon?>
        get() = _navigateToDetail

    private val _listReady = MutableLiveData<Boolean>()
    val listReady: LiveData<Boolean>
        get() = _listReady

    private val _notifyAdapter = MutableLiveData<Boolean>()
    val notifyAdapter: LiveData<Boolean>
        get() = _notifyAdapter

    init {
        _allPokemons = ArrayList()
        _status.value = LoadStatus.LOADING
        if (prefs.catalogSaved == true) {
            getPokemons()
        } else {
            getRestPokemons()
        }
    }

    private fun getRestPokemons() {
        uiScope.launch {
            try {
                _status.value = LoadStatus.LOADING
                val response = PokeApi.retrofitService.getKantoPokemons()
                buildListPokemons(response.results)

                savePokemons(response.results)
                _status.value = LoadStatus.DONE
                //getPokemons()
                //Log.i("response", response.results[0].name)
            } catch (e: Exception) {
                _status.value = LoadStatus.ERROR
            }
        }
    }

    private fun savePokemons(pokemonsList: List<PokemonTable>) {
        uiScope.launch {
            insert(pokemonsList)
            saveInfo()
        }
    }

    private suspend fun insert(pokemonsList: List<PokemonTable>) {
        withContext(Dispatchers.IO) {
            pokemonDB.insertAll(pokemonsList)
        }
    }

    private suspend fun saveInfo() {
        withContext(Dispatchers.IO) {
            prefs.catalogSaved = true
        }
    }

    private fun getPokemons() {
        uiScope.launch {
            getDBPokemonss()
            _status.value = LoadStatus.DONE

        }
    }

    private suspend fun getDBPokemonss() {
        withContext(Dispatchers.IO) {
            val pokemonsDB = pokemonDB.getAll()
            buildListPokemons(pokemonsDB)
        }
    }

    fun buildListPokemons(pokemonsDB : List<PokemonTable>) {
        _allPokemons = pokemonsDB.map {
            Pokemon(it.id, it.name, it.name, it.url, false, 0,)
        }
        _listPokemons.postValue(_allPokemons)
        _listReady.postValue(true)
    }

    fun displayDetail(pokemon: Pokemon) {
        _navigateToDetail.value = pokemon
    }

    fun navigateComplete() {
        _navigateToDetail.value = null
    }

    fun favSelected(name: String) {

        //_nameSelected.value = name
        _status.value = LoadStatus.LOADING
        uiScope.launch {
            initTimer(1)
                .onCompletion {
                    val random = (1..2).random()
                    val prefix = if (random == 1)  "Favorito - " else "Fail - "
                    val filtered = _allPokemons.find{ it.originaName.contains(name) }
                    val indexPoke = _allPokemons.indexOf(filtered)
                    _allPokemons[indexPoke].index = indexPoke
                    _allPokemons[indexPoke].name = prefix + _allPokemons[indexPoke].name
                    _listPokemons.value = _allPokemons
                    _notifyAdapter.value = true
                    favReset(_allPokemons[indexPoke])
                    _status.value = LoadStatus.DONE
                }
                .collect {  }
        }
    }

    fun favReset(pokemon: Pokemon) {
        uiScope.launch {
            initTimer(5)
                .onCompletion {
                    _allPokemons[pokemon.index].name = _allPokemons[pokemon.index].originaName
                    _listPokemons.value = _allPokemons
                    _notifyAdapter.value = false
                }
                .collect {  }
        }
    }


    private fun initTimer(totalSeconds: Int): Flow<Boolean> =
        (totalSeconds - 1 downTo 0).asFlow()
            .onEach { delay(1000) }
            .onStart { emit(totalSeconds) }
            .transform {}


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun resetListReady() {
       _listReady.value = false
    }

    fun resetNotifyAdapter() {
       _notifyAdapter.value = false
    }


}