package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UpdateStudentActivity : AppCompatActivity() {

    private lateinit var student: Student
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_student)

        student = intent.getSerializableExtra("student") as Student
        position = intent.getIntExtra("position", -1)

        val etName = findViewById<EditText>(R.id.etName)
        val etStudentId = findViewById<EditText>(R.id.etStudentId)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)

        etName.setText(student.name)
        etStudentId.setText(student.studentId)

        btnUpdate.setOnClickListener {
            val updatedStudent = Student(
                id = student.id,
                name = etName.text.toString(),
                studentId = etStudentId.text.toString()
            )
            val intent = Intent()
            intent.putExtra("updated_student", updatedStudent)
            intent.putExtra("position", position)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
    }
