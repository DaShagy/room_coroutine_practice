package com.dashagy.roomcoroutinespractice.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dashagy.roomcoroutinespractice.R
import com.dashagy.roomcoroutinespractice.data.NotesDatabase
import com.dashagy.roomcoroutinespractice.data.repositories.NoteRepositoryImpl
import com.dashagy.roomcoroutinespractice.domain.entities.Note
import com.dashagy.roomcoroutinespractice.domain.usecases.DeleteNoteUseCase
import com.dashagy.roomcoroutinespractice.domain.usecases.GetNoteByIdUseCase
import com.dashagy.roomcoroutinespractice.domain.usecases.GetNotesUseCase
import com.dashagy.roomcoroutinespractice.domain.usecases.InsertNoteUseCase
import com.dashagy.roomcoroutinespractice.presentation.adapters.NoteListAdapter
import com.dashagy.roomcoroutinespractice.presentation.util.NoteState
import com.dashagy.roomcoroutinespractice.presentation.viewmodel.NoteViewModel
import com.dashagy.roomcoroutinespractice.presentation.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    /*private val database by lazy { NotesDatabase.getDatabase(this) }
    private val repository by lazy { NoteRepositoryImpl(database.noteDao) }

    private val insertNoteUseCase by lazy { InsertNoteUseCase(repository) }
    private val getNotesUseCase by lazy { GetNotesUseCase(repository) }
    private val getNoteByIdUseCase by lazy { GetNoteByIdUseCase(repository) }
    private val deleteNoteUseCase by lazy { DeleteNoteUseCase(repository) }

    private val viewModelFactory by lazy {
        NoteViewModelFactory(
            insertNote = insertNoteUseCase,
            getNotes = getNotesUseCase,
            getNoteById = getNoteByIdUseCase,
            deleteNote = deleteNoteUseCase
        )
    }
    private lateinit var viewModel: NoteViewModel

    private lateinit var adapter: NoteListAdapter*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*viewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)

        adapter = NoteListAdapter(
            mutableListOf(),
            { onClickNoteListItem(it) },
            { onClickNoteListItemDeleteButton(it)}
        )

        val recyclerView = findViewById<RecyclerView>(R.id.notesList)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        val titleEditText = findViewById<EditText>(R.id.editTextTitle)
        val contentEditText = findViewById<EditText>(R.id.editTextContent)
        val insertNoteButton = findViewById<Button>(R.id.btnInsertNote)
        val cancelNoteButton = findViewById<Button>(R.id.btnCancelEdit)

        viewModel.notesList.observe(this, ::updateUI)
        viewModel.noteTitle.observe(this){
            titleEditText.setText(it)
        }
        viewModel.noteContent.observe(this){
            contentEditText.setText(it)
        }
        viewModel.state.observe(this){ noteState ->
            when (noteState){
                NoteState.CreateNoteState -> {
                    insertNoteButton.text = "CREATE NOTE"
                    cancelNoteButton.visibility = View.GONE
                }
                NoteState.EditNoteState -> {
                    insertNoteButton.text = "EDIT NOTE"
                    cancelNoteButton.visibility = View.VISIBLE
                }
            }
        }

        insertNoteButton.setOnClickListener{
            onClickInsertNote(
                titleEditText.text.toString(),
                contentEditText.text.toString()
            )
        }

        cancelNoteButton.setOnClickListener{
            onClickCancelButton()
        }*/
    }

        /*private fun onClickNoteListItemDeleteButton(it: Note) {
            viewModel.deleteSelectedNote(it)
        }

        private fun onClickCancelButton() {
            viewModel.updateState(NoteState.CreateNoteState)
            viewModel.update("", "")
        }

        private fun updateUI(list: List<Note>) {
            adapter.updateAdapterDataset(list as MutableList<Note>)
            adapter.notifyDataSetChanged()
        }

        private fun onClickInsertNote(title: String, content: String) {
            if (title.isNotBlank()) {
                viewModel.insertNoteIntoDb(title, content)
                viewModel.update("", "")
            } else {
                Toast.makeText(
                    this,
                    "El campo titulo no debe estar vacio",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        private fun onClickNoteListItem(note: Note){
            note.id?.let { viewModel.getNoteToEdit(it) }
        }*/
}