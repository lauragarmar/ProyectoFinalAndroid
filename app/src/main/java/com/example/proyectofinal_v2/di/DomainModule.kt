package com.example.proyectofinal_v2.di

import com.example.proyectofinal_v2.domain.usecase.GetCharacterListUseCase
import com.example.proyectofinal_v2.domain.usecase.GetDetailUseCase
import com.example.proyectofinal_v2.domain.usecase.GetFavoritesUseCase
import com.example.proyectofinal_v2.domain.usecase.SearchUseCase
import com.example.proyectofinal_v2.domain.usecase.SetFavoritesUseCase
import org.koin.dsl.module

val domainModule = module{
    single{GetCharacterListUseCase(get())}
    single{GetDetailUseCase(get())}
    single{GetFavoritesUseCase(get())}
    single{SetFavoritesUseCase(get())}
    single{SearchUseCase(get())}
}