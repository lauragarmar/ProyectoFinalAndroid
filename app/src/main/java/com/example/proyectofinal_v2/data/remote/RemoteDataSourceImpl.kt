package com.example.proyectofinal_v2.data.remote

import android.util.Log
import com.example.proyectofinal_v2.data.remote.dto.CharacterDto

class RemoteDataSourceImpl(
    private val rickYMortyApi: RickYMortyApi
) : RemoteDataSource {

    override suspend fun getCharacterList(): List<CharacterDto> {
        return try {
            val result = rickYMortyApi.getCharacterList().results
            if(result != null)
                result
            else {
                Log.w("", "Not recovering data")
                emptyList()
            }
        } catch (ex: Exception) {
            Log.e("","Error requesting data $ex" )
            emptyList()
        }
    }

}