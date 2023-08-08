package com.example.proyectofinal_v2.presentation.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.unit.dp
import com.example.proyectofinal_v2.R
import com.example.proyectofinal_v2.components.ShowError
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    characterListViewModel: CharacterListViewModel = koinViewModel(),
    onItemClick: (String) -> Unit,

    ) {
    val state = characterListViewModel.characterList.observeAsState()

    val errorState = characterListViewModel.errorMessage.observeAsState()

    if (errorState.value?.isNotEmpty() == true) {
        val error = errorState.value
        ShowError(error = error ?: "")
    }
    val result = state.value

    result?.let {
        Scaffold(
            topBar = {
                TopAppBar(

                    title = {
                        Text("Menú",
                        color= Color.Black)
                    },
                backgroundColor= Color.Gray,
                    navigationIcon = {
                        IconButton(onClick={}){

                            Icon(imageVector= Icons.Filled.Star,
                                contentDescription = "Favoritos"

                           )
                        }
                    }

                )
            }) {
            LazyColumn(
                modifier = Modifier
                    .padding(top= 50.dp)
                    .padding(vertical = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val characterList = state.value
                items(characterList?.size ?: 0) { i ->

                    val item = characterList?.get(i)
                    item?.let { character ->
                        ShowCharacterList(character = character) {
                            onItemClick.invoke(character.id.toString())
                        }

                    }
                }
            }
        }
    } ?: run {
        ShowError(error = "error")
    }
}