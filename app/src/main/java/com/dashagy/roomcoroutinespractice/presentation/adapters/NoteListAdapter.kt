package com.dashagy.roomcoroutinespractice.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dashagy.roomcoroutinespractice.R
import com.dashagy.roomcoroutinespractice.domain.entities.Note


class NoteListAdapter(
    private var dataset: MutableList<Note>,
    private val onClickListener: (Note) -> Unit
) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    fun updateAdapterDataset(data: MutableList<Note>){
        dataset = data
    }

    inner class NoteViewHolder(
        view: View,
        onItemClickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view){

        val textView: TextView = view.findViewById(R.id.title)

        init {
            view.setOnClickListener {
                onItemClickListener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notes_list_recycler_view, parent, false)

        return NoteViewHolder(view) { onClickListener(dataset[it]) }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.textView.text = dataset[position].title
    }

    override fun getItemCount() = dataset.size

    companion object {
        private val LIST_COMPARATOR = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }
}