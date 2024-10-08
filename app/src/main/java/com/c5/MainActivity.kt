package com.c5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.c5.ui.CharacterListScreen
import com.c5.viewmodel.CharacterViewModel

class MainActivity : ComponentActivity() {
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharacterListScreen(viewModel = characterViewModel)
        }
    }
}
