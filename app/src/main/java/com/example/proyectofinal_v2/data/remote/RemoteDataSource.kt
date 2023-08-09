package com.example.proyectofinal_v2.data.remote

import com.example.proyectofinal_v2.data.remote.dto.CharacterDto

interface RemoteDataSource {

suspend fun getCharacterList(): List<CharacterDto>
}