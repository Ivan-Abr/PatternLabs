fun main() {
    val path = "src/main/resources/input.txt"
    val st1 = Student(1,"Artur; Pirogov; Pylesosovich; 89604897189; @gelebe; test@mail.com; https://github.com/user/repo")
    val st2 = Student(2, "Sanya", "Samyolov", "Dobytchikovich", "89013568790", "@arbuzz", "test@mail.com",null)

    val studentList: List<Student> = listOf(st1, st2)
    println(studentList)
    val dataList = DataListStudent(studentList)
    println(dataList.toString())
    dataList.select(0)
    dataList.select(1)
    val dataTable: DataTable<String> = dataList.getData()
    val dataListNames = dataList.getNames()
    dataList.printNames(dataListNames)
    dataTable.displayTable()
//    Student.writeToTxt(studentList, path)
//    val studentInput = Student.readFromTxt(path)
//    studentInput.map { student -> student.displayInfo() }
}