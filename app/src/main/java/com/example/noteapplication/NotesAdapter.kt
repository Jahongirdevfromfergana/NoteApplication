package com.example.noteapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.databinding.NoteItemLayoutBinding

class NotesAdapter(
    val context: Context,
    private val noteClickInterface: NoteClickInterface,
    private val noteClickDeleteInterface: NoteClickDeleteInterface
) : RecyclerView.Adapter<NotesAdapter.ItemHolder>() {


    private val allNotes = ArrayList<Note>()

    inner class ItemHolder(val binding: NoteItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            NoteItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = allNotes[position]
        holder.binding.tvNoteTitle.text = item.noteTitle
        holder.binding.tvDes.text = item.noteDescription
        holder.binding.tvTimeStamp.text = "Last update ${item.timeStop}"

        holder.binding.tvDelete.setOnClickListener {
            noteClickDeleteInterface.onClickDelete(allNotes.get(position))
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.onClick(allNotes.get(position))
        }
    }


    override fun getItemCount(): Int {
        return allNotes.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNote(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickDeleteInterface {
    fun onClickDelete(note: Note)
}

interface NoteClickInterface {
    fun onClick(note: Note)
}