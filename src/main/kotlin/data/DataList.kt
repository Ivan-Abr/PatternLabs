package data

import Main.UpdateDataInterface
import TableGridPack.MainTable
import TableGridPack.TableView
import data.DataTable
import java.util.Observer
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.memberFunctions

open class DataList<T>(val elements: Array<T>) {
    private val selectedIndices = mutableSetOf<Int>()
    // Метод для выделения элемента по номеру
    fun select(number: Int) {
        if (number in elements.indices) {
            selectedIndices.add(number)
        } else {
            throw IndexOutOfBoundsException("Индекс вне диапазона")
        }
    }
    //Метод для убирания селекта
    fun unSelectAll() {
        selectedIndices.clear()
    }
    // Метод для получения массива ID выделенных элементов
    fun getSelectedIds() = selectedIndices.toIntArray()

    // Метод для получения массива наименований атрибутов (не реализован, так как не имеет информации об объектах)
    public fun getNames(): Array<String>{
        if(this.elements.isEmpty() || this.elements[0]!!::class.companionObject?.memberFunctions?.find{ it.name == "returnPropertyNames" }==null)  return arrayOf<String>()
        else{
            return (this.elements[0]!!::class.companionObject?.memberFunctions?.find{ it.name == "returnPropertyNames" }!!.call(this.elements[0]!!::class.companionObject?.objectInstance) as Set<String>).drop(1).toTypedArray<String>()
        }
    }


    // Метод для получения DataTable (не реализован, так как не имеет информации об объектах)
    protected open fun getPropertiesOfClass(value:T):List<Any?>{
        if(this.elements.isEmpty() || value!!::class.memberFunctions.find{ it.name == "propertiesReturn" }==null)  return listOf<Any?>()
        else{
            return (value!!::class.memberFunctions.find{ it.name == "propertiesReturn" }!!.call(value) as HashMap<String,Any?>).values.toList<Any?>()
        }
    }
    fun getData(): DataTable {
        val dataList:MutableList<Array<Any?>> = mutableListOf()
        var rowNumber = 0;
        dataList.add(arrayOf("ID",*getNames()))
        for(el in this.elements){
            dataList.add(
                arrayOf(*getPropertiesOfClass(el).toTypedArray<Any?>())
            )
            rowNumber+=1;
        }
        return DataTable(dataList.toTypedArray<Array<Any?>>())
    }


    //Задаем для обновления вьюх
    public var tableView: TableView? = null
        set(value){
            field = value;
            this.notifyView();
        };
    public fun notifyView(){
        val dataTable  = this.getData();

        val colNames = Array(dataTable.getColumnCount()){" "};
        for(i in 0 until  this.getData().getColumnCount()){
            colNames[i] = dataTable.getElement(0,i).toString();
        }
        this.tableView?.setTableParams(colNames,this.elements.size)
        this.tableView?.setTableData(dataTable)
    }

    public var subs:MutableList<UpdateDataInterface> = mutableListOf()
    public fun subscribe(ob:UpdateDataInterface){
        this.subs.add(ob);
    }
    public fun notifySubs(){
        for(el in this.subs){
            el.updatePage()
        }
    }
}