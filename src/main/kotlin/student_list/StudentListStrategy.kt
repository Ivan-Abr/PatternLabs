package student_list

import student.Student

interface StudentListStrategy {
    fun read():List<Student>
    fun write(students: List<Student>)
}