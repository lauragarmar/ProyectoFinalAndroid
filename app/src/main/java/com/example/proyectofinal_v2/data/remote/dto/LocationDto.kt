package com.example.proyectofinal_v2.data.remote.dto

import com.squareup.moshi.Json

data class LocationDto(
    @Json(name= "name") val name : String
)