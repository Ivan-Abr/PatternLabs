package org.example.student_db

import data.DataList
import data.DataListStudent
import student.ShortStudent
import student.Student

interface StudentListDB {
    fun read(): List<Student>
    fun getStudentList(k: Int, n: Int): DataListStudent
    fun getStudentById(id: Int): Student?
    fun addNewStudent(student: Student)
    fun updateStudent(id: Int, newStudent: Student)
    fun deleteStudent(id: Int)
    fun getCount(): Int
    fun sortByInitials(): List<Student>
    fun checkConnection(): Boolean
}