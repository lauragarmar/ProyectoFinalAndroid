package com.example.proyectofinal_v2


class CharacterTestDataBuilder {
    val id = "test-id"
    var name = ""
    var status = ""
    var type = ""
    var gender = ""
    var image = ""

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

}

