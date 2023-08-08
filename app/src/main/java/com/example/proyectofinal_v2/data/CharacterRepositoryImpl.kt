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

        if (localData.isNotEmpty()) {
            return localData.map { it?.toCharacterModel() }.filterNotNull()
        } else {
            /*Si está vacía,obtengo los datos de la Api que me vienen de
             remotedatasource y consigo la lista
              Después inserto la lista de caracteres a la localdatasource, lo recorro,
              lo convierto a local y le hago un filtro de nulos*/

            val remoteData = remoteDataSource.getCharacterList()
            localDataSource.insertCharacterList(remoteData.map { it.toCharacterLocal() }
                .filterNotNull())

            return remoteData.map { it.toCharacterModel() }.filterNotNull()
        }
    }

    override suspend fun getCharacterById(id: String): CharacterModel =

        localDataSource.getCharacterById(id).toCharacterModel()!! //fuerzo tema nulos


}