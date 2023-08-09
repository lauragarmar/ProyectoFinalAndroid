package com.example.proyectofinal_v2.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.proyectofinal_v2.data.local.LocalDataSource
import com.example.proyectofinal_v2.data.local.model.CharacterLocal
import com.example.proyectofinal_v2.data.remote.RemoteDataSource
import com.example.proyectofinal_v2.data.remote.dto.CharacterDto
import com.example.proyectofinal_v2.data.remote.dto.LocationDto
import com.example.proyectofinal_v2.data.remote.dto.OriginDto
import com.example.proyectofinal_v2.testutil.DefaultDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterRepositoryImplTest {
    @get:Rule
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val defaultDispatcherRule: DefaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var localDataSource: LocalDataSource

    @MockK(relaxed = true)
    private lateinit var remoteDataSource: RemoteDataSource


    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `WHEN getCharacterList EXPECT local data`() = runTest {
        coEvery { localDataSource.getCharacterList() } returns getListLocal()
        coEvery { remoteDataSource.getCharacterList() } returns listOf<CharacterDto>()

        val repo = CharacterRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )

        val res = repo.getCharacterList()


        MatcherAssert.assertThat(res, CoreMatchers.instanceOf(List::class.java))
        MatcherAssert.assertThat(res.size, CoreMatchers.`is`(2))
    }

    @Test
    fun `WHEN getCharacterList EXPECT remote data`() = runTest {
        coEvery { localDataSource.getCharacterList() } returns listOf<CharacterLocal>()
        coEvery { remoteDataSource.getCharacterList() } returns getListRemote()

        val repo = CharacterRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )

        val res = repo.getCharacterList()

        MatcherAssert.assertThat(res, CoreMatchers.instanceOf(List::class.java))
        MatcherAssert.assertThat(res.size, CoreMatchers.`is`(2))
    }
}

fun getListLocal() = listOf(
    CharacterLocal(1, "name", "status", "type", "gender", "image", "location", "origin", true),
    CharacterLocal(2, "name", "status", "type", "gender", "image", "location", "origin", false)
)

val locationDto = LocationDto("test name")
val originDto = OriginDto("test name")
fun getListRemote() = listOf<CharacterDto>(

    CharacterDto(1, "name", "status", "type", "gender", "image", locationDto, originDto, true),
    CharacterDto(2, "name", "status", "type", "gender", "image", locationDto, originDto, true)
)
