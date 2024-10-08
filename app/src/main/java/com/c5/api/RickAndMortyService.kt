package com.c5.api


import retrofit2.http.GET
import retrofit2.Response

interface RickAndMortyService{
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>
}