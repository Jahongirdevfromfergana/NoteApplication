package com.example.noteapplication

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)
    @Update
    suspend fun upDate(note: Note)
    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}