package com.urbandictionary

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        val etTerm = findViewById<TextInputEditText>(R.id.etTerm)
        val btnGetList = findViewById<Button>(R.id.btnSearch)
        btnGetList.setOnClickListener {
            if (etTerm.length() > 0) {
                val intent = Intent(this, DictionaryList::class.java)
                intent.putExtra("term", etTerm.text.toString())
                startActivity(intent)
            } else {
                etTerm.error = "Please type any word to search";
            }
        }
    }
}