class DataListStudent(students: List<Student>) : DataList<Student>(students) {

    override fun getNames(): List<String> {
        return listOf("firstName","lastName","patronymic","phone", "telegram","git","mail")
    }

    override fun fetchData(): Array<Array<String?>> {
        val data = mutableListOf<Array<String?>>()
        for ((index, student) in (super.getSelected().map { it to elements[it] })){
            val row = arrayOf(
                (index + 1).toString(),
                student.firstName,
                student.lastName,
                student.patronymic,
                student.telephone,
                student.telegram,
                student.git,
                student.mail,
            )
            data.add(row)
        }
        return data.toTypedArray()
    }

    override fun formatData(data: Array<Array<String?>>): Array<Array<String>>{
        val formatedData = mutableListOf<Array<String>>()
        for (row in data){
            val newRow  = arrayOf(
                row[0]!!,
                row[1]!!,
                row[2]!!,
                row.getOrNull(3)?:"",
                row.getOrNull(4)?:"",
                row.getOrNull(5)?:"",
                row.getOrNull(6)?:"",
                row.getOrNull(7)?:"",
                    )

            formatedData.add(newRow)
        }

        return formatedData.toTypedArray()
    }

    override fun getData(): DataTable<String> {
        val data = super.getData()
        return data
    }
}
