package student_list

import data.DataListStudent
import student.BaseStudent
import student.ShortStudent


interface IStudentList {
    fun getStudentById(id: Int): ShortStudent?

    fun getStudentShortList(k: Int, n: Int): DataListStudent

    fun addStudent(student: ShortStudent)

    fun replaceById(id: Int, newStudent: ShortStudent)

    fun deleteById(id: Int)

    fun getStudentShortCount(): Int

    fun sortByInitials(): List<ShortStudent>
}