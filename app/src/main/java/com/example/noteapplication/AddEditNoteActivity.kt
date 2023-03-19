package com.example.noteapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.noteapplication.databinding.ActivityAddEditNoteBinding
import java.text.SimpleDateFormat
import java.util.*

//@AndroidEntryPoint

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddEditNoteBinding
    lateinit var viewModel: NoteViewModel
    var notID = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDes = intent.getStringExtra("noteDescription")
            notID = intent.getIntExtra("noteID", -1)
            binding.btnAdd.text = "Update note"
            binding.edNoteTitle.setText(noteTitle)
            binding.edNoteDesc.setText(noteDes)
        }else{
            binding.btnAdd.text = "Save note"
        }
        binding.btnAdd.setOnClickListener {
            val notTitle = binding.edNoteTitle.text.toString()
            val noteDes = binding.edNoteDesc.text.toString()

            if (noteType.equals("Edit")){
                if (notTitle.isNotEmpty() && noteDes.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentData: String = sdf.format(Date())
                    val updateNote = Note(notTitle, noteDes, currentData)
                    updateNote.id = notID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note update", Toast.LENGTH_LONG).show()
                }
            }else{
                if (notTitle.isNotEmpty() && noteDes.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentData: String = sdf.format(Date())
                    viewModel.addNote(Note(notTitle, noteDes, currentData))
                    Toast.makeText(this, "Not added", Toast.LENGTH_LONG).show()

                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}