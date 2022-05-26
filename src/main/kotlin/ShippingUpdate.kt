interface ShippingUpdate {
    var previousStatus: String
    var newStatus: String
    var timeStamp: Long
}

class CreatedUpdate(override var previousStatus: String = "", override var timeStamp: Long) : ShippingUpdate{
    override var newStatus = "Created"

}

class ShippedUpdate(override var previousStatus: String, override var newStatus: String, override var timeStamp: Long) : ShippingUpdate{

}

class LocationUpdate(override var previousStatus: String, override var newStatus: String, override var timeStamp: Long) : ShippingUpdate{

}

class DeliveredUpdate(override var previousStatus: String, override var newStatus: String, override var timeStamp: Long) : ShippingUpdate{

}

class DelayedUpdate(override var previousStatus: String, override var newStatus: String, override var timeStamp: Long) : ShippingUpdate{

}

class LostUpdate(override var previousStatus: String, override var newStatus: String, override var timeStamp: Long) : ShippingUpdate{

}

class CanceledUpdate(override var previousStatus: String, override var newStatus: String, override var timeStamp: Long) : ShippingUpdate{

}

class NoteAddedUpdate(override var previousStatus: String, override var newStatus: String, override var timeStamp: Long) : ShippingUpdate{

}
