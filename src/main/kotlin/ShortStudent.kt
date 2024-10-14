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
        firstName = data.split(";")[1],
        lastName = data.split(";")[2],
        git = data.split(";").getOrNull(3),
        telephone = data.split(";").getOrNull(4),
        telegram = data.split(";").getOrNull(5),
        mail = data.split(";").getOrNull(6)
    )
    override fun toString(): String {
        return "ShortStudent($id; $firstName; $lastName; $git; $telephone; $telegram; $mail)"
    }

}
