package com.example.proyectofinal_v2.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proyectofinal_v2.components.StarComponent
import com.example.proyectofinal_v2.domain.model.CharacterModel
import com.example.proyectofinal_v2.presentation.theme.globalElevation
import com.example.proyectofinal_v2.presentation.theme.globalPadding
import com.example.proyectofinal_v2.presentation.theme.globalRoundedCornerShape

@Composable
fun ShowCharacterDetail(
    character: CharacterModel
) {
    var starred by rememberSaveable {
        mutableStateOf(false)
    }
    Card(
        //modifier = Modifier.padding(globalPadding),
        //elevation = globalElevation,
        //shape = RoundedCornerShape(globalRoundedCornerShape)
    ) {

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(color = Color.Gray),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                   // .clip(CircleShape)
                    .focusRequester(focusRequester = FocusRequester())
                    .focusable()
                    .padding(10.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.image)
                    .build(),
                contentDescription = "Imagen de personaje ${character.name}"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                        .padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = character.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis

                    )
                    Text(
                        text = character.gender,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = character.status,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Localization: ${character.location}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Origin: ${character.origin}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }

        }
    }
}

@Composable
fun AndroidViewTest(character: CharacterModel) {
    var starred by rememberSaveable {
        mutableStateOf(false)
    }
    // Star
    AndroidView(
        modifier = Modifier
            .semantics {
                contentDescription = "Marcar a ${character.name} como favorito"
                stateDescription = if (starred) {
                    "${character.name}  ha sido marcado como favorito"
                } else {
                    "${character.name} ha sido desmarcado como favorito"
                }
            }
            .clickable {
                val newState = !starred
                starred = newState
            },
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



