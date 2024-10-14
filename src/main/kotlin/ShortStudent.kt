class ShortStudent(
    id: Int,
    firstName: String,
    lastName: String,
    git: String?,
    telephone: String?,
    telegram: String?,
    mail: String?
) : BaseStudent(id,firstName, lastName, null,git, telephone,telegram,mail){
    constructor(student: Student): this(
        student.id,
        student.firstName,
        student.lastName,
        student.git,
        student.telephone,
        student.telegram,
        student.mail
    )

    constructor(id: Int, data: String): this(
        id = id,
        firstName = data.split(";")[0],
        lastName = data.split(";")[1],
        git = data.split(";").getOrNull(2),
        telephone = data.split(";").getOrNull(3),
        telegram = data.split(";").getOrNull(4),
        mail = data.split(";").getOrNull(5)
    )
    override fun toString(): String {
        return "ShortStudent($id; $firstName; $lastName; $git; $telephone; $telegram; $mail)"
    }

}
