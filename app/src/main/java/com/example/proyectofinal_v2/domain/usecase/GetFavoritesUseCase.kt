package com.example.proyectofinal_v2.domain.usecase

import com.example.proyectofinal_v2.data.CharacterRepository
import com.example.proyectofinal_v2.domain.model.CharacterModel

class GetFavoritesUseCase (
    private val characterRepository : CharacterRepository
) {

    suspend fun invoke (favorite: Boolean) : List<CharacterModel> = characterRepository.getCharacterByFavorites(favorite = favorite )
}