package com.example.proyectofinal_v2.data.remote.dto

import com.squareup.moshi.Json

data class ResultsDto (
    @Json(name = "results") val results : List<CharacterDto>?
)