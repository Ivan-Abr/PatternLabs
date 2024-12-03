package org.example

import org.example.student_db.StudentListDBImpl
import org.example.student_list.StudentListDBAdapter
import student_list.StudentList


fun main() {
    val studentDb = StudentListDBImpl()
    val adapter = StudentListDBAdapter(studentDb)
    val students = StudentList(adapter)
    val output = students.getStudentList(1, 3)
    output.map { println(it.toString()) }
}