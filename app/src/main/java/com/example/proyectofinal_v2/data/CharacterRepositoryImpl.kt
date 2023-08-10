package com.example.proyectofinal_v2.data

import com.example.proyectofinal_v2.data.local.LocalDataSource
import com.example.proyectofinal_v2.data.mappers.toCharacterLocal
import com.example.proyectofinal_v2.data.mappers.toCharacterModel
import com.example.proyectofinal_v2.data.remote.RemoteDataSource
import com.example.proyectofinal_v2.domain.model.CharacterModel

class CharacterRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CharacterRepository {
    override suspend fun getCharacterList(): List<CharacterModel> {
        /* Venimos de room (almacenamiento local), comprobamos que no está vacía
        y si no lo está, cogemos la lista, la recorremos y la transformamos en modelo*/

        val localData = localDataSource.getCharacterList()

        return if (localData.isNotEmpty()) {
            localData.map { it.toCharacterModel() }
        } else {
            /*Si está vacía,obtengo los datos de la Api que me vienen de
                 remotedatasource y consigo la lista
                  Después inserto la lista de caracteres a la localdatasource, lo recorro,
                  lo convierto a local y le hago un filtro de nulos*/

            val remoteData = remoteDataSource.getCharacterList()
            localDataSource.insertCharacterList(remoteData.mapNotNull { it.toCharacterLocal() })

            remoteData.mapNotNull { it.toCharacterModel() }
        }
    }

    override suspend fun getCharacterById(id: Int): CharacterModel {
        return localDataSource.getCharacterById(id).toCharacterModel()
    }

    override suspend fun getCharacterByFavorites(favorite: Boolean): List<CharacterModel> {
        return localDataSource.getCharacterByFavorites(favorite = favorite).mapNotNull { it.toCharacterModel() }
    }

    override suspend fun setFavorite(id: Int, favorite: Boolean) {
        localDataSource.setFavorite(id, favorite)
    }

 /*   override suspend fun searchCharacter(name: String): List<CharacterModel> {

        val localData= localDataSource.getCharacterByName(name)

        return if (localData.isNotEmpty()) {
            localData.map { it.toCharacterModel() }
        } else {

            val remoteData = remoteDataSource.getCharacterByName(name)
            remoteData.mapNotNull { it.toCharacterModel() }
        }
    }*/


}