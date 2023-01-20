package com.fibenotes

import com.fibenotes.database.Note
import com.fibenotes.database.NotesDao
import javax.inject.Inject

class NotesRepository @Inject constructor(
    val notesDao: NotesDao
) {


    suspend fun createNote(request: Note) {
        notesDao.insertNote(request)
    }

    suspend fun updateNote(request: Note) {

        notesDao.updateNote(request)
    }

    suspend fun deleteNote(request: Note) {

        notesDao.deleteNote(request)
    }

    fun fetchAllNotes() = notesDao.fetchAllNotes()

}
