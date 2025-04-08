package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding =ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel= ViewModelProvider(this)[NoteViewModel::class.java]
        binding.addFab.setOnClickListener {
            startActivity(Intent(this,AddingNoteActivity::class.java))

        }
        viewModel.getNotes().observe(this){ notes->
            val details =notes.map{it.details}
            val adapter =ArrayAdapter( this,android.R.layout.simple_list_item_1,details)
            binding.notesList.adapter= adapter

            binding.notesList.setOnItemClickListener { parent, view, position, id ->
                val i =Intent(this,EditingNoteActivity::class.java)
                i.putExtra("note",notes[position])
                startActivity(i)

            }

        }

    }


}