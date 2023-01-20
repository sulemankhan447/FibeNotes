package com.fibenotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fibenotes.database.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {

    var notesLiveData: LiveData<List<Note>> = MutableLiveData()


    init {
        viewModelScope.launch {
            notesLiveData = notesRepository.fetchAllNotes()
        }
    }

    fun createNote(request: Note) {
        viewModelScope.launch {
            notesRepository.createNote(request)
        }
    }
    fun updateNote(request: Note) {
        viewModelScope.launch {
            notesRepository.updateNote(request)
        }
    }
    fun deleteNote(request: Note) {
        viewModelScope.launch {
            notesRepository.deleteNote(request)
        }
    }


}