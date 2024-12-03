package student_list

import data.DataListStudent
import student.ShortStudent
import student.Student

class StudentList(
    private val strategy: StudentStrategy
) {

    fun write() = strategy.write()

    fun read(): List<Student> = strategy.read()

    fun getStudentById(id: Int) = strategy.getStudentById(id)

    fun getStudentList(k: Int, n: Int) = strategy.getStudentList(k, n)

    fun addStudent(student: Student) = strategy.addStudent(student)

    fun updateStudent(id: Int, newStudent: Student) = strategy.updateStudent(id, newStudent)

    fun deleteStudent(id: Int) = strategy.deleteStudent(id)

    fun getCount(): Int = strategy.getCount()

    fun sortByInitials(): List<Student> = strategy.sortByInitials()

    fun checkConnection(): Boolean = strategy.checkConnection()
}
