package com.example.proyectofinal_v2.domain.usecase

import com.example.proyectofinal_v2.data.CharacterRepository

class SetFavoritesUseCase (
    val characterRepository: CharacterRepository
        ){
    suspend fun invoke (id:Int, favorite: Boolean) : Unit = characterRepository.setFavorite(id, favorite)

}