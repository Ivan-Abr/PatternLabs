package ru.kubsu.lr_1

class Student{
    private lateinit var _firstName: String
    private lateinit var _lastName: String
    private lateinit var _patronymic: String
    private var _telephone: String? = null
    private var _telegram:String? = null
    private var _mail:String? = null
    private var _git: String? = null

    constructor(
        id: Int,
        firstName: String,
        lastName: String,
        patronymic: String,
        telephone: String?,
        telegram: String?,
        mail: String?,
        git: String?
    ){
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.patronymic = patronymic
        this.telephone = telephone
        this.telegram = telegram
        this.mail = mail
        this.git = git
    }

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

    var id: Int
    var firstName: String
        get() = _firstName
        set(value){_firstName = value}

    var lastName: String
        get() = _lastName
        set(value){_lastName = value}

    var patronymic: String
        get() = _patronymic
        set(value) {_patronymic = value}

    var telephone: String?
        get() = _telephone
        set(value){
            if (value != null && !isTelephoneNumberValid(value)){
                throw Exception("Phone number is not valid!: $value")
            }
            _telephone = value
        }

    var telegram: String?
        get() = _telegram
        set(value){
            if (value != null && !isTelegramValid(value)){
                throw Exception("Telegram is not valid!: $value")
            }
            _telegram = value
        }

    var mail: String?
        get() = _mail
        set(value){
            if (value!= null && !isEmailValid(value)){
                throw Exception("Mail is not valid!: $value")
            }
            _mail = value
        }

    var git: String?
        get() = _git
        set(value){
            if (value!= null && !isGitValid(value)){
                throw Exception("Git is not valid!: $value")
            }
            _git = value
        }

    companion object {
        private var telephoneRegex = Regex("/^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$/")
        private val emailRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")
        private val telegramRegex = Regex("^@[A-Za-z0-9_]{5,32}\$")
        private val gitRegex = Regex("/^github\\.com\\/[a-z0-9_-]+\$/gm")

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

    override fun toString(): String {
        return "Student(id=$id, firstName='$firstName', lastName='$lastName', patronymic='$patronymic', telephone=$telephone, telegram=$telegram, mail=$mail, git = $git)"
    }
}
