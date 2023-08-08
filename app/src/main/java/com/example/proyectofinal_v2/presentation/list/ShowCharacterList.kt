package com.example.proyectofinal_v2.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proyectofinal_v2.CharacterTestDataBuilder
import com.example.proyectofinal_v2.components.StarComponent
import com.example.proyectofinal_v2.domain.model.CharacterModel


@Composable
fun ShowCharacterList(
    character: CharacterModel,
    onClick : () -> Unit
) {
    var starred by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier.padding(10.dp),

        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp).fillMaxWidth().clickable { onClick.invoke() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row( //nombre
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(100.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .build(), contentDescription = "Imagen del personaje"
                )
                Column(
                    modifier = Modifier.weight(1f),
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
                }
                // Star
                AndroidView(
                    modifier = Modifier.clickable {
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
        }
    } //final card
}


