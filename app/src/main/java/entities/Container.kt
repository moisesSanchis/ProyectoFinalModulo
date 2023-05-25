package entities

class Container {
    private var code:String? = null
    private var type:String? = null
    private var recycled:String? = null

    constructor(code:String, type:String, recycled:String) {
        this.code = code
        this.type = type
        this.recycled = recycled

    }
    constructor(){
        code = ""
       type = ""
        recycled = "not"

    }
    fun getCode():String?{
        return this.code
    }
    fun setCode(code:String){
        this.code = code
    }
    fun getType():String?{
        return this.type
    }
    fun setType(type:String){
        this.type = type
    }
    fun getRecycled():String?{
        return this.recycled
    }
    fun setRecycled(recycled:String){
        this.recycled = recycled
    }
}