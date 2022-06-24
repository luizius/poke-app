package com.lgvh.gf.pokeapp.RestApi.InfoPokemon

data class InfoPokemonResponse(
    val name: String,
    val base_happiness: Int,
    val capture_rate: Int,
    val color: InfoPokemonColor,
    val egg_groups: List<InfoPokemonEgg>,
    val evolution_chain: InfoEvolutionChain
)