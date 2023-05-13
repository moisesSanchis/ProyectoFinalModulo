package entities


class Learner {
    private var image: Int?= null
    private var url:String?=null

    constructor(image:Int, url:String) {
        this.image = image
        this.url = url

    }
    constructor(){
        image = null
        url = ""

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