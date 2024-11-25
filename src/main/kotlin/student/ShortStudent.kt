package student

//import kotlinx.serialization.Serializable

//@Serializable
class ShortStudent : BaseStudent, Comparable<ShortStudent>{

    val nameAndInitials: String
    val contacts: String?

   constructor(student: Student): super(student.id, student.git){
       this.nameAndInitials = student.getInitials()
       this.contacts = student.getContact()
   }

    constructor(data: String) {
        this.id = data.split(";")[0].toInt()
        this.nameAndInitials = data.split(";")[1]
        this.git = data.split(";").getOrNull(2)
        this.contacts = data.split(";").getOrNull(3)
    }
    constructor(params: HashMap<String, Any?>){
        this.id = params["id"] as Int
        this.nameAndInitials = params["nameAndInitials"] as String
        this.git = params["git"] as String?
        this.contacts = params["contacts"] as String?

    }

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
