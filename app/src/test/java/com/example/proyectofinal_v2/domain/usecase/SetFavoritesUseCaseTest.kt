package com.example.proyectofinal_v2.domain.usecase

import com.example.proyectofinal_v2.data.CharacterRepository
import com.example.proyectofinal_v2.domain.model.CharacterModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class SetFavoritesUseCaseTest {

    @MockK(relaxed = true)
    private lateinit var characterRepository: CharacterRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    //testeo que es llamado
    @Test
    fun `WHEN request favorite EXPECT setFavorite to be called`() = runTest {
        val setFavoritesUseCase = SetFavoritesUseCase(characterRepository)
        coEvery { characterRepository.setFavorite(any(), eq(true)) } returns Unit
        setFavoritesUseCase.invoke(0,true)
        coVerify {  characterRepository.setFavorite(any(), eq(true))}
    }


}