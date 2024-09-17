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
        set(value){_telephone = value}

    var telegram: String?
        get() = _telegram
        set(value){_telegram = value}

    var mail: String?
        get() = _mail
        set(value){_mail = value}

    var git: String?
        get() = _git
        set(value){_git = value}

    override fun toString(): String {
        return "Student(id=$id, firstName='$firstName', lastName='$lastName', patronymic='$patronymic', telephone=$telephone, telegram=$telegram, mail=$mail, git = $git)"
    }
}
