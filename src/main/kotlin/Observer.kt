interface Observer {
    fun notify(shipmentId: String, shipmentStatus: String, shipmentNotes: MutableList<String>, shipmentUpdateHistory: MutableList<String>, expectedShipmentDeliveryDate: Long, shipmentLocation: String)
}