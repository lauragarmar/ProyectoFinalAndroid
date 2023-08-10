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
class GetDetailUseCaseTest{

    @MockK(relaxed = true)
    private lateinit var characterRepository : CharacterRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }
    @Test
    fun `WHEN request character EXPECT null`() = runTest {
        val getDetailUseCase = GetDetailUseCase(characterRepository)
        coEvery { characterRepository.getCharacterById(any()) } returns null
        val result = getDetailUseCase.invoke(0)
        assertNull(result)
    }

    @Test
    fun `WHEN request character EXPECT characterModel`() = runTest {
        val getDetailUseCase = GetDetailUseCase(characterRepository)
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

        coEvery { characterRepository.getCharacterById(any()) } returns character

        val result = getDetailUseCase.invoke(0)

        assertEquals(result, character)

        coVerify { characterRepository.getCharacterById(any()) }
    }
}