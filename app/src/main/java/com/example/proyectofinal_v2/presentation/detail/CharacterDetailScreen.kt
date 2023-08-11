package com.example.proyectofinal_v2.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.proyectofinal_v2.components.ShowError
import com.example.proyectofinal_v2.presentation.theme.PrimaryGreen
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CharacterDetailScreen(
    id: Int,
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
            Modifier.background(MaterialTheme.colors.onPrimary),
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.onBackground,
                    title={
                        Text(text="Detalle de ${character.name}",
                            color = PrimaryGreen,)
                    },
                    navigationIcon = {
                        IconButton(
                            modifier = Modifier.semantics { 
                                contentDescription = "Botón atrás Ir al listado de personajes"
                            },
                            onClick = onBack
                        ){
                            Icon(tint= PrimaryGreen,
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Flecha atrás")
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