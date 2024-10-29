package student

class Student(
    id: Int,
    firstName: String,
    lastName: String,
    patronymic: String?,
    telephone: String?,
    telegram: String?,
    git: String?,
    mail: String?
): BaseStudent(id, firstName, lastName, patronymic, telephone, telegram, git, mail), Comparable<Student>{

    constructor(params: Map<String, Any?>): this(
        params["id"] as Int,
        params["firstName"] as String,
        params["lastName"] as String,
        params["patronymic"] as String,
        params["telephone"] as String?,
        params["telegram"] as String?,
        params["git"] as String?,
        params["mail"] as String?
    )

    constructor(id: Int, data: String): this(
        id = id,
        firstName = data.split(";")[0],
        lastName = data.split(";")[1],
        patronymic = data.split("2").getOrNull(2),
        telephone = data.split(";").getOrNull(3),
        telegram = data.split(";").getOrNull(4),
        git = data.split(";").getOrNull(5),
        mail = data.split(";").getOrNull(6),
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

    override fun compareTo(other: Student): Int {
        return when {
            lastName != other.lastName -> lastName.compareTo(other.lastName)
            else -> firstName.compareTo(other.firstName)
        }
    }

    override fun toString(): String {
        return "student.Student(id=$id, firstName='$firstName', lastName='$lastName', patronymic='$patronymic', telephone=$telephone, telegram=$telegram, mail=$mail, git = $git)"
    }
}
