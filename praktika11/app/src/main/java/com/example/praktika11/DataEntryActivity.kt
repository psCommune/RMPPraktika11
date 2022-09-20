package com.example.praktika11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataEntryActivity : AppCompatActivity() {
    val Task : MutableList<Tasks> = mutableListOf()
    private lateinit var btnNT: Button
    private lateinit var btn: Button
    private lateinit var etH: EditText
    private lateinit var etM: EditText
    private lateinit var etDT: EditText
    private lateinit var tw: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_entry)

        btnNT = findViewById(R.id.button3)
        btn = findViewById(R.id.button2)
        etH = findViewById(R.id.editTextTextPersonName)
        etM = findViewById(R.id.editTextTextPersonName2)
        etDT = findViewById(R.id.editTextTime)
        tw = findViewById(R.id.textView3)
        val preferences = getSharedPreferences("str",MODE_PRIVATE)
        val edit = preferences.edit()

        btnNT.setOnClickListener {
            if (etH.text.toString()!=""&&etM.text.toString()!=""&&etDT.text.toString()!=""){
                var DescriptionTask = etM.text.toString()
                var HeadingTask = etH.text.toString()
                var DataTime = etDT.text.toString()

                var json: String = ""
                //if (preferences.contains("str")==true){
                if (!preferences.contains("str")){
                    Toast.makeText(this, "asd: ", Toast.LENGTH_SHORT).show()
                }
                else {
                    json = preferences.getString("str","Ошибка2").toString()
                }
                val taskList = Gson().fromJson<List<Tasks>>(json,object :TypeToken<List<Tasks>>(){}.type)
                Task.addAll(taskList)


                val task = Tasks(DataTime, HeadingTask, DescriptionTask)
                Task.add(task)
//                edit.putString("str",task.toString())
//                edit.apply()
                preferences.edit {
                    this.putString("str",Gson().toJson(Task).toString())

                }
                var itog = preferences.getString("str","Ошибка").toString()
                tw.text = itog.toString()
                etDT.text.clear()
                etM.text.clear()
                etH.text.clear()
                Toast.makeText(this, "Сохранена задача: " + task.toString(), Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this,"Не все поля заполнены", Toast.LENGTH_SHORT).show()
            }
        }

        btn.setOnClickListener {

            val intent = Intent (this, DataOutputActivity:: class.java)
            startActivity(intent)
        }
    }

}