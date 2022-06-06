open class Ellipse(p: Point, r1: Double, r2: Double) {
    var p: Point = Point(0.0 , 0.0)

    open var r1: Double = 1.0
        set(value){
            if (value <= 0){
                field = r1
            } else {
                field = value
            }
        }

    open var r2: Double = 2.0
        set(value){
            if (value <= 0){
                field = r2
            } else {
                field = value
            }
        }

    init{
        this.p = p
        this.r1 = r1
        this.r2 = r2

    }

    fun getArea(): Double{
        return Math.PI * r1 * r2
    }

    fun isCircle(): Boolean{
        if (r1 == r2){
            return true
        }
        return false
    }

    fun move(newP: Point){
        p = newP
    }
}