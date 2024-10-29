package student_list

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import student.ShortStudent
import java.io.File
import java.io.FileNotFoundException

class StudentListJson(private val path: String): IStudentListStrategy {
    override fun read(): List<ShortStudent> {
        val file = File(path)
        val students = mutableListOf<ShortStudent>()

        if (!file.exists()) {
            throw FileNotFoundException("No file: $path")
        }

        var jsonString = ""

        file.forEachLine { line ->
            try {
                // Создаем студента из строки и добавляем в список
                jsonString += line
            } catch (e: IllegalArgumentException) {
                println("Error in line: $line")
            }
        }

        val jsonOb = Json.parseToJsonElement(jsonString).jsonArray

        jsonOb.forEach { students.add(ShortStudent(it.jsonObject)) }

        if (students.isEmpty()) {
            throw IllegalArgumentException("В файле нет корректных данных для студентов.")
        }

        return students
    }

    override fun write(students: List<ShortStudent>) {
        val json = Json { prettyPrint = true }

        val jsonString = json.encodeToString(students)

        File(path).writeText(jsonString)
    }
}
