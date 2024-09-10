package ru.kubsu.lr_1

class Student{
    private lateinit var _firstName: String
    private lateinit var _lastName: String
    private lateinit var _patronymic: String
    private var _telephone: String? = null
    private var _telegram:String? = null
    private var _mail:String? = null

    constructor(
        id: Int,
        firstName: String,
        lastName: String,
        patronymic: String,
        telephone: String?,
        telegram: String?,
        mail: String?,
    ){
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.patronymic = patronymic
        this.telephone = telephone
        this.telegram = telegram
        this.mail = mail
    }

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
        set(value){_mail =value}

    override fun toString(): String {
        return "Student(id=$id, firstName='$firstName', lastName='$lastName', patronymic='$patronymic', telephone=$telephone, telegram=$telegram, mail=$mail)"
    }
}
