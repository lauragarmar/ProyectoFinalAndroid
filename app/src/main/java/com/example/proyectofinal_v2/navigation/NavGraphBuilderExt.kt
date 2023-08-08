package com.example.proyectofinal_v2.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.proyectofinal_v2.presentation.detail.CharacterDetailScreen
import com.example.proyectofinal_v2.presentation.list.CharacterListScreen

fun NavGraphBuilder.addCharacterListScreen(navController: NavHostController) {
    composable(Screen.CharacterListScreen.route) {
        CharacterListScreen { characterId ->
            navController.navigate("${Screen.CharacterListScreen.route}/$characterId")
        }
    }
}

fun NavGraphBuilder.addCharacterDetailScreen(navController : NavHostController){
    composable(
        route = Screen.CharacterListScreen.route + "/{characterId}",
        arguments= Screen.CharacterListScreen.arguments
    ){navBackStackEntry->
        val id= navBackStackEntry.arguments?.getString("characterId") ?: ""
        CharacterDetailScreen(id = id) {
            navController.popBackStack()
        }
    }
}