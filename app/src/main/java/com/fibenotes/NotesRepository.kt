package com.fibenotes

import androidx.lifecycle.MutableLiveData
import com.fibenotes.database.Note
import com.fibenotes.database.NotesDao
import javax.inject.Inject

class NotesRepository @Inject constructor(
    val notesDao: NotesDao
) {


    val notesLiveData = MutableLiveData<DatabaseResult<List<Note>>>()
    val createNoteLiveData = MutableLiveData<DatabaseResult<List<Note>>>()

    suspend fun createNote(request: Note) {

        notesDao.insertNote(request)
    }
    suspend fun updateNote(request: Note) {

        notesDao.updateNote(request)
    }
    suspend fun deleteNote(request: Note) {

        notesDao.deleteNote(request)
    }

    suspend fun fetchAllNotes() = notesDao.fetchAllNotes()

}
