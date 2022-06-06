class Point(x: Double = 0.0, y: Double = 0.0) {
    var x: Double = 0.0
//        set (value){
//            if (value < 0.0){
//                field = 0.0
//            } else {
//                field = value
//            }
//        }
    var y: Double = 0.0
//        set (value){
//            if (value < 0.0){
//                field = 0.0
//            } else {
//                field = value
//            }
//        }
    init {
        this.x = x
        this.y = y
    }

    fun clone(): Point{
        return Point(this.x, this.y)
    }

    fun move(deltaX: Double, deltaY: Double){
        this.x += deltaX
        this.y += deltaY
    }

    fun isEqual(p: Point): Boolean{
        if (this.x == p.x && this.y == p.y){
            return true
        }
        return false
    }

}