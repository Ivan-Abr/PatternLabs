package student

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import kotlin.math.max

abstract class BaseStudent(
   open var id: Int = generateId(),
   open var git: String? = null,
){
    companion object {
        private var currentId = 0
        private var maxId = 0

        internal fun generateId():Int{
            currentId = maxId
            maxId += 1
            return currentId
        }

        internal fun setMaxId(id: Int){
            maxId = max(maxId, id)
        }

        internal fun parseString(data:String):HashMap<String,Any?>{
            val dataSplit = data.split(',')
            val hashData = HashMap<String,Any?>();
            for (propertyValue in dataSplit){
                if(!propertyValue.trim().matches("[a-zA-Z]+:.*".toRegex())){
                    throw Exception("Неверный формат строки")
                }
                else{
                    val (key,propertyVal) = propertyValue.split(":".toRegex(),2);
                    hashData.set(key.trim(),if(propertyVal=="") null else propertyVal.trim());
                }
            }
            return hashData
        }
    }


//    open fun validate(): Boolean {
//        return hasGit() && hasContact()
//    }
//
//    open fun hasGit(): Boolean {
//        return git != null && git!!.isNotBlank()
//    }
//
//    open fun hasContact(): Boolean {
//        return !telephone.isNullOrBlank() || !telegram.isNullOrBlank() || !mail.isNullOrBlank()
//    }
//
//    open fun getLastnameAndInitials(): String{
//        val initialsFirst = " ${firstName[0]}."
//        var initialPatronymic = ""
//        patronymic?.let { initialPatronymic = " ${patronymic!![0]}. " }
//        return lastName + initialsFirst + initialPatronymic
//    }
//
//    open fun getContacts():String{
//        return when {
//            !telephone.isNullOrBlank() -> "Телефон: $telephone"
//            !telegram.isNullOrBlank() -> "Телеграм: $telegram"
//            !mail.isNullOrBlank() -> "Почта: $mail"
//            else -> "Контактов нет"
//        }
//    }

//    open fun displayInfo(){
//        println(
//            """
//                ID: $id
//                Фамилия и инициалы: ${getLastnameAndInitials()}
//                гит: ${git ?: "Нет гита"}
//                контакты: ${getContacts()}
//            """.trimIndent()
//        )
//    }



//        @Throws(IOException::class, IllegalArgumentException::class)
//        fun writeToTxt(studentList: List<Student>?, path: String){
//            val file = File(path)
//            if (!file.exists()) {
//                throw FileNotFoundException("Файл по адресу $path не найден.")
//            }
//            studentList?: throw Exception("NO DATA PRESENTED")
//            studentList.forEach{student ->
//                val string = "${student.firstName};" +
//                        "${student.lastName};" +
//                        "${student.patronymic};" +
//                        "${student.telephone};" +
//                        "${student.telegram};" +
//                        "${student.mail};" +
//                        "${student.git}"
//                file.writeText(string)
//            }
//
//        }
//        @Throws(IOException::class, IllegalArgumentException::class)
//        fun readFromTxt(path: String): List<Student> {
//            val file = File(path)
//            if (!file.exists()) {
//                throw FileNotFoundException("Файл по адресу $path не найден.")
//            }
//
//            val studentList = mutableListOf<Student>()
//            file.forEachLine { line ->
//                try {
//                    val student = Student((Math.random()*10).toInt(),line)
//                    studentList.add(student)
//                } catch (e: IllegalArgumentException) {
//                    println("Ошибка в строке: \"$line\". Пропускаем её.")
//                }
//            }
//
//            if (studentList.isEmpty()) {
//                throw IllegalArgumentException("В файле нет корректных данных для студентов.")
//            }
//
//            return studentList
//        }

    override fun toString(): String {
        return "BaseStudent(id=$id, git=$git)"
    }
}
