package com.example.notesapp

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityEditingNoteBinding
import com.example.notesapp.databinding.ActivityMainBinding

class EditingNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityEditingNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra("note", Note::class.java)!!
        else
            intent.getParcelableExtra("note")!!

        binding.noteEt.setText(note.details)

        val viewModel =ViewModelProvider(this,)[NoteViewModel::class.java]
        binding.updateBtn.setOnClickListener {
            val n =Note(note.id,binding.noteEt.text.toString())
            viewModel.upsert(n)
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            finish()
        }

        binding.deleteBtn.setOnClickListener {
            viewModel.delete(note)
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            binding.deleteBtn.isEnabled=false
            binding.updateBtn.isEnabled=false
            binding.noteEt.isEnabled=false
        }



    }


}