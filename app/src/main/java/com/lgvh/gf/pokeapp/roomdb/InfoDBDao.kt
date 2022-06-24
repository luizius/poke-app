package com.lgvh.gf.pokeapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface InfoDBDao {

    @Insert
    fun insert(info: InfoTable)

    @Query("SELECT COUNT(*) FROM info_table")
    fun getRegistro(): Boolean

}