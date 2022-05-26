import java.time.Instant

interface ShippingUpdate {
    var previousStatus: String
    var newStatus: String
    var timeStamp: Long
}

class CreatedUpdate(var id: String, override var timeStamp: Long) : ShippingUpdate{
    override var newStatus = "created"
    override var previousStatus = ""
    var newShipment = Shipment(newStatus, id)
        private set;

}

class ShippedUpdate(var shipment: Shipment, override var timeStamp: Long, var expectedArrival: Long) : ShippingUpdate{
    override var newStatus = "shipped"
    override var previousStatus = shipment.status
    init{
        shipment.status = newStatus
        shipment.addUpdate("Shipment went from $previousStatus to $newStatus at ${Instant.ofEpochMilli(timeStamp)}$"  )
        shipment.expectedDeliveryDateTimeStamp = expectedArrival
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
        shipment.addUpdate("Shipment went from $previousStatus to $newStatus at ${Instant.ofEpochMilli(timeStamp)}$"  )
    }
}
class DelayedUpdate(override var previousStatus: String, override var newStatus: String, override var timeStamp: Long) : ShippingUpdate{

}

class LostUpdate(override var previousStatus: String, override var newStatus: String, override var timeStamp: Long) : ShippingUpdate{

}

class CanceledUpdate(override var previousStatus: String, override var newStatus: String, override var timeStamp: Long) : ShippingUpdate{

}

class NoteAddedUpdate(override var previousStatus: String, override var newStatus: String, override var timeStamp: Long) : ShippingUpdate{

}
