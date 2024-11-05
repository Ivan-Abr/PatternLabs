package student_list

import student.ShortStudent

interface StudentStrategy {
    fun read():List<ShortStudent>
    fun write(students: List<ShortStudent>)
}
