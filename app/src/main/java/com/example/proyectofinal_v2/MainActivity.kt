package com.example.proyectofinal_v2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofinal_v2.navigation.NavigationGraph
import com.example.proyectofinal_v2.ui.theme.ProyectoFinal_v2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent()
        setContent {
            ProyectoFinal_v2Theme(
                //dynamicColor = false
            ) {
                NavigationGraph()
            }
        }
    }
}

