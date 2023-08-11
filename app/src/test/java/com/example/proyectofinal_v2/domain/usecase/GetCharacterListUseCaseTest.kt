package com.example.proyectofinal_v2.domain.usecase

import com.example.proyectofinal_v2.data.CharacterRepository
import com.example.proyectofinal_v2.data.local.LocalDataSource
import com.example.proyectofinal_v2.data.remote.RemoteDataSource
import com.example.proyectofinal_v2.domain.model.CharacterModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test


@ExperimentalCoroutinesApi
class GetCharacterListUseCaseTest{

    //Mock es un impostor que me crea una clase falsa
    //En esta clase puedo llamar metodos

    @MockK(relaxed = true)
    private lateinit var characterRepository : CharacterRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }
    @Test
    fun `WHEN request character list EXPECT emptyList`() = runTest {
        val getCharacterListUseCase = GetCharacterListUseCase(characterRepository)
        val result = getCharacterListUseCase.invoke()

        assertEquals(result, emptyList<CharacterModel>())
    }

    @Test
    fun `WHEN request character list EXPECT listOf(characterModel)`() = runTest {
        val getCharacterListUseCase = GetCharacterListUseCase(characterRepository)
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
        val characterModels = listOf(character)

        //Le digo que de este mock y de este metodo
        //En vez de llamar a la logica que hace ese metodo
        //Le pongo returns y cada vez que llamo a ese metodo con ese Mock
        //Me devuelve el valor de esa lista
        //El returns tiene que devolver el mismo tipo

        coEvery { characterRepository.getCharacterList() } returns characterModels

        val result = getCharacterListUseCase.invoke()

        assertEquals(result, characterModels)

        coVerify { characterRepository.getCharacterList() }
    }
}