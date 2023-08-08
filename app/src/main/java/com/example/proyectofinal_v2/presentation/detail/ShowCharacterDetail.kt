package com.example.proyectofinal_v2.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proyectofinal_v2.domain.model.CharacterModel

@Composable
fun ShowCharacterDetail(
    character: CharacterModel
) {
    var starred by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .focusRequester(focusRequester = FocusRequester())
                .focusable(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.image)
                .build(),
            contentDescription = "Imagen de personaje ${character.name}"
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment =Alignment.CenterVertically
        ){
            Column(
                modifier= Modifier.weight(1f),
                horizontalAlignment= Alignment.CenterHorizontally
            ){
                Text(
                    text= character.name,
                    maxLines=1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = character.status,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Localizacion: ${character.location}" ,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Origen: ${character.origin}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}

@Composable
fun AndroidViewTest(character: CharacterModel) {
    var starred by remember {
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



