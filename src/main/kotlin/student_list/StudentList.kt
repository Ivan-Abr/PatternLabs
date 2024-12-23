package student_list

import Main.UpdateDataInterface
import data.DataList
import org.example.student_list.StudentListComponent
import student.ShortStudent
import student.Student
import java.util.function.Function

class StudentList(
    private val strategy: StudentStrategy
): StudentListComponent {

    private var subscribers: MutableList<UpdateDataInterface> = mutableListOf();
    override fun subscribe(sub: UpdateDataInterface) {
        this.subscribers.add(sub);
    }

    override fun notifySubs() {
        for (sub in this.subscribers) {
            sub.updatePage()
        }
    }

    fun read() = strategy.read()

    fun write() = strategy.write()

    override fun getStudentById(id: Int) = strategy.getStudentById(id)

    override fun getStudentList(k: Int, n: Int): DataList<ShortStudent> = strategy.getStudentList(k, n)

    override fun addStudent(student: Student) = strategy.addStudent(student)

    override fun updateStudent(id: Int, newStudent: Student) = strategy.updateStudent(id, newStudent)

    override fun deleteStudent(id: Int) = strategy.deleteStudent(id)

    override fun getCount(): Int = strategy.getCount()

    override fun sortBy(order: Int, columnName: String) = strategy.sortBy(order, columnName)

    override fun filterList(function: Function<MutableList<Student>, MutableList<Student>>) =
        strategy.filterList(function);

    override fun restoreOrderList() {
        strategy.restoreOrderList()
    }
    override fun checkConnection(): Boolean = strategy.checkConnection()
}
