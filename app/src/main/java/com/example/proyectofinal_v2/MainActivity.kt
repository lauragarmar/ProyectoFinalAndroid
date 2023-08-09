package com.example.proyectofinal_v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyectofinal_v2.navigation.NavigationGraph
import com.example.proyectofinal_v2.ui.theme.ProyectoFinal_v2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProyectoFinal_v2Theme(
            ) {
                NavigationGraph()
            }
        }
    }
}

