package com.example.proyectofinal_v2.presentation.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.proyectofinal_v2.components.ShowError
import com.example.proyectofinal_v2.domain.model.CharacterModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    characterListViewModel: CharacterListViewModel = koinViewModel(),
    onItemClick: (Int) -> Unit,

    ) {
    val state = characterListViewModel.characterList.observeAsState()

    val errorState = characterListViewModel.errorMessage.observeAsState()

    if (errorState.value?.isNotEmpty() == true) {
        val error = errorState.value
        ShowError(error = error ?: "")
    }
    val result: List<CharacterModel>? = state.value

    if (result != null) {
        Scaffold(
            topBar = {
                TopAppBar(

                    title = {
                        Text(
                            "Menú",
                            color = Color.Black
                        )

                        Row {
                            IconButton(onClick = {}) {
                                Icon(
                                    modifier=Modifier.semantics
                                    { contentDescription = "Icono de favoritos" },
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Favoritos"

                                )
                            }
                        }

                    },
                    backgroundColor = Color.Gray,

                    )
            }) {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .padding(vertical = 5.dp)
                    ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val characterList = state.value
                items(characterList?.size ?: 0) { i ->

                    val item = characterList?.get(i)
                    item?.let { character ->
                        ShowCharacterList(character = character) {
                            onItemClick.invoke(character.id)
                        }

                    }
                }
            }
        }
    } else {

        //hasta que tenga datos
        ShowError(error = "Cargando")
    }

}