package com.example.proyectofinal_v2.data.local

import com.example.proyectofinal_v2.data.local.model.CharacterLocal
import com.example.proyectofinal_v2.data.remote.dto.CharacterDto
import retrofit2.Call

class LocalDataSourceImpl (
    private val characterDao : CharacterDao
) : LocalDataSource{
    override suspend fun getCharacterList(): List<CharacterLocal> = characterDao.getAll()

    override suspend fun insertCharacterList(characterList: List<CharacterLocal>) = characterDao.insertAll(characterList)

    override suspend fun getCharacterById(id: String): CharacterLocal = characterDao.getCharacterById(id)


}