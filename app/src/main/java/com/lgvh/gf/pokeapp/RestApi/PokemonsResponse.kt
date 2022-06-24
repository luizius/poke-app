package com.lgvh.gf.pokeapp.RestApi

import com.lgvh.gf.pokeapp.roomdb.PokemonTable

data class PokemonsResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonTable>
) {

}