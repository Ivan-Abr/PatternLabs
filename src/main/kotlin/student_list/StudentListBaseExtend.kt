package StudentList

import data.DataList
import student.ShortStudent
import student.Student
import student_list.StudentStrategy
import java.io.File
import java.io.FileWriter
import java.util.*
import java.util.function.Function
import kotlin.math.min

abstract class StudentListBaseExtend(var readFilePath: String?, var writeFilePath: String?) : StudentStrategy {
    protected val studentList: MutableList<Student> = mutableListOf();
    private var orderedStudentList: MutableList<Student> = mutableListOf();
    private var indexOrder: MutableList<Int> = mutableListOf();

    init {
        this.read()
    }

    abstract fun writeToFile(fileWriter: FileWriter, students: MutableList<Student>)
    abstract fun readFromFile(mainString: String, students: MutableList<Student>)

    // Запись в файл
    override fun write() {
        val file = File(this.writeFilePath)
        if (!this.checkConnection()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(writeFilePath)
        this.writeToFile(fileWriter, this.studentList)
        fileWriter.close()
    }

    // Чтение из файла
    override fun read(): List<Student> {
        if (!this.checkConnection()) throw Exception("Ошибка")
        else {
            val mainString = File(this.readFilePath).readText()
            this.readFromFile(mainString, this.studentList)
            this.indexOrder = this.studentList.map { it.id }.toMutableList();
            this.orderedStudentList = this.studentList.map { Student(it.toString()) }.toMutableList()
        }
        return this.studentList
    }

    override fun checkConnection(): Boolean =
        when {
            readFilePath == null -> false;
            writeFilePath == null -> false;
            !File(readFilePath!!).exists() -> false;
            !File(writeFilePath!!).exists() -> false;
            else -> true;
        }

    override fun getStudentById(id: Int): Student? {
        this.read()
        return studentList.find { it.id == id }
    }

    open override fun getStudentList(k: Int, n: Int): DataList<ShortStudent>
    { return DataList(orderedStudentList.slice((k - 1) * n until min((k - 1) * n + n, orderedStudentList.size))
                .map { ShortStudent(it) }.toTypedArray())}

    override fun sortBy(order: Int, columnName: String) {
        if (order == -1) {
            this.orderedStudentList.sortByDescending {
                Objects.toString(
                    ShortStudent(it).propertiesReturn()[columnName],
                    ""
                )
            }
        } else if (order == 1) {
            this.orderedStudentList.sortBy { Objects.toString(ShortStudent(it).propertiesReturn()[columnName], "") }
        } else {
            val oldList = this.orderedStudentList;
            this.orderedStudentList = mutableListOf<Student>();
            for (i in indexOrder) {
                this.orderedStudentList.add(oldList.first { it.id == i });
            }

        }
    }


    private fun addNewStudent(student: Student, id: Int = studentList.maxOf { it.id } + 1) {
        val stud = student
        stud.id = id
        this.studentList.add(stud);

        this.orderedStudentList = this.studentList.map { it}.toMutableList()
        this.write();
    }

    override fun addStudent(student: Student) {
        val stud = Student(student.toString())
        stud.id = studentList.maxOf { it.id } + 1
        this.studentList.add(stud)

        this.write()
        this.read()

    }

    override fun updateStudent(id: Int, newStudent: Student) {
        val stud = newStudent
        stud.id = id
        val ind = this.studentList.indexOf(this.studentList.find { it.id == id })
        if (ind != -1) this.studentList[ind] = stud
        else this.addNewStudent(newStudent, id)

        this.write()
        this.read()
    }

    override fun deleteStudent(id: Int) {
        val ind = this.studentList.indexOf(this.studentList.find { it.id == id })
        if (ind != -1) {
            this.studentList.removeAt(this.studentList.indexOf(this.studentList.find { it.id == id }))
            this.orderedStudentList = this.studentList.map { it }.toMutableList()
            this.write();
        }
        this.read()
    }

    override fun getCount() = this.orderedStudentList.size

    override fun toString() = this.orderedStudentList.toString()

    override fun filterList(function: Function<MutableList<Student>, MutableList<Student>>) {
        this.orderedStudentList = function.apply(this.orderedStudentList).toMutableList()
        this.indexOrder =
            this.indexOrder.filter { i -> this.orderedStudentList.map { it.id }.toList().contains(i) }.toMutableList()
    }

    override fun restoreOrderList() {
        this.read()
    }
}