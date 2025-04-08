package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class NoteViewModel(app: Application) : AndroidViewModel(app) {

    private val db = RoomDBHelper.getInstance(app)

    fun upsert(n: Note) = db.dao.saveNote(n)
    fun delete(n: Note) = db.dao.deleteNote(n)
    fun getNotes() = db.dao.getAllNotes()


}