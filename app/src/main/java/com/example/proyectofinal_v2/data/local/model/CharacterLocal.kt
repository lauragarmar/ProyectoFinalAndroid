package com.example.proyectofinal_v2.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//creo las columnas con los datos que quiero almacenar localmente
@Entity(tableName = "CharacterTable")
data class CharacterLocal(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo (name= "type") val type : String,
    @ColumnInfo (name = "gender") val gender: String,
    @ColumnInfo (name = "image") val image : String,
    @ColumnInfo (name= "location_name") val locationName : String,
    @ColumnInfo (name = "origin_name") val originName : String,
    @ColumnInfo(name = "favorite") val favorite: Boolean
)