package org.example.student_list

import Main.UpdateDataInterface
import data.DataList
import student.ShortStudent
import student.Student
import java.util.function.Function

interface StudentListComponent {
    fun subscribe(sub: UpdateDataInterface)
    fun notifySubs()
    fun getStudentById(id: Int): Student?
    fun getStudentList(k: Int, n: Int): DataList<ShortStudent>
    fun addStudent(student: Student)
    fun updateStudent(id: Int, newStudent: Student)
    fun deleteStudent(id: Int)
    fun getCount(): Int
    fun sortBy(order: Int,columnName:String)
    fun filterList(function: Function<MutableList<Student>, MutableList<Student>>)
    fun restoreOrderList();
    fun checkConnection(): Boolean
}