package com.lgvh.gf.pokeapp.RestApi.evolve

data class EvolveChain(
    val evolves_to: List<EvolutionSecond>,
    val is_baby: Boolean,
    val species:EvolveSpecie
)