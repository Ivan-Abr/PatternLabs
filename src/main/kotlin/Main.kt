package org.example
import Main.StudentManagementApp


import org.example.student_db.StudentListDBImpl
import org.example.student_list.StudentListDBAdapter
import student_list.StudentList


fun main() {
    val studentDb = StudentListDBImpl()
    val adapter = StudentListDBAdapter(studentDb)
    val students = StudentList(adapter)
    StudentManagementApp.start()
}