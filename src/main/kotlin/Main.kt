import student.BaseStudent
import student.ShortStudent
import student_list.StudentListImpl
import student_list.JsonStrategy
import student_list.TxtStrategy
import student_list.YamlStrategy

fun main() {
    val student = BaseStudent(2, "Sanya", "Samyolov", "Dobytchikovich", "89013568790", "@arbuzz", "test@",null)
    val shortStudent = ShortStudent(student)
    val jsonStrategy = JsonStrategy("src/main/resources/input.json")
    val yamlStrategy = YamlStrategy("src/main/resources/input.yaml")
    var strategy = StudentListImpl(jsonStrategy)
    println(strategy.getCount())
    strategy.addStudent(shortStudent)
    strategy.write()
    println("Output: " + strategy.read())
    strategy = StudentListImpl(yamlStrategy)
    strategy.addStudent(shortStudent)
    strategy.write()
    println("Output: " + strategy.read())
}
