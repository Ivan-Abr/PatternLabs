package student_list

import data.DataListStudent
import student.ShortStudent

class StudentListImpl(
    private val strategy: StudentStrategy
): StudentList {
    private val students = mutableListOf<ShortStudent>()

    fun write() = strategy.write(students)
    fun read(): List<ShortStudent> = strategy.read()

    override fun getStudentById(id: Int): ShortStudent {
        return students[id]
    }

    override fun getStudentList(k: Int, n: Int): DataListStudent {
        return DataListStudent(students.subList(k * n-1, n-k))
    }

    override fun addStudent(student: ShortStudent) {
        students.add(student)
    }

    override fun updateStudent(id: Int, newStudent: ShortStudent) {
        if (id == -1) throw Exception("Incorrect id")
        students[id] = newStudent
    }

    override fun deleteStudent(id: Int) {
        students.removeAt(id)
    }

    override fun getCount(): Int {
        return students.size
    }

    override fun sortByInitials(): List<ShortStudent> {
        return students.sortedBy { it.id }
    }
}
