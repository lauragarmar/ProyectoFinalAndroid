package com.example.proyectofinal_v2.domain.usecase

import com.example.proyectofinal_v2.data.CharacterRepository
import com.example.proyectofinal_v2.domain.model.CharacterModel

class GetDetailUseCase (
    private val characterRepository : CharacterRepository
        ) {

    suspend fun invoke (id: Int) : CharacterModel? = characterRepository.getCharacterById(id)
}