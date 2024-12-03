package org.example.student_db

import data.DataList
import data.DataListStudent
import student.ShortStudent
import student.Student
import java.util.HashMap
import kotlin.math.min

class StudentListDBImpl : StudentListDB {
    private var connection: DatabaseConnection? = DatabaseConnection
    private var studentList: MutableList<Student> = mutableListOf()

    init {
        connection?.getConnection()
        this.read()
    }

    override fun read(): List<Student> {
        val request = "SELECT * FROM student"
        val result = this.connection?.executeSqlSelect(request)
        if (result != null) {
            val resultList: MutableList<Student> = mutableListOf()
            while (result.next()) {
                val resultHash: HashMap<String, Any?> = hashMapOf()
                resultHash["id"] = result.getInt("id")
                resultHash["firstName"] = result.getString("firstname")
                resultHash["lastName"] = result.getString("lastname")
                resultHash["patronymic"] = result.getString("patronymic")
                resultHash["telephone"] = result.getString("telephone")
                resultHash["telegram"] = result.getString("telegram")
                resultHash["git"] = result.getString("git")
                resultHash["mail"] = result.getString("mail")
                resultList.add(Student(resultHash))
            }
            result.close()
            this.studentList = resultList
        }
        return studentList
    }

    override fun getStudentList(k: Int, n: Int): List<ShortStudent> {
        return (studentList.slice((k-1) * n until min((k-1) * n + n, studentList.size)).map { ShortStudent(it) })
    }

    override fun getStudentById(id: Int): Student? {
        this.read()
        return this.studentList.find { it.id == id }
    }

    override fun addNewStudent(student: Student) {
        val studentData = student.returnProperties()
        var columns = ""
        var values = ""
        for (key in studentData.keys) {
            if (studentData[key] != null && key != "id") {
                columns += "${key},"
                values += "'${studentData[key]}', "
            }
        }
        columns = columns.dropLast(1)
        values = values.dropLast(1)
        val request = "INSERT INTO student(${columns}) VALUES (${values})"
        this.connection?.executeSql(request)
        this.read()
    }

    override fun updateStudent(id: Int, newStudent: Student) {
        val studentData = newStudent.returnProperties()
        var values = ""
        for (key in studentData.keys) {
            if (studentData[key] != null && key != "id") {
                values += "${key}='${studentData[key]}',"
            }
        }
        values = values.dropLast(1)
        val request = "update student t set $values where t.id=${id}"
        this.connection?.executeSql(request);
        this.read()
    }

    override fun deleteStudent(id: Int) {
        val request = "delete from student as t where t.id=${id}"
        this.connection?.executeSql(request);
        this.read()
    }

    override fun getCount(): Int {
        this.read();
        return this.studentList.size
    }

    override fun sortByInitials(): List<Student> {
        this.read()
        this.studentList.sortByDescending { it.getInitials() }
        return this.studentList
    }

    override fun checkConnection(): Boolean {
        return this.connection?.getConnection() != null
    }
}