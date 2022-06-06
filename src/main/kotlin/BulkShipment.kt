class BulkShipment(status: String, id: String, created: Long): Shipment(status, id, created, type = "bulk") {

    override var errorMessage: String = "An express shipment was updated to include a delivery date earlier than 3 days after it was created"


    override fun isValid(): Boolean{
        return (expectedDeliveryDateTimeStamp - created) >= 259200000L
    }
}