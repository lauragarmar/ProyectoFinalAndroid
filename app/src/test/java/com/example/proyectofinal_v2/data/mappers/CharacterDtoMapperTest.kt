package com.example.proyectofinal_v2.data.mappers

import com.example.proyectofinal_v2.data.remote.dto.CharacterDto
import com.example.proyectofinal_v2.data.remote.dto.LocationDto
import com.example.proyectofinal_v2.data.remote.dto.OriginDto
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.Assert.*

class CharacterDtoMapperTest {
    class CharacterDtoMapperTest {

        @Test
        fun `WHEN toCharacterModel with values EXPECT model has value`() {
            val locationDto = LocationDto("test name")
            val characterDto = CharacterDto(
                id = 1,
                name = "Sample Name",
                status = "sample status",
                type = "sample type",
                gender = "sample gender",
                image = "photo-url",
                location = locationDto,
                origin = OriginDto("origin name")
            )
            val res = characterDto.toCharacterModel()

            MatcherAssert.assertThat(res!!.id, CoreMatchers.`is`(1))
        }

        @Test
        fun `WHEN toCharacterModel with null EXPECT empty string`() {
            val characterDto = CharacterDto(
                id = null,
                name = "Sample Name",
                status = "sample status",
                type = "sample type",
                gender = "sample gender",
                image = "photo-url",
                location = LocationDto("location name"),
                origin = OriginDto("origin name")
            )
            val res = characterDto.toCharacterModel()

            assertNull(res)
        }
    }
}