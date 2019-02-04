package com.jackperryjr.samplekt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton?.setOnClickListener { showText() }
    }

    private fun showText() {
        val name = findViewById<EditText>(R.id.mainEditText)
        if (name != null) {
            Toast.makeText(this, name.text, Toast.LENGTH_LONG).show()
        }
    }
}
