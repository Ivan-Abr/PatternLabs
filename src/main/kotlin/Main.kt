fun main() {
    val st1 = Student.fromStringToStudent("Test; Stringov; Stringovovich; 89604897189; @gelebe; test@mail.com; https://github.com/user/repo")
    val shortStudent = ShortStudent(st1)
    println(shortStudent.toString())
}