package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import kotlin.random.Random
class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)


        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val name = findViewById<EditText>(R.id.editName).text.toString()
            val studentId = findViewById<EditText>(R.id.editStudentId).text.toString()
            val newStudent = Student(Random.nextInt().toString(), name, studentId)

            val resultIntent = Intent()
            resultIntent.putExtra("new_student", newStudent)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
