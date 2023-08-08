package com.example.proyectofinal_v2.data

import com.example.proyectofinal_v2.domain.model.CharacterModel

interface CharacterRepository {

    suspend fun getCharacterList(): List<CharacterModel>

    suspend fun getCharacterById(id: String) : CharacterModel

}