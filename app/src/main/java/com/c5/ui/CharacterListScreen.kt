package com.c5.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.c5.api.Character
import com.c5.viewmodel.CharacterViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

//@Composable
//fun CharacterListScreen(characters: List<Character>) {
//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(characters) { character ->
//            Row(modifier = Modifier.fillMaxWidth().padding(2.dp, 3.dp)
//                .border(2.dp, Color.Black, shape = RoundedCornerShape(12.dp))
//                .clip(RoundedCornerShape(12.dp))
//                .padding(8.dp)) {
//                Image(
//                    painter = rememberAsyncImagePainter(character.image),
//                    contentDescription = null,
//                    modifier = Modifier.size(100.dp),
//                    contentScale = ContentScale.Crop
//                )
//                Spacer(modifier = Modifier.width(16.dp))
//                Column {
//                    Text(text = character.name)
//                    Text(text = "Species: " + character.species)
//                    Text(text = "Gender: " + character.gender)
//                    Text(text = "Origin: " + character.origin.name)
//                }
//            }
//        }
//    }
//}

@Composable
fun CharacterListScreen(viewModel: CharacterViewModel = viewModel()) {
    val charactersState = viewModel.characters.observeAsState(initial = emptyList())
    val characters = charactersState.value

    Column {
        // Button to fetch and save API characters
        Button(onClick = { viewModel.fetchAndSaveCharacters() }) {
            Text("Fetch & Save Characters")
        }

//        LazyColumn {
//            items(characters) { character ->
//                Text(character.name)
//                Button(onClick = {
//                    viewModel.deleteCharacter(character)
//                }) {
//                    Text("Delete")
//                }
//            }
//        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(characters) { character ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(2.dp, 3.dp)
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                        .padding(8.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(character.image),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = character.name)
                        Text(text = "Species: " + character.species)
                        Text(text = "Gender: " + character.gender)
                        Text(text = "Origin: " + character.origin)

                    }
                    Spacer(Modifier.weight(1f))

                    IconButton(
                        onClick = { viewModel.deleteCharacter(character) }
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_delete),
                            contentDescription = "Delete",
                            tint = Color.Black
                        )
                    }                }
            }
        }
    }
}
