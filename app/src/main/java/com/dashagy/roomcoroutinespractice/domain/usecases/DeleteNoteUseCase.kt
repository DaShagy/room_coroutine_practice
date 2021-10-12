package com.dashagy.roomcoroutinespractice.domain.usecases

import com.dashagy.roomcoroutinespractice.domain.entities.Note
import com.dashagy.roomcoroutinespractice.domain.repositories.NoteRepository

class DeleteNoteUseCase (
    private val repository: NoteRepository
    ) {

    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}