package student_db

import org.example.student_db.DatabaseConnection
import org.example.student_db.StudentListDBImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import student.ShortStudent
import student.Student


class StudentListDBTest{

    fun getStudentMockFromBD(surname:String): Student {
        val conn = DatabaseConnection;
        val result = conn.executeSqlSelect("SELECT * FROM ref_student_moc as t where t.surname='$surname' and t.name='Test' and t.patronymic='Test'");
        result?.next()
        val resultHash: HashMap<String, Any?> = hashMapOf<String, Any?>()
        resultHash["id"] = result?.getInt("id")
        resultHash["firstName"] = result?.getString("firstName")
        resultHash["lastName"] = result?.getString("surname")
        resultHash["patronymic"] = result?.getString("patronymic")
        resultHash["telephone"] = result?.getString("telephone")
        resultHash["git"] = result?.getString("git")
        resultHash["mail"] = result?.getString("mail")
        resultHash["telegram"] = result?.getString("telegram")
        val resultDBStudent = Student(resultHash)
        result?.close()
        return resultDBStudent
    }

    @Test
    fun testAddDelete(){
        val studentListDB = StudentListDBImpl();
        studentListDB.tableName = "student_moc"
        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        assert(resultStudent.toString() ==  resultDbStudent.toString())
        studentListDB.deleteStudent(resultStudent.id)
        assert(null ==  studentListDB.getStudentById(resultStudent.id))
    }

    @Test
    fun testGetNKStudentShort(){
        val studentListDB = StudentListDBImpl();
        studentListDB.tableName = "ref_student_moc"

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        assert(studentListDB.getStudentList(6, 6).elements.isEmpty())
        assert(ShortStudent(resultStudent).toString() == studentListDB.getStudentList(1,1).elements[0].toString())
        studentListDB.deleteStudent(resultStudent.id)
    }

    @Test
    fun testSortByInitials(){
        val studentListDB = StudentListDBImpl();
        studentListDB.tableName = "ref_student_moc"

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent)

        val resultStudent2 = Student("Student(id:4,surname:Tester,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent2)


        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        val resultDbStudent2 = getStudentMockFromBD("Tester")
        resultStudent2.id = resultDbStudent2.id


        studentListDB.sortBy(1,"initials")
        assert(ShortStudent(resultStudent).toString() == studentListDB.getStudentList(1,6).elements[0].toString())
        studentListDB.sortBy(-1,"initials")
        assert(ShortStudent(resultStudent2).toString() == studentListDB.getStudentList(1,6).elements[0].toString())

        studentListDB.deleteStudent(resultStudent.id)
        studentListDB.deleteStudent(resultStudent2.id)
    }
    @Test
    fun testReplaceById(){
        val studentListDB = StudentListDBImpl();
        studentListDB.tableName = "ref_student_moc"

        val resultStudent = Student("Student(id:4,firstName:Test,lastName:Test,patronymic:Test,telephone:,email:,telegram:@testtest,git:)")
        studentListDB.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        val replaceStudent = Student("Student(id:3,firstname:Aeeeeeaaa,lastName:Bbbbbb,patronymic:Cccccc,telephone:,email:,telegram:,git:)")
        replaceStudent.id = resultDbStudent.id

        studentListDB.updateStudent(replaceStudent.id,replaceStudent)

        assert(replaceStudent.toString() == studentListDB.getStudentById(resultStudent.id).toString())
        studentListDB.deleteStudent(resultStudent.id)
    }
    @Test
    fun testGetStudentCount(){
        val studentListDB = StudentListDBImpl();
        studentListDB.tableName = "ref_student_moc"

        val resultStudent = Student("Student(id:4,firstName:Test,lastName:Test,patronymic:Test,telephone:,email:,telegram:@testtest,git:)")
        studentListDB.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        assert(1 == studentListDB.getCount())
        studentListDB.deleteStudent(resultStudent.id)
    }

    @Test
    fun testFilter(){
        val studentListDB = StudentListDBImpl();
        studentListDB.tableName = "ref_student_moc"

        val resultStudent = Student("Student(id:4,surname:Test,name:Test,patronymic:Test,phoneNumber:,email:,telegram:@testtest,gitHub:)")
        studentListDB.addNewStudent(resultStudent)

        val resultDbStudent = getStudentMockFromBD("Test")
        resultStudent.id = resultDbStudent.id

        studentListDB.filterList(){it.filter { i->i.firstName=="Test" }.toMutableList()}
        assert(1 == studentListDB.getCount())
        studentListDB.deleteStudent(resultStudent.id)
    }

}