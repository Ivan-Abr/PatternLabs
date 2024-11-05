package student_list

import com.charleskorn.kaml.*
import kotlinx.serialization.encodeToString
import student.ShortStudent
import java.io.File
import java.io.FileNotFoundException

class YamlStrategy(private val path: String) : StudentStrategy {
    private fun createMap(el: Any): HashMap<String, Any?> {
        val map = HashMap<String, Any?>()
        for ((key, value) in (el as YamlMap).entries.entries) {
            map[key.content] = value.yamlScalar.content
        }
        return map
    }

    fun convertMap(hashM: Map<String, Any?>): Map<String, Any> {
        val res = mutableMapOf<String, Any>()
        for ((key, value) in hashM.entries) {
            if (value != null) res[key] = if (value is Int) value else value.toString()
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
        val yamlString = file.readText()
        val yamlObject = Yaml.default.parseToYamlNode(yamlString).yamlList
        yamlObject.items.forEach {studentList.add(ShortStudent(createMap(it.yamlMap)))}
        return studentList
    }
}
