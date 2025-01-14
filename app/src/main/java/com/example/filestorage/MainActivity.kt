package com.example.filestorage

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class MainActivity : AppCompatActivity() {

    private val filename = "myFile.txt"
    private lateinit var editText: EditText
    private lateinit var tvContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        tvContent = findViewById(R.id.tvContent)

        val savedText = fileRead()
        if (savedText.isNotEmpty())
            tvContent.text = savedText
        }

    private fun fileRead(): String {
        return if (File(filesDir, filename).exists()) {
            openFileInput(filename).bufferedReader().useLines { lines ->
                lines.fold("") { some, text ->
                    "$some\n$text"
                }
            }
        } else {
            ""
        }
    }

    fun saveToFile(view: View) {
        openFileOutput(filename, MODE_PRIVATE).use {
            it.write(editText.text.toString().toByteArray())
        }
        tvContent.text = fileRead()
    }

}