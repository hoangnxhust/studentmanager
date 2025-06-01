package com.example.myapplication
import java.io.Serializable

data class Student(
    val id: String,
    var name: String,
    var studentId: String

) : Serializable