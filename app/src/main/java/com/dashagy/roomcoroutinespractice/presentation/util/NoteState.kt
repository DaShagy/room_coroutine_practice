package com.dashagy.roomcoroutinespractice.presentation.util

sealed class NoteState{
    object CreateNoteState : NoteState()
    object EditNoteState : NoteState()
}
