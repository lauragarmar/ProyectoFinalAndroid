package com.example.proyectofinal_v2.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {

    object CharacterListScreen : Screen(
        route = "CharacterList",
        arguments = emptyList()
    )

}