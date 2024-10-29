package student_list

import data.DataListStudent
import student.Student

interface StudentList {
    fun getStudentById(id: Int): Student?

    fun getStudentShortList(k: Int, n: Int): DataListStudent

    fun addStudent(student: Student)

    fun replaceById(id: Int, newStudent: Student)

    fun deleteById(id: Int)

    fun getStudentShortCount(): Int

    fun sortByInitials(): List<Student>
}