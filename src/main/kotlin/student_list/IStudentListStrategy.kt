package student_list

import student.BaseStudent
import student.ShortStudent
import student.Student

interface IStudentListStrategy {
    fun read():List<ShortStudent>
    fun write(students: List<ShortStudent>)
}
