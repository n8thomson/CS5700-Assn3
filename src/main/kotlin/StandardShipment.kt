class StandardShipment(status: String, id: String, created: Long): Shipment(status, id, created, type = "standard") {

    override var errorMessage: String = "Error"

    override fun isValid(): Boolean{
        return true
    }
}