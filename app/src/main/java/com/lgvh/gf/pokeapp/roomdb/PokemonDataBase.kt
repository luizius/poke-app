package com.lgvh.gf.pokeapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PokemonTable::class, InfoTable::class], version = 1, exportSchema = false)
abstract class PokemonDataBase : RoomDatabase() {

    abstract val pokemonDBDao: PokemonDBDao
    abstract val infoDBDao: InfoDBDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonDataBase? = null

        fun getInstance(context: Context): PokemonDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonDataBase::class.java,
                        "pokemon_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}