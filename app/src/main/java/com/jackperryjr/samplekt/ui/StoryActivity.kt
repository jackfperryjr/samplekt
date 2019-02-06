package com.jackperryjr.samplekt.ui

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.*
import android.view.*

import com.jackperryjr.samplekt.model.Page
import com.jackperryjr.samplekt.model.Story
import com.jackperryjr.samplekt.R

import java.util.Stack

class StoryActivity : AppCompatActivity() {

    private var name: String? = null
    private var story: Story? = null
    private var storyImageView: ImageView? = null
    private var storyTextView: TextView? = null
    private var choice1Button: Button? = null
    private var choice2Button: Button? = null
    private val pageStack = Stack<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        storyImageView = findViewById(R.id.storyImageView) as ImageView
        storyTextView = findViewById(R.id.storyTextView) as TextView
        choice1Button = findViewById(R.id.choice1Button) as Button
        choice2Button = findViewById(R.id.choice2Button) as Button

        val intent = intent
        name = intent.getStringExtra(getString(R.string.key_name))
        if (name == null || name!!.isEmpty()) {
            name = "Friend"
        }
        Log.d(TAG, name)

        story = Story()
        loadPage(0)
    }

    private fun loadPage(pageNumber: Int) {
        pageStack.push(pageNumber)

        val page = story!!.getPage(pageNumber)

        val image = ContextCompat.getDrawable(this, page!!.imageId)
        storyImageView!!.setImageDrawable(image)

        var pageText = getString(page.textId)
        // Add name if placeholder included. Won't add if not
        pageText = String.format(pageText, name)
        storyTextView!!.text = pageText

        if (page.isFinalPage) {
            choice1Button!!.visibility = View.INVISIBLE
            choice2Button!!.setText(R.string.play_again_button_text)
            choice2Button!!.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    loadPage(0)
                }
            })
        } else {
            loadButtons(page)
        }
    }

    private fun loadButtons(page: Page) {
        choice1Button!!.visibility = View.VISIBLE
        choice1Button!!.setText(page.choice1!!.textId)
        choice1Button!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val nextPage = page.choice1!!.nextPage
                loadPage(nextPage)
            }
        })

        choice2Button!!.visibility = View.VISIBLE
        choice2Button!!.setText(page.choice2!!.textId)
        choice2Button!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val nextPage = page.choice2!!.nextPage
                loadPage(nextPage)
            }
        })
    }

    override fun onBackPressed() {
        pageStack.pop()
        if (pageStack.isEmpty()) {
            super.onBackPressed()
        } else {
            loadPage(pageStack.pop())
        }
    }

    companion object {

        val TAG = StoryActivity::class.java.simpleName
    }
}
