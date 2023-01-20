package com.fibenotes.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {


    @Query("SELECT * from NOTE ORDER BY ID DESC")
    fun fetchAllNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}