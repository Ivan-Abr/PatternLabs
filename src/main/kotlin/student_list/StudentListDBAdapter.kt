package org.example.student_list

import data.DataList
import org.example.student_db.StudentListDB
import student.ShortStudent
import student.Student
import student_list.StudentStrategy
import java.util.function.Function

class StudentListDBAdapter(private val studentListDB: StudentListDB) : StudentStrategy {

    override fun read() = throw IllegalAccessError("student_db.StudentListDB cannot have read method")

    override fun write() = throw IllegalAccessError("student_db.StudentListDB cannot have write method")

    override fun getStudentById(id: Int): Student? = studentListDB.getStudentById(id)


    override fun getStudentList(k: Int, n: Int): DataList<ShortStudent> = studentListDB.getStudentList(k, n)

    override fun addStudent(student: Student) = studentListDB.addNewStudent(student)

    override fun updateStudent(id: Int, newStudent: Student) = studentListDB.updateStudent(id, newStudent)

    override fun deleteStudent(id: Int) = studentListDB.deleteStudent(id)

    override fun getCount(): Int = studentListDB.getCount()
    override fun filterList(function: Function<MutableList<Student>, MutableList<Student>>) =
        studentListDB.filterList(function)

    override fun restoreOrderList() = studentListDB.restoreOrderList()

    override fun sortBy(order: Int, columnName: String) = studentListDB.sortBy(order, columnName)


    fun sortByInitials(): List<Student> = studentListDB.sortByInitials()

    override fun checkConnection(): Boolean = studentListDB.checkConnection()

}