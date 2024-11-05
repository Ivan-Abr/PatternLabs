package student_list

import student.ShortStudent
import java.io.File
import java.io.FileNotFoundException

class TxtStrategy(
    private val path: String
): StudentStrategy {
    override fun read(): List<ShortStudent> {
        val file = File(path)
        val students = mutableListOf<ShortStudent>()
        if (!file.exists())
            throw FileNotFoundException("no file: $path")

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
        val file = File(path)
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
