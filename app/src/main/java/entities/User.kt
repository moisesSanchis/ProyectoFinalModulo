package entities

import java.io.Serializable

class User:Serializable{

   private var id: Int? = null
   private var name: String? = null
   private var password: String? = null
   private var mail: String? = null
   private var points: Int? = null

    constructor(name:String,password:String, points:Int, mail:String) {
        id = 0
        this.name = name
        this.password = password
        this.mail = mail
        this.points = points

    }
    constructor(){
        id = 0
        name = ""
        password = ""
        mail = ""
        points = 0
    }

    fun getName():String?{
        return this.name
    }
    fun setName(name:String){
        this.name = name
    }
    fun getPassword():String?{
        return this.password
    }
    fun setPassword(password:String){
        this.password = password
    }
    fun getMail():String?{
        return this.mail
    }
    fun setMail(mail:String){
        this.mail = mail
    }
    fun getId():Int?{
        return this.id
    }
    fun getPoints():Int?{
        return this.points
    }
    fun setPoints(points:Int){
        this.points = points
    }

}