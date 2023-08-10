package com.example.proyectofinal_v2.domain.usecase

import com.example.proyectofinal_v2.data.CharacterRepository
import com.example.proyectofinal_v2.domain.model.CharacterModel

class SearchUseCase (
    private val characterRepository : CharacterRepository
) {

    //suspend fun invoke (name : String) : List<CharacterModel> = characterRepository.searchCharacter(name)
}