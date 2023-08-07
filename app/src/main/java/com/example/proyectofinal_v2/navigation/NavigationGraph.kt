package com.example.proyectofinal_v2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationGraph(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.CharacterListScreen.route ){
        addCharacterListScreen(navController)
    }
}