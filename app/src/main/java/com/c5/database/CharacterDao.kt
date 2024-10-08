package com.c5.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.c5.database.CharacterEntity


@Dao
interface CharacterDao {
    @Insert
    suspend fun insert(character: CharacterEntity)

    @Delete
    suspend fun delete(character: CharacterEntity)

    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): LiveData<List<CharacterEntity>>
}