package com.example.proyectofinal_v2.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.proyectofinal_v2.di.baseUrl
import com.example.proyectofinal_v2.testutil.DefaultDispatcherRule
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
class SuperHeroApiTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = DefaultDispatcherRule()


    private lateinit var api: RickYMortyApi

    @Before
    fun setup() {
        api = retrofit.create(RickYMortyApi::class.java)
    }

    @Test
    fun `WHEN request character list EXPECT result`() = runTest {
        val result = api.getCharacterList()

        MatcherAssert.assertThat(result.results?.isNotEmpty(), CoreMatchers.`is`(true))
    }

    @Test
    fun `WHEN request whit search EXPECT items`() = runTest {
        val result = api.getCharacterList()

        MatcherAssert.assertThat(result.results?.size, CoreMatchers.`is`(20))
    }

    companion object {
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            retrofit = Retrofit.Builder()
                // lo ideal es probar contra un entorno estable
                // entorno de QA
                .baseUrl(baseUrl)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                                .apply {
                                    level = HttpLoggingInterceptor.Level.BODY
                                }).build()
                )
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder()
                            .addLast(KotlinJsonAdapterFactory())
                            .build()
                    )
                ).build()
        }
    }
}