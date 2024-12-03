package org.example.student_list

import org.example.student_db.StudentListDB
import student.ShortStudent
import student.Student
import student_list.StudentStrategy

class StudentListDBAdapter(private val studentListDB: StudentListDB) : StudentStrategy {

    override fun read(): List<Student>  = studentListDB.read()

    override fun write(students: List<ShortStudent>) = throw Exception("Nuh-uh")

    override fun getStudentById(id: Int): Student? = studentListDB.getStudentById(id)


    override fun getStudentList(k: Int, n: Int): List<ShortStudent> = studentListDB.getStudentList(k, n)

    override fun addStudent(student: Student) = studentListDB.addNewStudent(student)

    override fun updateStudent(id: Int, newStudent: Student) = studentListDB.updateStudent(id, newStudent)

    override fun deleteStudent(id: Int) = studentListDB.deleteStudent(id)

    override fun getCount(): Int = studentListDB.getCount()

    override fun sortByInitials(): List<Student> = studentListDB.sortByInitials()

    override fun checkConnection(): Boolean = studentListDB.checkConnection()

}