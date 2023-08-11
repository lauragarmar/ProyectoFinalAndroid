package com.example.proyectofinal_v2.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchCharacterBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSearchExecute: () -> Unit
) {
    Box(modifier = Modifier.padding(16.dp)) {
        val searchIcon = @Composable { Icon(Icons.Default.Search, "Icono de búsqueda") }
        val placeHolderText = @Composable { Text(text = "Buscar...") }
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth().alpha(1f),
            leadingIcon = searchIcon,
            keyboardActions = KeyboardActions(),
            placeholder = placeHolderText,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black
            )
        )
    }
}