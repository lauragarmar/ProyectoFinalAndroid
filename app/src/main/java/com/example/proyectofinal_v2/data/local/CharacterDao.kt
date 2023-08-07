package com.example.proyectofinal_v2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectofinal_v2.data.local.model.CharacterLocal

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterTable")
    suspend fun getAll(): List<CharacterLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CharacterLocal>)
}