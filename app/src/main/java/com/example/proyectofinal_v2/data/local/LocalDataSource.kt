package com.example.proyectofinal_v2.data.local

import com.example.proyectofinal_v2.data.local.model.CharacterLocal
import com.example.proyectofinal_v2.data.remote.dto.CharacterDto
import retrofit2.Call

interface LocalDataSource {

    suspend fun insertCharacterList(characterList: List<CharacterLocal>)
    suspend fun getCharacterList() : List<CharacterLocal>

}