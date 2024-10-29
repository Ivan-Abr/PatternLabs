package student

class ShortStudent(
    var id: Int,
    var nameAndInitials: String,
    var git: String?,
    var contacts: String?,
): Comparable<ShortStudent>{
    constructor(student: BaseStudent): this(
        student.id,
        student.getLastnameAndInitials(),
        student.git,
        student.getContacts()
    )

    constructor(params: Map<String, Any?>): this(
        params["id"] as Int,
        params["nameAndInitials"] as String,
        params["git"] as String?,
        params["contacts"] as String?,
    )

    constructor(data: String): this(
        id = data.split(";")[0].toInt(),
        nameAndInitials = data.split(";")[1],
        git = data.split(";").getOrNull(2),
        contacts = data.split(";").getOrNull(3),
    )

    override fun compareTo(other: ShortStudent): Int {
        val nameComparison = this.nameAndInitials.compareTo(other.nameAndInitials)
        return if (nameComparison != 0) {
            nameComparison
        } else {
            this.id.compareTo(other.id)
        }
    }

    override fun toString(): String {
        return "student.ShortStudent(id=$id, nameAndInitials='$nameAndInitials', git=$git, contacts=$contacts)"
    }
}
