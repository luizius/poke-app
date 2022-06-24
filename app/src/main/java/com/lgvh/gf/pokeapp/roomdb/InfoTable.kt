package com.lgvh.gf.pokeapp.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "info_table")
data class InfoTable(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "catalog_saved")
    var catalogSaved: Boolean =  false,

    ) {
    constructor() : this(0 , false)
}