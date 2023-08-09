package com.example.proyectofinal_v2.presentation.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.proyectofinal_v2.CharacterTestDataBuilder
import com.example.proyectofinal_v2.domain.usecase.GetCharacterListUseCase
import com.example.proyectofinal_v2.testutil.DefaultDispatcherRule
import com.example.proyectofinal_v2.testutil.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HeroListViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var getCharacterListUseCase: GetCharacterListUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        // Liberar recursos del test
    }

    @Test
    fun `WHEN viewModel init EXPECT data at LiveData`() = runTest {
        coEvery { getCharacterListUseCase.invoke() } returns CharacterTestDataBuilder()
            .withNumElements(20)
            .buildList()

        val viewModel = CharacterListViewModel(getCharacterListUseCase)

        val res = viewModel.characterList.getOrAwaitValue()

        MatcherAssert.assertThat(res.size, CoreMatchers.`is`(20))

    }

}
