package com.dashagy.roomcoroutinespractice.domain.usecases

import com.dashagy.roomcoroutinespractice.domain.entities.Note
import com.dashagy.roomcoroutinespractice.domain.repositories.NoteRepository

class GetNoteByIdUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}