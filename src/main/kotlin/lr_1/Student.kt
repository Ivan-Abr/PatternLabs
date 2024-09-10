package ru.kubsu.lr_1

class Student{
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
        get() = id
        set(value){id = value}

    var firstName: String
        get() = firstName
        set(value){firstName = value}

    var lastName: String
        get() = lastName
        set(value){lastName = value}

    var patronymic: String
        get() = patronymic
        set(value) {patronymic = value}

    var telephone: String?
        get() = telephone
        set(value){telephone = value}

    var telegram: String?
        get() = telegram
        set(value){telegram = value}

    var mail: String?
        get() = mail
        set(value){mail =value}
}
