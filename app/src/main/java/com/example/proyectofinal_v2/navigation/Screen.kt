package com.example.proyectofinal_v2.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {

    object CharacterListScreen : Screen(
        route = "CharacterList",
        arguments = emptyList()
    )

    object CharacterDetailScreen: Screen(
        route = "characterDetail",
        arguments= listOf(
            navArgument("characterId"){
                type= NavType.IntType
                nullable= false
            }
        )

    )



}