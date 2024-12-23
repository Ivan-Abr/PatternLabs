package org.example.student_list
import Main.UpdateDataInterface
import TableGridPack.Controllers.TableViewController
import data.DataList
import student.ShortStudent
import student.Student
import student_list.StudentList
import java.util.function.Function

class StudentListDecorator(private val wrapee: StudentList, private val tableViewController: TableViewController):StudentListComponent{
    override fun subscribe(sub: UpdateDataInterface) {
        this.wrapee.subscribe(sub)
    }

    override fun notifySubs() {
        println(1)
        this.wrapee.notifySubs()
    }

    override fun getStudentById(id: Int): Student? {
        return this.wrapee.getStudentById(id)
    }

    override fun getStudentList(k: Int, n: Int): DataList<ShortStudent> {
        return this.wrapee.getStudentList(k,n);
    }

    override fun addStudent(student: Student) {
        this.wrapee.addStudent(student);
    }

    override fun updateStudent(id: Int, newStudent: Student) {
        this.wrapee.updateStudent(id, newStudent);
    }

    override fun deleteStudent(id: Int) {
        this.wrapee.deleteStudent(id);
    }

    override fun getCount(): Int {
        return this.wrapee.getCount()
    }

    override fun sortBy(order: Int,columnName:String) {
        this.wrapee.sortBy(order,columnName)
    }

    override fun filterList(function: Function<MutableList<Student>, MutableList<Student>>) {
        this.wrapee.filterList(function);
    }

    override fun restoreOrderList() {
        this.wrapee.restoreOrderList();
    }

    override fun checkConnection(): Boolean {
        return this.wrapee.checkConnection();
    }

    override fun toString(): String {
        return this.wrapee.toString();
    }
}