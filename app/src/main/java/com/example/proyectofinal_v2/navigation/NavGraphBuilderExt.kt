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
    ){ navBackStackEntry->
        //println("doing something here ${navBackStackEntry.arguments}")
        val id: String = navBackStackEntry.arguments?.getString("characterId") ?: "0"
        CharacterDetailScreen(id.toInt()) {
            navController.popBackStack()
        }
    }
}

