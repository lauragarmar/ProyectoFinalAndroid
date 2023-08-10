package com.example.proyectofinal_v2.presentation.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proyectofinal_v2.components.StarComponent
import com.example.proyectofinal_v2.domain.model.CharacterModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun ShowCharacterList(
    characterListViewModel: CharacterListViewModel,
    character: CharacterModel,
    onClick: () -> Unit
) {
    var starred by rememberSaveable {
        mutableStateOf(character.favorite)
    }

    Card(
        modifier = Modifier.padding(10.dp),

        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .clickable { onClick.invoke() },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column( //nombre
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(100.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .build(), contentDescription = "Imagen del personaje"
                )
               Column(
                    modifier = Modifier.weight(1f)
                        .padding(top=10.dp),

                   horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = character.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.size(10.dp)
                        //modifier= Modifier.semantics { contentDescription = character.name }
                    )

                }

                // Star
                AndroidView(
                    modifier = Modifier
                        //.background(if (starred) Color.DarkGray else Color.LightGray)
                        .clickable {
                            val newState = !starred
                            starred = newState
                            characterListViewModel.fav(character.id, starred)
                        }
                        .size(48.dp),

                    factory = { context ->
                        StarComponent(context).apply {
                            this.checked = starred
                        }
                    },
                    update = {
                        it.checked = starred
                    }
                )


            }
        }
    } //final card
}


