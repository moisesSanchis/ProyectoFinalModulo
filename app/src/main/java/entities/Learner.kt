package entities


class Learner {
    private var name:String?=null
    private var image: Int?= null
    private var url:String?=null


    constructor(name:String, image:Int, url:String) {
        this.name = name
        this.image = image
        this.url = url

    }
    constructor(){
        name = ""
        image = 0
        url = ""

    }
    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }
    fun getImage(): Int? {
        return image
    }

    fun setImage(image: Int?) {
        this.image = image
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }
}