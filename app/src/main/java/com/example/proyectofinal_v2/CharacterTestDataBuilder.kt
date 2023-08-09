package com.example.proyectofinal_v2

import com.example.proyectofinal_v2.domain.model.CharacterModel


class CharacterTestDataBuilder {
    val id = 1
    var name = ""
    var status = ""
    var type = ""
    var gender = ""
    var image = ""
    var location = ""
    var origin = ""
    var numElements: Int = 1


    fun withName (name : String) : CharacterTestDataBuilder{
        this.name= name
        return this
    }

    fun withStatus(status: String) : CharacterTestDataBuilder{
        this.status= status
        return this
    }
    fun withType(type: String) : CharacterTestDataBuilder{
        this.type= type
        return this
    }

    fun withGender(gender: String) : CharacterTestDataBuilder{
        this.gender= gender
        return this
    }

    fun withImage(image: String) : CharacterTestDataBuilder{
        this.image= image
        return this
    }

    fun withLocation(location : String) : CharacterTestDataBuilder{
        this.location=location
        return this
    }

    fun withOrigin(origin : String): CharacterTestDataBuilder{
        this.origin = origin
        return this
    }

    fun withNumElements(numElements: Int): CharacterTestDataBuilder {
        this.numElements = numElements

        return this
    }

    fun buildList() : List<CharacterModel>{
        val list = mutableListOf<CharacterModel>()
        for(i in 0 until numElements) {
            list.add(
                CharacterModel(
                    id,
                    name= name,
                    status= status,
                    type=type,
                    gender= gender,
                    image = image,
                    location= location,
                    origin = origin

                )
            )
        } // fin for

        return list.toList()
    }

    fun buildSingle()= CharacterModel(
        id,
        name= name,
        status= status,
        type=type,
        gender= gender,
        image = image,
        location= location,
        origin = origin
    )

}

