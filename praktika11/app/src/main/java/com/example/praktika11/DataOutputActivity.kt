package com.example.praktika11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataOutputActivity : AppCompatActivity() {
    val Task : MutableList<Tasks> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_output)

        val preferences = getSharedPreferences("str", MODE_PRIVATE)
        var json : String = ""
        if (!preferences.contains("str")){
            Toast.makeText(this, "asd: ", Toast.LENGTH_SHORT).show()
        }
        else {
            json = preferences.getString("str","ошибка3").toString()
            val TaskList = Gson().fromJson<List<Tasks>>(json, object : TypeToken<List<Tasks>>(){}.type)
            Task.addAll(TaskList)
        }
        Task.forEach{
            Log.d("result",it.toString())
        }
    }
}