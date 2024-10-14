open class DataList<T: Comparable<T>>(val elements: List<T>) {
    private val sortedElements: List<T> = elements.sorted()
    private val selectedElements = mutableSetOf<Int>()

    fun select(number: Int){
        if (number in sortedElements.indices)
            selectedElements.add(number)
        else
            throw IndexOutOfBoundsException("number $number is not valid")
    }

    fun getSelected(): List<Int> = selectedElements.toList()

    open fun getNames():List<String>{
        throw UnsupportedOperationException("should be realized in nested class")
    }

    open fun getData(): DataTable<String>{
        throw UnsupportedOperationException("should be realized in nested class")
    }
}
