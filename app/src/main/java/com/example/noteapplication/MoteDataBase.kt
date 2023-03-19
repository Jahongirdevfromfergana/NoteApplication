package com.example.noteapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class MoteDataBase : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: MoteDataBase? = null
        fun getDataBase(context: Context): MoteDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoteDataBase::class.java,
                    "note_database"
                ).build()
                INSTANCE =  instance
                instance
            }
        }

    }

}