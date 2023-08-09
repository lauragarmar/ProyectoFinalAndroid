package com.example.proyectofinal_v2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proyectofinal_v2.data.local.model.CharacterLocal

//BBDD
@Database(entities= [CharacterLocal::class], version = 1, exportSchema = false)

abstract class CharacterDatabase : RoomDatabase(){
    abstract fun characterDao (): CharacterDao
}