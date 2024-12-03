package student_list

import data.DataListStudent
import student.ShortStudent
import student.Student

interface StudentStrategy {
    fun read(): List<Student>
    fun write(): List<Student>

    fun getStudentById(id: Int): Student?

    fun getStudentList(k: Int, n: Int): List<ShortStudent>

    fun addStudent(student: Student)

    fun updateStudent(id: Int, newStudent: Student)

    fun deleteStudent(id: Int)

    fun getCount(): Int

    fun sortByInitials(): List<Student>

    fun checkConnection(): Boolean
}
