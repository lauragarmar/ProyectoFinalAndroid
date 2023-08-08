package com.example.proyectofinal_v2.presentation.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.proyectofinal_v2.components.ShowError
import com.example.proyectofinal_v2.navigation.Screen
import org.koin.androidx.compose.koinViewModel

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CharacterDetailScreen(
    id: String,
    characterDetailViewModel : DetailViewModel = koinViewModel(),
    onBack : () -> Unit
){
    val characterState = characterDetailViewModel.character.observeAsState()
    val errorState = characterDetailViewModel.errorMessage.observeAsState()
    
    characterDetailViewModel.getCharacter(id)
    
    if(errorState.value?.isNotEmpty() == true){
        val error = errorState.value
        ShowError(error = error?: "")
    }
    
    val result = characterState.value
    
    result?.let{ character ->
        Scaffold (
            topBar = {
                TopAppBar(
                    title={
                        Text("Detalle de ${character.name}")
                    },
                    navigationIcon = {
                        IconButton(
                            modifier = Modifier.semantics { 
                                contentDescription = "Botón atrás Ir al listado de personajes"
                            },
                            onClick = onBack
                        ){
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    }
                )
            }
                ){
            ShowCharacterDetail(character = character)
            
        }
    } ?: run{
        ShowError(error = "error")
    }

}