package com.example.proyectofinal_v2.data.remote

import com.example.proyectofinal_v2.data.remote.dto.ResultsDto

import retrofit2.http.GET

interface RickYMortyApi {

    @GET("character")
    suspend fun getCharacterList(): ResultsDto

}
