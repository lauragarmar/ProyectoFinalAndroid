package com.example.proyectofinal_v2.data.mappers

import com.example.proyectofinal_v2.data.local.model.CharacterLocal
import com.example.proyectofinal_v2.data.remote.dto.CharacterDto
import com.example.proyectofinal_v2.domain.model.CharacterModel

fun CharacterDto.toCharacterModel(): CharacterModel? {
  return if (id != null) {
        CharacterModel(
            id = this.id,
            name = name ?: "",
            status = status ?: "",
            type = type ?: "",
            gender = gender ?: "",
            image = image ?: "",
            location = location.name ?: "",
            origin= origin.name ?: ""
        )
    } else {
       null

    }
}


fun CharacterDto.toCharacterLocal() =
    if (id != null) {
        CharacterLocal(
            id = this.id,
            name = name ?: "",
            status = status ?: "",
            type = type ?: "",
            gender = gender ?: "",
            image = image ?: "",
            locationName = location.name ?: "",
            originName= origin.name ?: "",
            favorite = favorite ?: false
        )
    } else {
        null

    }


fun CharacterLocal.toCharacterModel() =
    if (id != null) {
        CharacterModel(
            id = this.id,
            name = name ?: "",
            status = status ?: "",
            type = type ?: "",
            gender = gender ?: "",
            image = image ?: "",
            location= locationName ?: "",
            origin = originName ?: ""
        )
    } else {
        null

    }