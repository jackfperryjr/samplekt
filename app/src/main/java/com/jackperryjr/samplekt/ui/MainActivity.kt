package com.jackperryjr.samplekt.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.view.*
import com.jackperryjr.samplekt.R
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private var nameField: EditText? = null
    private var startButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameField = findViewById(R.id.nameEditText)
        startButton = findViewById(R.id.startButton)

        startButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val name = nameField!!.text.toString()
                startStory(name)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        nameField!!.setText("")
    }

    private fun startStory(name: String) {
        val intent = Intent(this, StoryActivity::class.java)
        val resources = resources
        val key = resources.getString(R.string.key_name)
        intent.putExtra(key, name)
        startActivity(intent)
    }
}
