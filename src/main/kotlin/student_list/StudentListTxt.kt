package student_list

import data.DataListStudent
import student.Student
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.Exception
import javax.xml.crypto.Data
import kotlin.math.roundToInt

class StudentListTxt(private val filePath: String) {
    private val studentList: MutableList<Student> = mutableListOf();

    @Throws(IOException::class, IllegalArgumentException::class)
    fun readFromTxt(): List<Student> {
        val file = File(filePath)

        if (!file.exists()) {
            throw FileNotFoundException("Файл по адресу $filePath не найден.")
        }

        file.forEachLine { line ->
            try {
                val randInt = (Math.random()*10).roundToInt()
                // Создаем студента из строки и добавляем в список
                val student = Student(randInt,line)
                studentList.add(student)
            } catch (e: IllegalArgumentException) {
                // Если строка некорректна, выбрасываем исключение
                println("Ошибка в строке: \"$line\". Пропускаем её.")
            }
        }

        if (studentList.isEmpty()) {
            throw IllegalArgumentException("В файле нет корректных данных для студентов.")
        }

        return studentList
    }

    @Throws(IOException::class)
    fun writeToTxt() {
        val file = File(filePath)
        file.bufferedWriter().use { writer ->
            studentList.forEach { student ->
                writer.write(
                    "${student.id};${student.firstName};${student.lastName};${student.patronymic ?: ""};" + "${student.telephone ?: ""};${student.telegram ?: ""};${student.mail ?: ""};${student.git ?: ""}"
                )
                writer.newLine()
            }
        }
    }

    fun getStudentById(id: Int) = studentList.find { it.id == id }

    fun getKNStudentShortList(k: Int, n: Int): DataListStudent {
        return DataListStudent(studentList.subList(k * n - 1, k * n))
    }

    fun addNewStudent(student: Student) {
        this.studentList.add(student)
    }

    fun replaceById(id: Int, newStudent: Student) {
        newStudent.id = id
        val ind = this.studentList.indexOf(this.studentList.find { it.id == id })
        if (ind != -1) this.studentList[ind] = newStudent
        else throw Exception("Такого студента не существует")
    }

    fun deleteById(id: Int) {
        this.studentList.removeAt(this.studentList.indexOf(this.studentList.find { it.id == id }))
    }

    fun getStudentShortCount() = this.studentList.size
    fun sortByInitials() = this.studentList.sortedBy { it.getInitials() }

}