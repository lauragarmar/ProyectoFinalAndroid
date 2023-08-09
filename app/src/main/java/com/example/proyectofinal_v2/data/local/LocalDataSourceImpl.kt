package com.example.proyectofinal_v2.data.local

import com.example.proyectofinal_v2.data.local.model.CharacterLocal

//Implementamos la interfaz de LocalDataSource
class LocalDataSourceImpl (
    private val characterDao : CharacterDao
) : LocalDataSource{
    //implementamos los métodos que definimos en la interfaz de CharacterDao: getall,
    // insertall y getcharacterbyid
    override suspend fun getCharacterList(): List<CharacterLocal> = characterDao.getAll()

    override suspend fun insertCharacterList(characterList: List<CharacterLocal>) = characterDao.insertAll(characterList)

    override suspend fun getCharacterById(id: Int): CharacterLocal = characterDao.getCharacterById(id)
    override suspend fun getCharacterByFavorites(favorite: Boolean): List<CharacterLocal> {
      return characterDao.getCharacterByFavorites(favorite)
    }

    override suspend fun setFavorite(id: Int, favorite: Boolean) {
        characterDao.setFavorite(id= id, favorite = favorite)
    }

}