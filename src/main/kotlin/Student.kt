import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class Student(
    id: Int,
    firstName: String,
    lastName: String,
    patronymic: String?,
    telephone: String?,
    telegram: String?,
    mail: String?,
    git: String?
): BaseStudent(id, firstName, lastName, patronymic, git, telephone, telegram, mail){

    constructor(params: Map<String, Any?>): this(
        params["id"] as Int,
        params["firstName"] as String,
        params["lastName"] as String,
        params["patronymic"] as String,
        params["telephone"] as String?,
        params["telegram"] as String?,
        params["mail"] as String?,
        params["git"] as String?
    )

    constructor(id: Int, data: String): this(
        id = id,
        firstName = data.split(";")[0],
        lastName = data.split(";")[1],
        patronymic = data.split("2").getOrNull(2),
        telephone = data.split(";").getOrNull(3),
        telegram = data.split(";").getOrNull(4),
        mail = data.split(";").getOrNull(5),
        git = data.split(";").getOrNull(6)
    )

    override fun hasGit(): Boolean {
        return git != null && git!!.isNotBlank()
    }


    override fun hasContact(): Boolean {
        return !telephone.isNullOrBlank() || !telegram.isNullOrBlank() || !mail.isNullOrBlank()
    }


    override fun validate(): Boolean {
        return hasGit() && hasContact()
    }

    override fun displayInfo() {
        super.displayInfo()
    }

    fun setContacts(telephone: String?, telegram: String? = null, email: String? = null) {
        if (telephone != null) {
            if (!isTelephoneNumberValid(telephone)) {
                throw IllegalArgumentException("Неверный формат телефона: $telephone")
            }
            this.telephone = telephone
        }
        if (telegram != null) {
            if (!isTelegramValid(telegram)) {
                throw IllegalArgumentException("Неверный формат Telegram: $telegram")
            }
            this.telegram = telegram
        }
        if (email != null) {
            if (!isEmailValid(email)) {
                throw IllegalArgumentException("Неверный формат email: $email")
            }
            this.mail = email
        }
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

    fun getContact():String{
        lateinit var contact: String
        lateinit var contactInfo: String
        telegram?.let {
            contact = it
            contactInfo = "telegram"
        }
        telephone?.let {
            contact = it
            contactInfo = "telephone"
        }
        return "$contactInfo: $contact"
    }

    fun getInfo():String{
        val contact = getContacts()
        val fio = getLastnameAndInitials()
        return "Student: $fio, git: $git, $contact"
    }

    override fun toString(): String {
        return "Student(id=$id, firstName='$firstName', lastName='$lastName', patronymic='$patronymic', telephone=$telephone, telegram=$telegram, mail=$mail, git = $git)"
    }
}
