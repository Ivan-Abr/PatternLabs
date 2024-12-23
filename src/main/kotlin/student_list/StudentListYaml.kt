package StudentList

import NumberOrStringSerializer
import student.Student
import com.charleskorn.kaml.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import java.io.FileWriter

open class StudentListYaml(readFilePath: String?, writeFilePath: String?) :
    StudentListBaseExtend(readFilePath, writeFilePath) {
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

    override fun writeToFile(fileWriter: FileWriter, students: MutableList<Student>) {
        val res = Yaml.default.encodeToString(ListSerializer(
            MapSerializer(
                String.serializer(),
                NumberOrStringSerializer
            )
        ), students.map { convertMap(it.propertiesReturn()) })
        fileWriter.write(res)
    }

    // Чтение из файла
    override fun readFromFile(mainString: String, students: MutableList<Student>) {
        students.clear()
        val yamlOb = Yaml.default.parseToYamlNode(mainString).yamlList
        yamlOb.items.forEach { students.add(Student(createMap(it.yamlMap))) }
    }
}