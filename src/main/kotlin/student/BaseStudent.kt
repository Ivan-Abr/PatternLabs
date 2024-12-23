package student

import kotlin.math.max

abstract class BaseStudent(
   open var id: Int = generateId(),
   open var git: String? = null,
){
    companion object {
        private var currentId = 0
        private var maxId = 0

        internal fun generateId():Int{
            currentId = maxId
            maxId += 1
            return currentId
        }

        internal fun setMaxId(id: Int){
            maxId = max(maxId, id)
        }

        internal fun parseString(data:String):HashMap<String,Any?>{
            val dataSplit = data.split(',')
            val hashData = HashMap<String,Any?>();
            for (propertyValue in dataSplit){
                if(!propertyValue.trim().matches("[a-zA-Z]+:.*".toRegex())){
                    throw Exception("Неверный формат строки")
                }
                else{
                    val (key,propertyVal) = propertyValue.split(":".toRegex(),2);
                    hashData.set(key.trim(),if(propertyVal=="") null else propertyVal.trim());
                }
            }
            return hashData
        }
    }

    abstract fun propertiesReturn(): Map<String, Any?>

    override fun toString(): String {
        return "BaseStudent(id=$id, git=$git)"
    }
}
