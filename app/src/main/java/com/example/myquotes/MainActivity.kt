package com.example.myquotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var quote: TextView
    private lateinit var author: TextView
    private lateinit var previous: LinearLayout
    private lateinit var next: LinearLayout
    private lateinit var fabtn : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(application))[MainViewModel::class.java]
        quote = findViewById(R.id.id_quote)
        author = findViewById(R.id.id_writer)
        previous = findViewById(R.id.previous_btn)
        fabtn = findViewById(R.id.FAbtn)
        next = findViewById(R.id.next_btn)
        setQuoteText(mainViewModel.getQuote())
        previous.setOnClickListener {
            setQuoteText(mainViewModel.prevQuote())
        }
        next.setOnClickListener {
            setQuoteText(mainViewModel.nextQuote())
        }
        fabtn.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
            startActivity(intent)
        }
    }

    private fun setQuoteText(model: Model) {

        quote.text = model.text
        author.text = model.author
    }



}