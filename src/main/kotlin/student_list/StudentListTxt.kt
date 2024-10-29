package student_list

import data.DataListStudent
import student.ShortStudent
import student.Student
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.Exception
import javax.xml.crypto.Data
import kotlin.math.roundToInt

class StudentListTxt(
    private val filePath: String
): IStudentListStrategy {
    override fun read(): List<ShortStudent> {
        val file = File(filePath)
        val students = mutableListOf<ShortStudent>()
        if (!file.exists())
            throw FileNotFoundException("no file: $filePath")

        file.forEachLine { line ->
            try {
                val student = ShortStudent(line)
                students.add(student)
            }
            catch (e: IllegalArgumentException){
                println("Error in line: $line")
            }
        }

        if (students.isEmpty())
            throw IllegalArgumentException("No data present")
        return students
    }

    override fun write(students: List<ShortStudent>) {
        val file = File(filePath)
        file.bufferedWriter().use { writer ->
            students.forEach {student ->
                writer.write(
                    "${student.id};${student.nameAndInitials};${student.git};${student.contacts}"
                )
                writer.newLine()
            }
        }
    }
}