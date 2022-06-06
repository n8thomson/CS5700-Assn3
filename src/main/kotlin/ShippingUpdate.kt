import java.time.Instant

interface ShippingUpdate {
    var previousStatus: String
    var newStatus: String
    var timeStamp: Long
}

class CreatedUpdate(var id: String, override var timeStamp: Long, type: String) : ShippingUpdate{
    override var newStatus = "created"
    override var previousStatus = ""

    var newShipment = ShipmentFactory.createShipment("created", id, timeStamp, type)
        private set;

}

class ShippedUpdate(var shipment: Shipment, override var timeStamp: Long, var expectedArrival: Long) : ShippingUpdate{
    override var newStatus = "shipped"
    override var previousStatus = shipment.status
    init{
        shipment.status = newStatus
        shipment.addUpdate("Shipment went from $previousStatus to $newStatus at ${Instant.ofEpochMilli(timeStamp)}$".dropLast(2)  )
        shipment.expectedDeliveryDateTimeStamp = expectedArrival
        if (!shipment.isValid())(
                shipment.addUpdate(shipment.errorMessage)
        )
    }
}

class LocationUpdate(var shipment: Shipment, override var timeStamp: Long, var newLocation: String) : ShippingUpdate{
    override var newStatus = shipment.status
    override var previousStatus = ""
    init{
        shipment.currentLocation = newLocation
    }
}

class DeliveredUpdate(var shipment: Shipment, override var timeStamp: Long) : ShippingUpdate{
    override var newStatus = "delivered"
    override var previousStatus = shipment.status
    init{
        shipment.status = newStatus
        shipment.addUpdate("Shipment went from $previousStatus to $newStatus at ${Instant.ofEpochMilli(timeStamp)}$".dropLast(2)  )
    }
}
class DelayedUpdate(var shipment: Shipment, override var timeStamp: Long, var newExpected: Long) : ShippingUpdate{
    override var newStatus = "delayed"
    override var previousStatus = shipment.status
    init{
        shipment.status = newStatus
        shipment.addUpdate("Shipment went from $previousStatus to $newStatus at ${Instant.ofEpochMilli(timeStamp)}$".dropLast(2))
        shipment.expectedDeliveryDateTimeStamp = newExpected

    }
}

class LostUpdate(var shipment: Shipment, override var timeStamp: Long) : ShippingUpdate{
    override var newStatus = "lost"
    override var previousStatus = shipment.status
    init{
        shipment.status = newStatus
        shipment.addUpdate("Shipment went from $previousStatus to $newStatus at ${Instant.ofEpochMilli(timeStamp)}$".dropLast(2)  )
    }
}

class CanceledUpdate(var shipment: Shipment, override var timeStamp: Long) : ShippingUpdate{
    override var newStatus = "canceled"
    override var previousStatus = shipment.status
    init{
        shipment.status = newStatus
        shipment.addUpdate("Shipment went from $previousStatus to $newStatus at ${Instant.ofEpochMilli(timeStamp)}$".dropLast(2)  )
    }
}

class NoteAddedUpdate(var shipment: Shipment, override var timeStamp: Long, var note: String) : ShippingUpdate{
    override var newStatus = shipment.status
    override var previousStatus = ""
    init{
        shipment.addNote(note)
    }
}
