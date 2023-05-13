package entities

import android.media.Image
import com.msp.proyectomoises.R
import java.io.Serializable

class User : Serializable {

    private var id: Int? = null
    private var name: String? = null
    private var password: String? = null
    private var mail: String? = null
    private var points: Int? = null
    private var image: Int? = null

    constructor(name: String, password: String, points: Int, mail: String, image: Int) {
        id = 0
        this.name = name
        this.password = password
        this.mail = mail
        this.points = points
        this.image = image

    }

    constructor() {
        id = 0
        name = ""
        password = ""
        mail = ""
        points = 0
        image = R.drawable.icon_profile

    }

    fun getName(): String? {
        return this.name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getPassword(): String? {
        return this.password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getMail(): String? {
        return this.mail
    }

    fun setMail(mail: String) {
        this.mail = mail
    }

    fun getId(): Int? {
        return this.id
    }

    fun getPoints(): Int? {
        return this.points
    }

    fun setPoints(points: Int) {
        this.points = points
    } fun getImage(): Int? {
        return this.image
    }

    fun setImage(image: Int) {
        this.image = image
    }

}