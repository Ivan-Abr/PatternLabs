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
