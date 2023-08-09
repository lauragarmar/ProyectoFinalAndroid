package com.example.proyectofinal_v2.presentation.detail



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.proyectofinal_v2.CharacterTestDataBuilder
import com.example.proyectofinal_v2.domain.usecase.GetDetailUseCase
import com.example.proyectofinal_v2.testutil.DefaultDispatcherRule
import com.example.proyectofinal_v2.testutil.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DetailViewModelTest {
    @get:Rule
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val defaultDispatcherRule: DefaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var getDetailUseCase: GetDetailUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `WHEN detail viewModel getData EXPECT returns data`() = runTest {
        coEvery { getDetailUseCase.invoke(1) } returns
               CharacterTestDataBuilder().buildSingle()

        val viewModel = DetailViewModel(getDetailUseCase)

        viewModel.getCharacter(1)

        val res = viewModel.character.getOrAwaitValue()

        assertThat(res.id, `is`(1))
    }

}