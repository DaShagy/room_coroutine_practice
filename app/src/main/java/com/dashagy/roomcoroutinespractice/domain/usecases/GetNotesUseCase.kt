package com.dashagy.roomcoroutinespractice.domain.usecases

import com.dashagy.roomcoroutinespractice.domain.entities.Note
import com.dashagy.roomcoroutinespractice.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}