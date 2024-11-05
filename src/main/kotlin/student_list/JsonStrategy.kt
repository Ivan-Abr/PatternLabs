package student_list

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import student.ShortStudent
import java.io.File
import java.io.FileNotFoundException

class JsonStrategy(private val path: String): StudentStrategy {
    override fun read(): List<ShortStudent> {
        val file = File(path)
        val students = mutableListOf<ShortStudent>()

        if (!file.exists()) {
            throw FileNotFoundException("No file: $path")
        }

//        var jsonString = ""
//
//        file.readText()

        val jsonObject = Json.parseToJsonElement(file.readText()).jsonArray

        jsonObject.forEach { jsonElement ->
            val jsonObj = jsonElement.jsonObject
            val params: Map<String, Any?> = mapOf(
                "id" to (jsonObj["id"] as? JsonPrimitive)?.toString()!!.toInt(),
                "nameAndInitials" to (jsonObj["nameAndInitials"] as? JsonPrimitive)?.content,
                "git" to (jsonObj["git"] as? JsonPrimitive)?.content,
                "contacts" to (jsonObj["contacts"] as? JsonPrimitive)?.content
            )

            students.add(ShortStudent(params))
        }

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
