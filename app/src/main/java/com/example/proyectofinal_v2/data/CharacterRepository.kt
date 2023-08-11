package com.example.proyectofinal_v2.data

import com.example.proyectofinal_v2.domain.model.CharacterModel

interface CharacterRepository {

    suspend fun getCharacterList(): List<CharacterModel>

    suspend fun getCharacterById(id: Int): CharacterModel

    suspend fun getCharacterByFavorites(favorite: Boolean) : List<CharacterModel>
    suspend fun setFavorite(id: Int, favorite: Boolean) : Unit

}