open class DataList<T: Comparable<T>>(private val elements: List<T>) {
    private val sortedElements: List<T> = elements.sorted()
    private val selectedElements = mutableSetOf<Int>()
}
