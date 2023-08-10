package com.example.proyectofinal_v2.data.remote

import com.example.proyectofinal_v2.data.remote.dto.ResultsDto

import retrofit2.http.GET
import retrofit2.http.Query

interface RickYMortyApi {

    @GET("character")
    suspend fun getCharacterList(): ResultsDto
    @GET("character")
    suspend fun getCharacterByName(@Query("name") name : String): ResultsDto
}
