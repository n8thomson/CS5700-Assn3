import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ShipmentTest {



    @Test
    fun addUpdate() {
        var s: Shipment = Shipment("hungry", "s10000")
        s.addUpdate("ballin")
        assertTrue(s.updateHistory[s.updateHistory.size-1] == "ballin")
    }

    @Test
    fun addNote() {
        var s: Shipment = Shipment("hungry", "s10000")
        s.addUpdate("ballin")
        assertTrue(s.updateHistory[s.updateHistory.size-1] == "ballin")
    }



}