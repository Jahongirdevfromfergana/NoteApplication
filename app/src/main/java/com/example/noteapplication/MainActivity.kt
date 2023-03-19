package com.example.noteapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapplication.databinding.ActivityMainBinding
import java.util.EnumSet.of
import java.util.List.of
import java.util.Set.of

//@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        //viewModel = ViewGroupewModelProvider.of(this).get(NoteViewModel::class.java)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            ).get(NoteViewModel::class.java)



        val notesAdapter = NotesAdapter(this, this, this)
        binding.rcNotes.adapter = notesAdapter
        binding.rcNotes.layoutManager = LinearLayoutManager(this)


        viewModel.allNote.observe(this, Observer { list->
            list?.let {
                    notesAdapter.updateNote(it)
            }
        })
        binding.flAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
    override fun onClickDelete(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted ", Toast.LENGTH_LONG).show()

    }

    override fun onClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteID", note.id)
        startActivity(intent)
        this.finish()


    }
}