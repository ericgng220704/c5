package com.c5.api

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CharacterResponse(
    val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Origin,
    val image: String
)

data class  Origin(
    val name: String,
    val url: String
)
