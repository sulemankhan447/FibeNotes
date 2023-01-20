package com.fibenotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fibenotes.database.NotesDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotesActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
    }

}