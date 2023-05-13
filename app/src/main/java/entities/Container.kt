package entities

class Container {
    private var code:String? = null
    private var type:String? = null

    constructor(code:String, type:String) {
        this.code = code
        this.type = type

    }
    constructor(){
        code = ""
       type = ""

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
}