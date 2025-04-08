package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NoteDao {

    @Upsert
    fun saveNote(n:Note)
    // insert into note (details) values (*.....*)

    //delete from note where -id ==2
    @Delete
    fun deleteNote(n:Note)

    //select * from note
    @Query("select *from note")
    fun getAllNotes(): LiveData<List<Note>>  //liveData


}

