import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PointTest {


    @Test
    fun testClone() {
        var p1: Point = Point(9.8, 2.2)
        var clone: Point = p1.clone()
        assertEquals(clone.x, p1.x)

        assertEquals(clone.y, p1.y)
    }

    @Test
    fun move() {
        var p1: Point = Point(2.0, 3.0)
        p1.move(4.7, 9.8)
        assertEquals(p1.x, 6.7)
        assertEquals(p1.y, 12.8)
        p1.move(-4.7, -9.8)
        assertEquals(p1.x, 2.0)
        assertEquals(p1.y, 3.0)
    }

    @Test
    fun isEqual() {
        var p1: Point = Point(5.0, 5.0)
        var p2: Point = Point(5.0, 5.0)
        var p3: Point = Point(6.0, 6.0)

        assertTrue(p1.isEqual(p1))

        assertTrue(p1.isEqual(p2))
        assertTrue(p2.isEqual(p1))

        assertFalse(p1.isEqual(p3))
        assertFalse(p3.isEqual(p1))
    }
}