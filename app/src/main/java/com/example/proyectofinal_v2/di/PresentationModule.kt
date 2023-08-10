package com.example.proyectofinal_v2.di


import com.example.proyectofinal_v2.presentation.detail.DetailViewModel
import com.example.proyectofinal_v2.presentation.list.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CharacterListViewModel(get(), get(), get(), get()) }
    viewModel { DetailViewModel(get()) }

}