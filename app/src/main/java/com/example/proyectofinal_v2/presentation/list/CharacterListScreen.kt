package com.example.proyectofinal_v2.presentation.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectofinal_v2.components.ShowError
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterListScreen(
    characterListViewModel : CharacterListViewModel = koinViewModel(),
    onItemClick: (String) -> Unit
) {
    val state = characterListViewModel.characterList.observeAsState()

    val errorState = characterListViewModel.errorMessage.observeAsState()

    if(errorState.value?.isNotEmpty() == true){
        val error = errorState.value
        ShowError(error = error ?: "")
    }

    LazyColumn(
        modifier = Modifier.padding(
            vertical = 8.dp
        ),

        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val characterList = state.value
        items(characterList?.size ?: 0){
            i ->

            val item = characterList?.get(i)
            item?.let {character ->
                ShowCharacterList(character = character) {
                    onItemClick.invoke(character.id.toString())
                }

            }
        }
    }
}