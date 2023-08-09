package com.example.proyectofinal_v2.di

import android.content.Context
import androidx.room.Room
import com.example.proyectofinal_v2.data.CharacterRepository
import com.example.proyectofinal_v2.data.CharacterRepositoryImpl
import com.example.proyectofinal_v2.data.local.CharacterDao
import com.example.proyectofinal_v2.data.local.CharacterDatabase
import com.example.proyectofinal_v2.data.local.LocalDataSource
import com.example.proyectofinal_v2.data.local.LocalDataSourceImpl
import com.example.proyectofinal_v2.data.remote.RemoteDataSource
import com.example.proyectofinal_v2.data.remote.RemoteDataSourceImpl
import com.example.proyectofinal_v2.data.remote.RickYMortyApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val baseUrl = "https://rickandmortyapi.com/api/"

val dataModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single<Moshi> {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    single<LocalDataSource> { LocalDataSourceImpl(get()) }

    single<RickYMortyApi> {
        getRickYMortyApi(get())
    }

    single {
        getDatabase(get())
    }

    single {
        providesCharacterDao(get())
    }

}

private fun getRickYMortyApi(retrofit: Retrofit) =
    retrofit.create(RickYMortyApi::class.java)

private fun getDatabase(context: Context) : CharacterDatabase =
    Room.databaseBuilder(
        context,
        CharacterDatabase::class.java, "db"
    ).build()

private fun providesCharacterDao(db: CharacterDatabase) : CharacterDao =
    db.characterDao()
