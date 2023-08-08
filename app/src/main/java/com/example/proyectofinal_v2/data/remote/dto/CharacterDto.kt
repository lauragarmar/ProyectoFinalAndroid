package com.example.proyectofinal_v2.data.remote.dto

import com.squareup.moshi.Json

data class CharacterDto (
    @Json(name = "id") val id : Int?,
    @Json (name= "name") val name : String?,
    @Json (name= "status") val status : String?,
    @Json (name = "type") val type: String?,
    @Json (name = "gender") val gender : String?,
    @Json (name = "image") val image : String?,
    @Json (name= "location") val location : LocationDto,
    @Json(name = "origin") val origin : OriginDto,
    @Json (name ="favorite") val favorite: Boolean?
    )

