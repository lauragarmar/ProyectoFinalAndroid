package com.example.proyectofinal_v2.data.remote.dto

import com.squareup.moshi.Json

data class CharacterIdDto (
    @Json(name= "id") private val id : String?
)