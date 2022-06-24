package com.lgvh.gf.pokeapp.roomdb

import androidx.room.*

@Dao
interface PokemonDBDao {

    @Insert
    fun insertAll(pokemons: List<PokemonTable>)

    @Query("SELECT * FROM pokemon_table")
    fun getAll(): List<PokemonTable>

}