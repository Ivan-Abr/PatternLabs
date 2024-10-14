fun main() {
    val path = "src/main/resources/input.txt"
    val studentInput = Student.readFromTxt(path)
    studentInput.map { student -> student.displayInfo() }
}