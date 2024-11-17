package student_list

import data.DataListStudent
import student.ShortStudent


interface StudentList {
    fun getStudentById(id: Int): ShortStudent?

    fun getStudentList(k: Int, n: Int): DataListStudent

    fun addStudent(student: ShortStudent)

    fun updateStudent(id: Int, newStudent: ShortStudent)

    fun deleteStudent(id: Int)

    fun getCount(): Int

    fun sortByInitials(): List<ShortStudent>
}
