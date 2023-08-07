package com.example.proyectofinal_v2.data.remote

import com.example.proyectofinal_v2.data.remote.dto.CharacterDto
import retrofit2.Call
import retrofit2.http.GET

interface RemoteDataSource {

suspend fun getCharacterList(): List<CharacterDto>
}