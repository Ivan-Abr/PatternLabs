fun main() {
    val path = "src/main/resources/input.txt"
    val st1 = Student(3,"Test; Stringov; Stringovovich; 89604897189; @gelebe; test@mail.com; https://github.com/user/repo")
    val studentList: List<Student> = listOf(st1)
    Student.writeToTxt(studentList, path)
    val studentInput = Student.readFromTxt(path)
    studentInput.map { student -> student.displayInfo() }
}