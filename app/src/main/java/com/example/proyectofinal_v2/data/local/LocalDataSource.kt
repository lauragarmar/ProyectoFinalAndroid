package com.example.proyectofinal_v2.data.local

import com.example.proyectofinal_v2.data.local.model.CharacterLocal

//Datos locales
interface LocalDataSource {

    suspend fun insertCharacterList(characterList: List<CharacterLocal>)
    suspend fun getCharacterList() : List<CharacterLocal>
    suspend fun getCharacterById(id: Int) : CharacterLocal

    suspend fun getCharacterByFavorites(favorite: Boolean) : List<CharacterLocal>

    suspend fun setFavorite(id: Int, favorite: Boolean)

}