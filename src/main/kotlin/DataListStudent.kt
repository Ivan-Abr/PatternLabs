class DataListStudent(students: List<Student>) : DataList<Student>(students) {

    override fun getNames(): List<String> {
        return listOf("firstName","lastName","patronymic","phone", "telegram","git","mail")
    }

    override fun getData(): DataTable<String> {
        val data = mutableListOf<Array<String>>()
        for ((index, student) in (super.getSelected().map { it to elements[it] })){
            val row = arrayOf(
                (index + 1).toString(),
                student.firstName,
                student.lastName,
                student.patronymic ?:"",
                student.patronymic ?:"",
                student.telephone ?:"",
                student.telegram ?:"",
                student.git ?:"",
                student.mail ?:"",
            )
            data.add(row)
        }
        return DataTable(data.toTypedArray())
    }
}