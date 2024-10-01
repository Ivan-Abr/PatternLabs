

class ShortStudent(
    val id: Int,
    val fio: String,
    val git: String?,
    val contact: String?
){
    constructor(student: Student): this(
        student.id,
        "${student.lastName} ${student.firstName[0]}.${student.patronymic[0]}",
        student.git,
        student.getContact()
    )
    override fun toString(): String {
        return "ShortStudent(id: $id, fio='$fio', git: $git, contact=$contact)"
    }

    companion object{
        fun getShortStudentFromString(id: Int, data: String): ShortStudent{
            /*
                Вид данных в строке: "ФИО; гит; контакт"
            */
            val parsedData = data.split("; ")
            return ShortStudent(
                id,
                parsedData[0],
                parsedData.getOrNull(1),
                parsedData.getOrNull(2)
            )

        }
    }
}