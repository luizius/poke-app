package com.lgvh.gf.pokeapp.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class PokemonTable(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String =  "",

    @ColumnInfo(name = "url")
    var url: String =  ""

) {
    constructor() : this(0 , "" , "")
}