package com.dashagy.roomcoroutinespractice.presentation.viewmodel

import androidx.lifecycle.*
import com.dashagy.roomcoroutinespractice.domain.entities.Note
import com.dashagy.roomcoroutinespractice.domain.usecases.DeleteNoteUseCase
import com.dashagy.roomcoroutinespractice.domain.usecases.GetNoteByIdUseCase
import com.dashagy.roomcoroutinespractice.domain.usecases.GetNotesUseCase
import com.dashagy.roomcoroutinespractice.domain.usecases.InsertNoteUseCase
import com.dashagy.roomcoroutinespractice.presentation.util.NoteState
import kotlinx.coroutines.launch

class NoteViewModel(
    private val insertNote: InsertNoteUseCase,
    private val getNotes: GetNotesUseCase,
    private val getNoteById: GetNoteByIdUseCase,
    private val deleteNote: DeleteNoteUseCase
) : ViewModel() {

    private var _state : MutableLiveData<NoteState> = MutableLiveData()
    val state get() = _state as LiveData<NoteState>

    private var _noteTitle : MutableLiveData<String> = MutableLiveData()
    val noteTitle get() = _noteTitle as LiveData<String>

    private var _noteContent: MutableLiveData<String> = MutableLiveData()
    val noteContent get() = _noteContent as LiveData<String>

    private var noteId: Int? = null

    val notesList: LiveData<List<Note>> = getNotes().asLiveData()

    fun update(title: String, content: String, id: Int? = null){
        _noteTitle.value = title
        _noteContent.value = content
        noteId = id
    }

    fun insertNoteIntoDb(title: String, content: String) = viewModelScope.launch {
        updateState(NoteState.CreateNoteState)
        update(title, content, noteId)
        insertNote(
            Note(
                title = _noteTitle.value!!,
                content = _noteContent.value!!,
                timestamp = System.currentTimeMillis(),
                id = noteId
            )
        )
    }

    fun getNoteToEdit(id: Int) = viewModelScope.launch {
        getNoteById(id)?.let {
            updateState(NoteState.EditNoteState)
            update(it.title, it.content, it.id)
        }
    }

    fun deleteSelectedNote(note: Note) = viewModelScope.launch {
        deleteNote(note)
    }

    fun updateState(noteState: NoteState) {
        _state.value = noteState
    }
}