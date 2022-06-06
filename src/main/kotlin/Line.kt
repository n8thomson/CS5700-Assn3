class Line(p1: Point = Point (0.0, 0.0), p2: Point = Point (1.0, 0.0)) {
    var p1: Point = Point(0.0, 0.0)
        set (value){
            if (value.isEqual(p2)){
                field = p1
            } else {
                field = value
            }
        }
    var p2: Point = Point(1.0, 0.0)
        set (value) {
            if (value.isEqual(p1)) {
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

    fun getSlope(): Double{
        return ((p2.y - p1.y) / (p2.x - p1.x))
    }

    fun getLength(): Double{
        return (Math.sqrt(Math.pow((p2.y - p1.y), 2.0) + Math.pow((p2.x - p1.x), 2.0)))
    }

    fun move(deltaX: Double, deltaY: Double){
        this.p1.move(deltaX, deltaY)
        this.p2.move(deltaX, deltaY)
    }
}