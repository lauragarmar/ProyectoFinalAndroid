package com.example.proyectofinal_v2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectofinal_v2.data.local.model.CharacterLocal

//Room
// interfaz común entre la aplicación y bbdd
//interfaz con consultas a la base de datos
@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterTable")
    suspend fun getAll(): List<CharacterLocal>

    @Query("SELECT * FROM CharacterTable WHERE id=:id")
    suspend fun getCharacterById(id: Int) :CharacterLocal

    @Query("SELECT * FROM CharacterTable WHERE name like :name")
    suspend fun getCharacterByName(name: String) : List<CharacterLocal>

    @Query("SELECT * FROM CharacterTable WHERE favorite= :favorite")
    suspend fun getCharacterByFavorites(favorite: Boolean):List<CharacterLocal>

    @Query("UPDATE CharacterTable SET favorite = :favorite WHERE id = :id")
    suspend fun setFavorite(id: Int, favorite: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CharacterLocal>)
}