package com.dashagy.roomcoroutinespractice.presentation.viewmodel

import androidx.lifecycle.*
import com.dashagy.roomcoroutinespractice.domain.entities.Note
import com.dashagy.roomcoroutinespractice.domain.usecases.GetNotesUseCase
import com.dashagy.roomcoroutinespractice.domain.usecases.InsertNoteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val insertNote: InsertNoteUseCase,
    private val getNotes: GetNotesUseCase
) : ViewModel() {

    private var _noteTitle : MutableLiveData<String> = MutableLiveData()

    private var _noteContent: MutableLiveData<String> = MutableLiveData()

    val notesList: LiveData<List<Note>> = getNotes().asLiveData()

    fun update(title: String, content: String){
        _noteTitle.value = title
        _noteContent.value = content
    }

    fun insertNoteIntoDb() = viewModelScope.launch {
        insertNote(
            Note(
                title = _noteTitle.value!!,
                content = _noteContent.value!!,
                timestamp = System.currentTimeMillis(),
                id = null
            )
        )
    }
}