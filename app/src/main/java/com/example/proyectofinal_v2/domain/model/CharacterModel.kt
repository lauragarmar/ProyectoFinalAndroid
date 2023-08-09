package com.example.proyectofinal_v2.domain.model

data class CharacterModel(
    val id : Int,
    val name : String,
    val status : String,
    val type : String,
    val gender: String,
    val image : String,
    val location: String,
    val origin : String,
    val favorite : Boolean
)