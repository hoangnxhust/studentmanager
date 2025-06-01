package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {
    private lateinit var students: ArrayList<Student>
    private lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        students = arrayListOf()
        val listView = findViewById<ListView>(R.id.listView)
        adapter = StudentAdapter(this, students) { student, position ->
            val intent = Intent(this, UpdateStudentActivity::class.java)
            intent.putExtra("student", student)
            intent.putExtra("position", position)
            startActivityForResult(intent, 2)
        }
        findViewById<ListView>(R.id.listView).adapter = adapter
        listView.adapter = adapter

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            intent.putExtra("students", students)
            startActivityForResult(intent, 1)
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, UpdateStudentActivity::class.java)
            intent.putExtra("student", students[position])
            intent.putExtra("position", position)
            startActivityForResult(intent, 2)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                1 -> { // Add student
                    val newStudent = data.getSerializableExtra("new_student") as Student
                    students.add(newStudent)
                }
                2 -> { // Update student
                    val updatedStudent = data.getSerializableExtra("updated_student") as Student
                    val pos = data.getIntExtra("position", -1)
                    if (pos != -1) {
                        students[pos] = updatedStudent
                    }
                }
            }
            adapter.notifyDataSetChanged()
        }
    }
}