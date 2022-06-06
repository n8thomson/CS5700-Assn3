class ShipmentFactory {
    companion object{
        fun createShipment(status: String, id: String, created: Long, type: String): Shipment{
            when (type) {
                "bulk" -> {return BulkShipment(status, id, created)}
                "overnight" -> {return OvernightShipment(status, id, created)}
                "express" -> {return ExpressShipment(status, id, created)}
                else -> {return StandardShipment(status, id, created)}
            }


        }
    }
}