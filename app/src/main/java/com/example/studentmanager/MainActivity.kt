package com.example.studentmanager

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etMSSV: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var listView: ListView

    private val studentList = ArrayList<Student>()
    private lateinit var adapter: ArrayAdapter<String>
    private var selectedIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etMSSV = findViewById(R.id.etMSSV)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        listView = findViewById(R.id.listView)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, getDisplayList())
        listView.adapter = adapter

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val mssv = etMSSV.text.toString()
            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                studentList.add(Student(name, mssv))
                refreshList()
                clearInput()
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            selectedIndex = position
            val student = studentList[position]
            etName.setText(student.name)
            etMSSV.setText(student.mssv)
        }

        btnUpdate.setOnClickListener {
            if (selectedIndex >= 0) {
                studentList[selectedIndex].name = etName.text.toString()
                studentList[selectedIndex].mssv = etMSSV.text.toString()
                refreshList()
                clearInput()
                selectedIndex = -1
            } else {
                Toast.makeText(this, "Chọn sinh viên để cập nhật", Toast.LENGTH_SHORT).show()
            }
        }

        btnDelete.setOnClickListener {
            if (selectedIndex >= 0) {
                studentList.removeAt(selectedIndex)
                refreshList()
                clearInput()
                selectedIndex = -1
            } else {
                Toast.makeText(this, "Chọn sinh viên để xóa", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDisplayList(): List<String> {
        return studentList.map { "${it.name}\n${it.mssv}" }
    }

    private fun refreshList() {
        adapter.clear()
        adapter.addAll(getDisplayList())
        adapter.notifyDataSetChanged()
    }

    private fun clearInput() {
        etName.text.clear()
        etMSSV.text.clear()
    }
}
