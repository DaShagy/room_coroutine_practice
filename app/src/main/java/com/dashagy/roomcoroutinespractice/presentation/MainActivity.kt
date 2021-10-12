package com.dashagy.roomcoroutinespractice.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dashagy.roomcoroutinespractice.R
import com.dashagy.roomcoroutinespractice.data.NotesDatabase
import com.dashagy.roomcoroutinespractice.data.repositories.NoteRepositoryImpl
import com.dashagy.roomcoroutinespractice.domain.entities.Note
import com.dashagy.roomcoroutinespractice.domain.usecases.GetNotesUseCase
import com.dashagy.roomcoroutinespractice.domain.usecases.InsertNoteUseCase
import com.dashagy.roomcoroutinespractice.presentation.adapters.NoteListAdapter
import com.dashagy.roomcoroutinespractice.presentation.viewmodel.NoteViewModel
import com.dashagy.roomcoroutinespractice.presentation.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private val database by lazy { NotesDatabase.getDatabase(this) }
    private val repository by lazy { NoteRepositoryImpl(database.noteDao) }

    private val insertNoteUseCase by lazy { InsertNoteUseCase(repository) }
    private val getNotes by lazy { GetNotesUseCase(repository) }

    private val viewModelFactory by lazy { NoteViewModelFactory(insertNoteUseCase, getNotes) }
    private lateinit var viewModel: NoteViewModel

    private lateinit var adapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)

        adapter = NoteListAdapter(mutableListOf()){
            Toast.makeText(this, it.title, Toast.LENGTH_LONG).show()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.notesList)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        val titleEditText = findViewById<EditText>(R.id.editTextTitle)
        val contentEditText = findViewById<EditText>(R.id.editTextContent)
        val insertNoteButton = findViewById<Button>(R.id.btnInsertNote)

        viewModel.notesList.observe(this, ::updateUI)

        insertNoteButton.setOnClickListener{
            onClickInsertNote(titleEditText.text.toString(), contentEditText.text.toString())
            titleEditText.setText("")
            contentEditText.setText("")
        }
    }

    private fun updateUI(list: List<Note>) {
        adapter.updateAdapterDataset(list as MutableList<Note>)
        adapter.notifyDataSetChanged()
    }

    private fun onClickInsertNote(title: String, content: String) {
        viewModel.update(title, content)
        viewModel.insertNoteIntoDb()
    }
}