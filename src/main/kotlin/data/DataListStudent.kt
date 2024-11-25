package data

import student.ShortStudent

class DataListStudent(students: List<ShortStudent>) : DataList<ShortStudent>(students) {

    override fun getNames(): List<String> {
        return listOf("№","name","git","contacts")
    }

    fun printNames(list: List<String>){
        println(list.joinToString(" "))
    }

    override fun fetchData(): Array<Array<String?>> {
        return super.elements.mapIndexed {index, student ->
            arrayOf(
                (index + 1).toString(),
                student.nameAndInitials,
                student.git,
                student.contacts,
            )
        }.toTypedArray()
    }

    override fun formatData(data: Array<Array<String?>>): Array<Array<String>>{
        val formatedData = mutableListOf<Array<String>>()
        for (row in data){
            val newRow  = arrayOf(
                row[0]!!,
                row[1]!!,
                row.getOrNull(2)?:"",
                row.getOrNull(3)?:"",
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
