class Triangle(p1: Point = Point(0.0, 0.0), p2: Point = Point(1.0, 0.0), p3: Point = Point(0.0, 1.0)) {
    var p1: Point = Point(0.0, 0.0)
        set (value){
            if (Triangle(value, p2, p3).getArea() == 0.0){
                field = p1
            } else {
                field = value
            }
        }
    var p2: Point = Point(1.0, 0.0)
        set (value){
            if (Triangle(p1, value, p3).getArea() == 0.0){
                field = p2
            } else {
                field = value
            }
        }
    var p3: Point = Point(0.0, 1.0)
        set (value){
            if (Triangle(p1, p2, value).getArea() == 0.0){
                field = p3
            } else {
                field = value
            }
        }

    init {
        this.p1 = p1
        this.p2 = p2
        this.p3 = p3
        //set twice to ensure unnecessary setter overrides dont occur
        this.p1 = p1
        this.p2 = p2
        this.p3 = p3
    }


    fun getArea(): Double{
        return (p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y))
    }

    fun move(deltaX: Double, deltaY: Double){
        this.p1.move(deltaX, deltaY)
        this.p2.move(deltaX, deltaY)
        this.p3.move(deltaX, deltaY)
    }


}
