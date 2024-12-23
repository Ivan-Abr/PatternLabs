package org.example.student_db

import data.DataList
import student.ShortStudent
import student.Student
import java.util.function.Function

interface StudentListDB {
    fun read(): List<Student>
    fun getStudentList(k: Int, n: Int): DataList<ShortStudent>
    fun getStudentById(id: Int): Student?
    fun addNewStudent(student: Student)
    fun updateStudent(id: Int, newStudent: Student)
    fun deleteStudent(id: Int)
    fun getCount(): Int
    fun sortByInitials(): List<Student>
    fun sortBy(order:Int,columnName:String)
    fun checkConnection(): Boolean
    fun filterList(function: Function<MutableList<Student>, MutableList<Student>>)
    fun restoreOrderList()
}