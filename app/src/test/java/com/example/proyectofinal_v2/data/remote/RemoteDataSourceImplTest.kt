package com.example.proyectofinal_v2.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.proyectofinal_v2.data.locationDto
import com.example.proyectofinal_v2.data.originDto
import com.example.proyectofinal_v2.data.remote.dto.CharacterDto
import com.example.proyectofinal_v2.data.remote.dto.ResultsDto
import com.example.proyectofinal_v2.testutil.DefaultDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceImplTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var rickYMortyApi: RickYMortyApi

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `WHEN getCharacterList EXPECT empty list`() = runTest {
        val remoteDataSource = RemoteDataSourceImpl(rickYMortyApi)
        assertEquals(remoteDataSource.getCharacterList(), emptyList<CharacterDto>())
    }

    @Test
    fun `WHEN getCharacterList EXPECT a list of CharacterDto`() = runTest {
        val remoteDataSource = RemoteDataSourceImpl(rickYMortyApi)
        val characterDto = listOf(
            CharacterDto(1, "name", "status", "type", "gender", "image", locationDto, originDto),
            CharacterDto(2, "name", "status", "type", "gender", "image", locationDto, originDto)
        )
        coEvery { rickYMortyApi.getCharacterList() } returns ResultsDto(characterDto)

        assertEquals(remoteDataSource.getCharacterList(), characterDto)

    }



}