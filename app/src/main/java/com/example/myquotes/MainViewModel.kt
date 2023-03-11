package com.example.myquotes

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val  context: Context) : ViewModel() {
        private  var allQuotes : Array<Model> = emptyArray()
       private  var index = 0
    init {
       allQuotes = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Model>{
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
       val gson = Gson()
        return gson.fromJson(json, Array<Model>::class.java)
    }

    fun Context.readJsonAsset(fileName: String): String {
        val inputStream = assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charsets.UTF_8)
    }
    fun getQuote() = allQuotes[index]

    fun prevQuote(): Model {
        return if(index > 0) {
            allQuotes[--index]
        } else {
            allQuotes[index]
        }
    }
    fun nextQuote(): Model {
        return if(index < allQuotes.size -1 ) {
            allQuotes[index ++]
        } else {
            allQuotes[index]
        }
    }
}