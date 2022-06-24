package com.lgvh.gf.pokeapp.RestApi.evolve

data class EvolutionSecond(
    val evolves_to: List<EvolutionThird>,
    val is_baby: Boolean,
    val species:EvolveSpecie
)