package com.example.proyectofinal_v2.presentation.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectofinal_v2.components.ShowError
import com.example.proyectofinal_v2.domain.model.CharacterModel
import com.example.proyectofinal_v2.presentation.search.SearchCharacterBar
import com.example.proyectofinal_v2.presentation.theme.PrimaryGreen
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
    var searchText by remember { mutableStateOf("") }



    if (result != null) {
        Scaffold(
            Modifier.background(MaterialTheme.colors.onPrimary),
            bottomBar = {
                SearchCharacterBar(
                    value = searchText,
                    onValueChange = { newText ->
                        searchText = newText
                            .replace("\n", "")
                            .replace("\r", "")
                    },
                    onSearchExecute = { searchText = "" }
                )
            },
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.onBackground
                ) {


                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high
                    ) {
                        IconButton(
                            onClick = { characterListViewModel.getData() },
                            modifier = Modifier
                                .semantics {
                                    contentDescription = "Botón atrás para volver al listado"
                                },

                            ) {
                            Icon(
                                tint = PrimaryGreen,
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Flecha atrás"
                            )
                        }
                    }
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                        LocalTextStyle provides MaterialTheme.typography.h6
                    ) {
                        Text(
                            text = "Rick & Morty App",
                            color = PrimaryGreen,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)

                        )
                    }
                    IconButton(onClick = {
                        characterListViewModel.getFavoriteData()
                    }) {
                        Icon(
                            tint = PrimaryGreen,
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Favoritos"

                        )
                    }
                }

            },
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(top = 70.dp)
                     ){
                            val characterList = state.value
                                .orEmpty()
                                .filter {
                                    it.name.contains(
                                        searchText.lowercase(),
                                        ignoreCase = true
                                    )
                                }
                            Log.d(
                                "searchbar",
                                "searchText = $searchText, characterList = $characterList"
                            )
                            items(characterList.size) { i ->

                                val item = characterList[i]
                                item.let { character ->
                                    ShowCharacterList(
                                        character = character,
                                        characterListViewModel = characterListViewModel
                                    ) {
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

