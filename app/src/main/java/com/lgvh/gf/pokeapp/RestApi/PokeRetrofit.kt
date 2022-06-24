package com.lgvh.gf.pokeapp.RestApi

import com.lgvh.gf.pokeapp.RestApi.InfoPokemon.InfoPokemonResponse
import com.lgvh.gf.pokeapp.RestApi.abilities.AbilitiesResponse
import com.lgvh.gf.pokeapp.RestApi.evolve.EvolveResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://pokeapi.co/api/v2/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

     fun getRetrofit() : Retrofit {
         return retrofit
     }

     interface PokeApiService {
         @GET("pokemon?limit=151")
         suspend fun getKantoPokemons(): PokemonsResponse

         //https://pokeapi.co/api/v2/pokemon-species/charmander
         @GET("pokemon-species/{name}/")
         suspend fun getInfoPokemon(@Path("name") name: String): InfoPokemonResponse

         //https://pokeapi.co/api/v2/pokemon/2/ -> url viene en la primer consulta
         @GET("pokemon/{url}")
         suspend fun getAbilities(@Path("url") url : String): AbilitiesResponse

         //https://pokeapi.co/api/v2/evolution-chain/2/ -> detalle de la info
         @GET("evolution-chain/{num}/")
         suspend fun getEvolutionChain(@Path("num") num : String): EvolveResponse
     }

    interface InfoApiService {

    }

     object PokeApi {
         val retrofitService : PokeApiService by lazy { retrofit.create(PokeApiService::class.java) }
     }

    object InfoApi {
        val retrofitService : InfoApiService by lazy { retrofit.create(InfoApiService::class.java) }
    }

