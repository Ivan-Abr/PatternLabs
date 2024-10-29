package student_list

import com.charleskorn.kaml.*
import kotlinx.serialization.encodeToString
import student.ShortStudent
import java.io.File
import java.io.FileNotFoundException

class StudentListYaml(private val path: String) : IStudentListStrategy {
    fun createMap(el: Any): HashMap<String, Any?> {
        val map = HashMap<String, Any?>()
        for ((key, value) in (el as YamlMap).entries.entries) {
            map[key.content] = value.yamlScalar.content
        }
        return map
    }

    fun convertMap(hashM: Map<String, Any?>): Map<String, Any> {
        val res = mutableMapOf<String, Any>()
        for ((key, value) in hashM.entries) {
            if (value != null) res.set(key, if (value is Int) value else value.toString())
        }
        return res
    }

    override fun write(students: List<ShortStudent>) {
        val yamlContent = Yaml.default.encodeToString(students)
        File(path).writeText(yamlContent)
    }

    override fun read(): List<ShortStudent> {
        val file = File(path)
        val studentList = mutableListOf<ShortStudent>()

        if (!file.exists()) {
            throw FileNotFoundException("No file: $path")
        }

        var yamlString = ""

        file.forEachLine { line ->
            try {
                // Создаем студента из строки и добавляем в список
                yamlString += line
            } catch (e: IllegalArgumentException) {
                // Если строка некорректна, выбрасываем исключение
                println("Error in line: $line")
            }
        }

        val yamlOb = Yaml.default.parseToYamlNode(yamlString).yamlList
        yamlOb.items.forEach {studentList.add(ShortStudent(createMap(it.yamlMap)))}

        return studentList
    }
}