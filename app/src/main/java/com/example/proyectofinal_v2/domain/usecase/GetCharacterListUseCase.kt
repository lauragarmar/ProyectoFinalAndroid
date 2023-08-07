package com.example.proyectofinal_v2.domain.usecase

import com.example.proyectofinal_v2.data.CharacterRepository

class GetCharacterListUseCase(
    private val characterRepository : CharacterRepository
) {
    suspend fun invoke() = characterRepository.getCharacterList()
}