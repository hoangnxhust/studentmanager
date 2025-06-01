package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class StudentAdapter(
    private val context: Context,
    private val students: List<Student>,
    private val onEditClick: (Student, Int) -> Unit
) : BaseAdapter()  {
    override fun getCount() = students.size
    override fun getItem(position: Int): Any = students[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvStudentId = view.findViewById<TextView>(R.id.tvStudentId)
        val btnEdit = view.findViewById<Button>(R.id.btnEdit)

        val student = students[position]
        tvName.text = student.name
        tvStudentId.text = "MSSV: ${student.studentId}"

        btnEdit.setOnClickListener {
            onEditClick(student, position) // ✅ Đúng tham số
        }

        return view
    }
}