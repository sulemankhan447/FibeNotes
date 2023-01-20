package com.fibenotes

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fibenotes.database.Note
import com.fibenotes.listener.ValidationListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {

    var notesLiveData: LiveData<List<Note>> = MutableLiveData()
    var validationListener: ValidationListener? = null


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

    fun validateNote(note: Note) {
        if (TextUtils.isEmpty(note.title)) {
            validationListener?.onFailure(R.string.please_enter_title_of_note)
            return
        }
        if (TextUtils.isEmpty(note.description)) {
            validationListener?.onFailure(R.string.please_enter_description_of_note)
            return
        }
        validationListener?.onSuccess()
    }


}