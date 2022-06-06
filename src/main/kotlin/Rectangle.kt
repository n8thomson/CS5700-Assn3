open class Rectangle(p1: Point = Point (0.0, 0.0), p2: Point = Point (1.0, 1.0)) {
    open var p1: Point = Point(0.0, 0.0)
        set (value){
            if (!Rectangle(value, p2).isValid()){
                field = p1
            } else {
                field = value
            }
        }
    open var p2: Point = Point(1.0, 1.0)
        set (value) {
            if (!Rectangle(p1, value).isValid()) {
                field = p2
            } else {
                field = value
            }
        }
    init {
        this.p1 = p1
        this.p2 = p2
        //set twice to ensure unnecessary setter overrides dont occur
        this.p1 = p1
        this.p2 = p2
    }


    fun isSquare(): Boolean{
        if(Math.abs(p1.x - p2.x) == Math.abs(p1.y - p2.y) && isValid()){
            return true
        }
        return false
    }

    fun isValid(): Boolean{
        if (p1.x == p2.x || p1.y == p2.y){
            return false
        }
        return true

    }

    fun getArea(): Double {
        return (Math.abs(p1.x - p2.x) * Math.abs(p1.y - p2.y))
    }



}