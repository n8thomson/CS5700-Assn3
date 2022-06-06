class Circle(p: Point = Point(0.0, 0.0), r1: Double = 1.0, r2: Double = 1.0): Ellipse(p, r1, r2) {

    override var r1: Double = 1.0
        set (value){
            if (!Circle(p, value, r2).isCircle()){
                field = r1
            } else {
                field = value
            }
        }

    override var r2: Double = 1.0
        set (value){
            if (!Circle(p, r1, value).isCircle()){
                field = r2
            } else {
                field = value
            }
        }
}