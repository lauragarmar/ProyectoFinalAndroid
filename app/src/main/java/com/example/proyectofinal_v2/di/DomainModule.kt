package com.example.proyectofinal_v2.di

import com.example.proyectofinal_v2.domain.usecase.GetCharacterListUseCase
import com.example.proyectofinal_v2.domain.usecase.GetDetailUseCase
import org.koin.dsl.module

val domainModule = module{
    single{ GetCharacterListUseCase(get())}
    single{GetDetailUseCase(get())}
}