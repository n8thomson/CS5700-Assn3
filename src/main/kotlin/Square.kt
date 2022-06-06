class Square(p1: Point = Point(0.0, 0.0), p2: Point = Point(1.0, 1.0)): Rectangle(p1, p2) {
    override var p1: Point = Point(0.0, 0.0)
        set (value){
            if (!Square(value, p2).isSquare()){
                field = p1
            } else {
                field = value
            }
        }
    override var p2: Point = Point(1.0, 1.0)
        set (value){
            if (!Square(p1, value).isSquare()){
                field = p2
            } else {
                field = value
            }
        }



}