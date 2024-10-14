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

        fun fromStringToStudent(input: String): Student {
            val parsedData = input.split("; ")
            val id = 1337
            return Student(
                id,
                parsedData[0],
                parsedData[1],
                parsedData[2],
                parsedData.getOrNull(3),
                parsedData.getOrNull(4),
                parsedData.getOrNull(5),
                parsedData.getOrNull(6)
            )
        }

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
