package com.dashagy.roomcoroutinespractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dashagy.roomcoroutinespractice.domain.usecases.GetNoteByIdUseCase
import com.dashagy.roomcoroutinespractice.domain.usecases.GetNotesUseCase
import com.dashagy.roomcoroutinespractice.domain.usecases.InsertNoteUseCase
import java.lang.IllegalArgumentException

class NoteViewModelFactory(
    private val insertNote: InsertNoteUseCase,
    private val getNotes: GetNotesUseCase,
    private val getNoteById: GetNoteByIdUseCase
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(insertNote, getNotes, getNoteById) as T
        }

        throw IllegalArgumentException("Wrong dependencies")
    }
}