package com.fibenotes.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {


    @Query("SELECT * from NOTE ORDER BY ID DESC")
    fun fetchAllNotes(): LiveData<List<Note>>

    @Insert
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)


    @Delete
    fun deleteNote(note: Note)
}