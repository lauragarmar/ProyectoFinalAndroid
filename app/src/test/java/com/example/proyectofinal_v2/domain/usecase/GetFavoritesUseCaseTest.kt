package com.example.proyectofinal_v2.domain.usecase

import com.example.proyectofinal_v2.data.CharacterRepository
import com.example.proyectofinal_v2.domain.model.CharacterModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

@ExperimentalCoroutinesApi
class GetFavoritesUseCaseTest {

    @MockK(relaxed = true)
    private lateinit var characterRepository: CharacterRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `WHEN request favorite EXPECT null`() = runTest {
        val getFavoritesUseCase = GetFavoritesUseCase(characterRepository)
        coEvery { characterRepository.getCharacterByFavorites(any()) } returns emptyList()
        val result = getFavoritesUseCase.invoke(true)
        assertEquals(result, emptyList<CharacterModel>())
    }

    @Test
    fun `WHEN request favorite EXPECT list of characters`() = runTest {
        val getFavoritesUseCase = GetFavoritesUseCase(characterRepository)
        val character = CharacterModel(
            1,
            "name",
            "status",
            "type",
            "gender",
            "https://image",
            "location",
            "origin",
            false
        )

        coEvery { characterRepository.getCharacterByFavorites(eq(true)) } returns listOf(character)

        val result = getFavoritesUseCase.invoke(true)

        assertEquals(result, listOf(character) )

        coVerify { characterRepository.getCharacterByFavorites(any()) }
    }

    @Test
    fun `WHEN request favorite not EXPECT list of characters`() = runTest {
        val getFavoritesUseCase = GetFavoritesUseCase(characterRepository)
        val character = CharacterModel(
            1,
            "name",
            "status",
            "type",
            "gender",
            "https://image",
            "location",
            "origin",
            false
        )

        coEvery { characterRepository.getCharacterByFavorites(eq(false)) } returns listOf(character)

        val result = getFavoritesUseCase.invoke(false)

        assertEquals(result, listOf(character) )

        coVerify { characterRepository.getCharacterByFavorites(any()) }
    }
}
