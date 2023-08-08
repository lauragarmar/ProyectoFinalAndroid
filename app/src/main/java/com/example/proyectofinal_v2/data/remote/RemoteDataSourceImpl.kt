package com.example.proyectofinal_v2.data.remote

import com.example.proyectofinal_v2.data.remote.dto.CharacterDto

class RemoteDataSourceImpl(
    private val rickYMortyApi: RickYMortyApi
) : RemoteDataSource {

    override suspend fun getCharacterList(): List<CharacterDto> {
        return rickYMortyApi.getCharacterList().results!!
    }

}