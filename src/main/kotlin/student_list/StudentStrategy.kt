package student_list

import data.DataList
import student.ShortStudent
import student.Student
import java.util.function.Function

interface StudentStrategy {
    fun read(): List<Student>
    fun write()

    fun getStudentById(id: Int): Student?

    fun getStudentList(k: Int, n: Int): DataList<ShortStudent>

    fun addStudent(student: Student)

    fun updateStudent(id: Int, newStudent: Student)

    fun deleteStudent(id: Int)

    fun getCount(): Int

    fun filterList(function: Function<MutableList<Student>, MutableList<Student>>)
    fun restoreOrderList()
    fun sortBy(order:Int,columnName:String)

    fun checkConnection(): Boolean
}
