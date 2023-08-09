package com.example.proyectofinal_v2.domain.model

import com.example.proyectofinal_v2.CharacterTestDataBuilder
import com.example.proyectofinal_v2.data.remote.dto.LocationDto
import com.example.proyectofinal_v2.data.remote.dto.OriginDto
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class CharacterModelTest {

    // val id : Int,
    //    val name : String,
    //    val status : String,
    //    val type : String,
    //    val gender: String,
    //    val image : String,
    //    val location: String,
    //    val origin : String



    var characterModel = CharacterModel(
       1,
        "name",
        "status",
        "type",
        "gender",
        "https://image",
        "location",
        "origin"
    )

    @Test
    fun `WHEN create model EXPECT not null value`() {
        MatcherAssert.assertThat(characterModel, CoreMatchers.instanceOf(CharacterModel::class.java))
        MatcherAssert.assertThat(characterModel, CoreMatchers.notNullValue())
    }

    @Test
    fun `WHEN characterModel id is 1 EXPECT id = 1`() {
        val character= CharacterTestDataBuilder()
            .buildSingle()
        MatcherAssert.assertThat(character.id, CoreMatchers.`is`(1))
    }

    @Test
    fun `WHEN characterModel name is Alex EXPECT name = Alex`() {
        val character = CharacterTestDataBuilder()
            .withName("Alex")
            .buildSingle()
        MatcherAssert.assertThat(character.name, CoreMatchers.`is`("Alex"))
    }

    @Test
    fun `WHEN creates characterModel EXPECT photoUrl contains schema`() {
        MatcherAssert.assertThat(characterModel.image, characterModel.image.startsWith("https"))
    }

}