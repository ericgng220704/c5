package com.c5.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.c5.api.ApiClient
import com.c5.database.AppDatabase
import com.c5.database.CharacterEntity
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application) : AndroidViewModel(application){
    private val characterDao = AppDatabase.getDatabase(application).characterDao()
    val characters: LiveData<List<CharacterEntity>> = characterDao.getAllCharacters()

    fun insertCharacter(character: CharacterEntity) {
        viewModelScope.launch {
            characterDao.insert(character)
            Log.d("CharacterViewModel", "Inserted character: ${character.name}")
        }
    }

    fun deleteCharacter(character: CharacterEntity) {
        viewModelScope.launch {
            characterDao.delete(character)
            Log.d("CharacterViewModel", "Deleted character: ${character.name}")
        }
    }

    // New function to fetch and save API data
    fun fetchAndSaveCharacters() {
        viewModelScope.launch {
            val response = ApiClient.service.getCharacters()
            if (response.isSuccessful) {
                response.body()?.results?.forEach { character ->
                    val characterEntity = CharacterEntity(
                        id = character.id,
                        name = character.name,
                        status = character.status,
                        species = character.species,
                        gender = character.gender,
                        origin = character.origin.name,
                        image = character.image
                    )
                    insertCharacter(characterEntity)
                }
            } else {
                Log.e("CharacterViewModel", "Failed to fetch characters: ${response.errorBody()}")
            }
        }
    }
}