import java.time.LocalDateTime

class OvernightShipment(status: String, id: String, created: Long): Shipment(status, id, created, type = "overnight") {

    override var errorMessage: String = "An overnight shipment was updated to include a delivery date later than 24 hours after it was created"


    override fun isValid(): Boolean{
        return (expectedDeliveryDateTimeStamp - created) < 86400000L
    }
}