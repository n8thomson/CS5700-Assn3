import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LineTest {

    @Test
    fun setP1() {
        var l1: Line = Line(Point(1.0, 1.0), Point(2.0, 2.0))
        assertTrue(l1.p1.isEqual(Point(1.0, 1.0)))
        //try to change to identical point
        l1.p1 = Point(2.0, 2.0)
        //make sure it does not actually change
        assertFalse(l1.p1.isEqual(Point(2.0, 2.0)))
        //assure it remains unchanged
        assertTrue(l1.p1.isEqual(Point(1.0, 1.0)))

        //try to change normally
        l1.p1 = Point(3.0, 3.0)
        //assure it changes
        assertTrue(l1.p1.isEqual(Point(3.0, 3.0)))
    }

    @Test
    fun setP2() {
        var l1: Line = Line(Point(1.0, 1.0), Point(2.0, 2.0))
        assertTrue(l1.p2.isEqual(Point(2.0, 2.0)))
        //try to change to identical point
        l1.p2 = Point(1.0, 1.0)
        //make sure it does not actually change
        assertFalse(l1.p2.isEqual(Point(1.0, 1.0)))
        //assure it remains unchanged
        assertTrue(l1.p2.isEqual(Point(2.0, 2.0)))

        //try to change normally
        l1.p2 = Point(3.0, 3.0)
        //assure it changes
        assertTrue(l1.p2.isEqual(Point(3.0, 3.0)))
    }

    @Test
    fun getSlope() {
        //no slope
        var l1: Line = Line(Point(0.0, 0.0), Point(1.0, 0.0))
        assertEquals(l1.getSlope(), 0.0)
        //1.0 slope
        var l2: Line = Line(Point(0.0, 0.0), Point(1.0, 1.0))
        assertEquals(l2.getSlope(), 1.0)
        //5.0 slope
        var l3: Line = Line(Point(0.0, 0.0), Point(1.0, 5.0))
        assertEquals(l3.getSlope(), 5.0)
        //0.5 slope
        var l4: Line = Line(Point(0.0, 0.0), Point(2.0, 1.0))
        assertEquals(l4.getSlope(), 0.5)
        //-1.0 slope
        var l5: Line = Line(Point(0.0, 0.0), Point(1.0, -1.0))
        assertEquals(l5.getSlope(), -1.0)
    }

    @Test
    fun getLength() {
        //1.0 length horiz
        var l1: Line = Line(Point(1.0, 0.0), Point(2.0, 0.0))
        assertEquals(l1.getLength(), 1.0)
        //1.0 length vert
        var l2: Line = Line(Point(0.0, 1.0), Point(0.0, 2.0))
        assertEquals(l2.getLength(), 1.0)
        //5.0 length diagonal
        var l3: Line = Line(Point(0.0, 0.0), Point(-3.0, -4.0))
        assertEquals(l3.getLength(), 5.0)
        //0.5 length diagonal negative
        var l4: Line = Line(Point(0.0, 0.0), Point(0.3, -.4))
        assertEquals(l4.getLength(), 0.5)

    }

    @Test
    fun move() {
        var l1: Line = Line(Point(0.0, 0.0), Point(2.0, 2.0))
        l1.move(2.5, 8.9)
        assertEquals(l1.p1.x, 2.5)
        assertEquals(l1.p1.y, 8.9)
        assertEquals(l1.p2.x, 4.5)
        assertEquals(l1.p2.y, 10.9)
        l1.move(-2.5, -8.9)
        assertEquals(l1.p1.x, 0.0)
        assertEquals(l1.p1.y, 0.0)
        assertEquals(l1.p2.x, 2.0)
        assertEquals(l1.p2.y, 2.0)

    }
}