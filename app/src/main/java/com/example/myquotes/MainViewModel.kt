package com.example.myquotes

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(private val  context: Context) : ViewModel() {
        private  var allQuotes : Array<Model> = emptyArray()
       private  var index = loadData()
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


    fun getQuote() = allQuotes[index]

    fun prevQuote(): Model {
        return if(index > 0) {
            saveData(--index)
            allQuotes[index]
        } else {
            saveData(index)
            allQuotes[index]
        }
    }
    fun nextQuote(): Model {
        return if(index < allQuotes.size -1 ) {
            saveData(++index)
            allQuotes[index]
        } else {
            saveData(index)
            allQuotes[index]
        }
    }
    private fun saveData( Index : Int){
        val sharedPreferences : SharedPreferences  = context.getSharedPreferences("sharedPref",Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply{
            putInt("index", Index)
        }.apply()

    }
    private fun loadData(): Int {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("index", 0)
    }
}