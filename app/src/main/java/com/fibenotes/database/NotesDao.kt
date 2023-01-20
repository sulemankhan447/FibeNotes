package com.fibenotes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NotesDao {


    @Query("SELECT * from NOTE")
    fun getAllNotes(): List<Note>

    @Insert
    fun insertNote(note: Note)

}