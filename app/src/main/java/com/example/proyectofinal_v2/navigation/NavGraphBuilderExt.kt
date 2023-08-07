package com.example.proyectofinal_v2.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.proyectofinal_v2.presentation.list.CharacterListScreen

fun NavGraphBuilder.addCharacterListScreen(navController: NavHostController) {
    composable(Screen.CharacterListScreen.route) {
        CharacterListScreen { characterId ->
            navController.navigate("${Screen.CharacterListScreen.route}/$characterId")
        }
    }
}