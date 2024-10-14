open class BaseStudent(
    var id: Int,
    var firstName: String,
    var lastName: String,
    var patronymic: String? = null,
    var git: String? = null,
    var telephone: String? = null,
    var telegram: String? = null,
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
        val initials = " ${firstName}. ${patronymic}."
        return lastName + initials
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
}