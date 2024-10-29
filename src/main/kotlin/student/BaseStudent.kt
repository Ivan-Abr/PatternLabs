package student

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

open class BaseStudent(
    var id: Int,
    var firstName: String,
    var lastName: String,
    var patronymic: String? = null,
    var telephone: String? = null,
    var telegram: String? = null,
    var git: String? = null,
    var mail: String? = null
){

    init {
        if (!validate()) {
            throw IllegalArgumentException("Персона должна иметь хотя бы один контакт или Git.")
        }
    }

    open fun validate(): Boolean {
        return hasGit() && hasContact()
    }

    open fun hasGit(): Boolean {
        return git != null && git!!.isNotBlank()
    }

    open fun hasContact(): Boolean {
        return !telephone.isNullOrBlank() || !telegram.isNullOrBlank() || !mail.isNullOrBlank()
    }

    open fun getLastnameAndInitials(): String{
        val initialsFirst = " ${firstName[0]}."
        var initialPatronymic = ""
        patronymic?.let { initialPatronymic = " ${patronymic!![0]}. " }
        return lastName + initialsFirst + initialPatronymic
    }

    open fun getContacts():String{
        return when {
            !telephone.isNullOrBlank() -> "Телефон: $telephone"
            !telegram.isNullOrBlank() -> "Телеграм: $telegram"
            !mail.isNullOrBlank() -> "Почта: $mail"
            else -> "Контактов нет"
        }
    }

    open fun displayInfo(){
        println(
            """
                ID: $id
                Фамилия и инициалы: ${getLastnameAndInitials()}
                гит: ${git ?: "Нет гита"}
                контакты: ${getContacts()}
            """.trimIndent()
        )
    }

    companion object {
        private var telephoneRegex = Regex("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}\$")
        private val emailRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")
        private val telegramRegex = Regex("^@[A-Za-z0-9_]{5,32}\$")
        private val gitRegex = Regex("(https?:\\/\\/)?(www\\.)?(github\\.com|gitlab\\.com|bitbucket\\.org)\\/[A-Za-z0-9_.-]+\\/[A-Za-z0-9_.-]+(\\.git)?\$")

        fun isTelephoneNumberValid(phone: String): Boolean {
            return telephoneRegex.matches(phone)
        }

        fun isEmailValid(email: String): Boolean{
            return emailRegex.matches(email)
        }

        fun isTelegramValid(telegram: String): Boolean{
            return telegramRegex.matches(telegram)
        }

        fun isGitValid(git: String): Boolean{
            return gitRegex.matches(git)
        }

        @Throws(IOException::class, IllegalArgumentException::class)
        fun writeToTxt(studentList: List<Student>?, path: String){
            val file = File(path)
            if (!file.exists()) {
                throw FileNotFoundException("Файл по адресу $path не найден.")
            }
            studentList?: throw Exception("NO DATA PRESENTED")
            studentList.forEach{student ->
                val string = "${student.firstName};" +
                        "${student.lastName};" +
                        "${student.patronymic};" +
                        "${student.telephone};" +
                        "${student.telegram};" +
                        "${student.mail};" +
                        "${student.git}"
                file.writeText(string)
            }

        }
        @Throws(IOException::class, IllegalArgumentException::class)
        fun readFromTxt(path: String): List<Student> {
            val file = File(path)
            if (!file.exists()) {
                throw FileNotFoundException("Файл по адресу $path не найден.")
            }

            val studentList = mutableListOf<Student>()
            file.forEachLine { line ->
                try {
                    val student = Student((Math.random()*10).toInt(),line)
                    studentList.add(student)
                } catch (e: IllegalArgumentException) {
                    println("Ошибка в строке: \"$line\". Пропускаем её.")
                }
            }

            if (studentList.isEmpty()) {
                throw IllegalArgumentException("В файле нет корректных данных для студентов.")
            }

            return studentList
        }
    }
}