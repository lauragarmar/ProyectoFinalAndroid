package com.example.proyectofinal_v2.data.local

import com.example.proyectofinal_v2.data.local.model.CharacterLocal

class LocalDataSourceImpl (
    private val characterDao : CharacterDao
) : LocalDataSource{
    override suspend fun getCharacterList(): List<CharacterLocal> = characterDao.getAll()

    override suspend fun insertCharacterList(characterList: List<CharacterLocal>) = characterDao.insertAll(characterList)

    override suspend fun getCharacterById(id: Int): CharacterLocal = characterDao.getCharacterById(id)


}