package student

class Student : BaseStudent, Comparable<Student>{

    private var _id = generateId()
    private lateinit var _firstName: String
    private lateinit var _lastName: String
    private var _patronymic: String? = null
    private var _telephone: String? = null
    private var _telegram: String? = null
    private var _mail: String? = null
    private var _git: String? = null

    override var id: Int = _id
        set(value) {
            field = value
            setMaxId(value)
        }

    var firstName: String
        get() = _firstName
        set(value) {
            _firstName = value
        }

    var lastName: String
        get() = _lastName
        set(value) {
            _lastName = value
        }

    var patronymic: String?
        get() = _patronymic
        set(value) {
            _patronymic = value
        }

    var telephone: String?
        get() = _telephone
        set(value) {
            if (!Validator.isTelephoneNumberValid(value)) throw Exception("Неверный telephoneNumber")
            _telephone = value
        }

    var telegram: String?
        get() = _telegram
        set(value) {
            if (!Validator.isTelegramValid(value)) throw Exception("Неверный telegram")
            _telegram = value
        }

    var mail: String?
        get() = _mail
        set(value) {
            //if (!Validator.isEmailValid(value)) throw Exception("Неверный email")
            _mail = value
        }

    override var git:String?=_git
        set(value:String?) {
            if(this.checkValueAndPropertyNotNull(value,field)) {
                Validator.isGitValid(value)
                field = value;
            }
        };

    private fun checkValueAndPropertyNotNull(value:String?,propertyValue:String?) = value==null && propertyValue!=null || value!=null

    constructor(
        id: Int,
        firstName: String,
        lastName: String,
        patronymic: String? = null,
        telephone: String? = null,
        telegram: String? = null,
        mail: String? = null,
        git: String? = null
    ) {
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
        if (params.getOrDefault("id", null).toString()
                .toIntOrNull() == null
        ) generateId() else (params["id"].toString().toInt()),
        params["firstName"] as String,
        params["lastName"] as String,
        params["patronymic"] as String,
        params["telephone"] as String?,
        params["telegram"] as String?,
        params["git"] as String?,
        params["mail"] as String?
    )

//    constructor(data: String): this(
//        id = data.split(";")[0].toIntOrNull()?:throw NumberFormatException("Incorrect ID!: ${data.split(";")[0]}"),
//        firstName = data.split(";")[1],
//        lastName = data.split(";")[2],
//        patronymic = data.split("2").getOrNull(3),
//        telephone = data.split(";").getOrNull(4),
//        telegram = data.split(";").getOrNull(5),
//        git = data.split(";").getOrNull(6),
//        mail = data.split(";").getOrNull(7),
//    )

    private fun hasGit(): Boolean {
        return git != null && git!!.isNotBlank()
    }

    companion object {
        fun fromString(data: String): Student{
            println(data)
            val params = data.split(";")
            println(params)
            val id = params[0].toIntOrNull() ?: throw NumberFormatException("Неверный формат ID: ${params[0]}")
            // Получаем остальные параметры
            val firstName = params[1]
            val lastName = params[2]
            val patronymic = params[3].takeIf { it != "null" }
            val telephone = params[4].takeIf { it != "null" }
            val telegram = params[5].takeIf { it != "null" }
            val mail = params[6].takeIf { it != "null" }
            val git = params[7].takeIf { it != "null" }

            return Student(id, firstName, lastName, patronymic, telephone, telegram, mail, git)
        }
    }


    private fun hasContact(): Boolean {
        return !telephone.isNullOrBlank() || !telegram.isNullOrBlank() || !mail.isNullOrBlank()
    }


    fun validate(): Boolean {
        return hasGit() && hasContact()
    }
    fun getInfo(): String {
        return "Фамилия: $lastName, " + getInitials() + ", Git: ${git ?: "не указан"}, " + getContact()
    }

    fun getContact(): String {
        return when {
            !telephone.isNullOrBlank() -> telephone!!
            !telegram.isNullOrBlank() -> telegram!!
            !mail.isNullOrBlank() -> mail!!
            else -> "нет"
        }
    }
    fun getInitials(): String {
        return "${firstName.firstOrNull() ?: ""}.${patronymic?.firstOrNull() ?: ""}."
    }

    override fun compareTo(other: Student): Int {
        return when {
            lastName != other.lastName -> lastName.compareTo(other.lastName)
            else -> firstName.compareTo(other.firstName)
        }
    }

    override fun toString(): String {
        return "student.Student(id=$id, firstName='$firstName', lastName='$lastName', patronymic='$patronymic', telephone=$telephone, telegram=$telegram, mail=$mail, git = $git)"
    }

    override fun propertiesReturn(): Map<String, Any?> =
        mapOf(
            "id" to this.id,
            "lastname" to this.lastName,
            "firstname" to this.firstName,
            "patronymic" to this.patronymic,
            "telephone" to this.telephone,
            "mail" to this.mail,
            "telegram" to this.telegram,
            "git" to this.git
        )
}
