import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TrackerViewHelper(var shipment: Shipment): Observer {
    var shipmentId by mutableStateOf("")
        private set;
    var shipmentTotes = mutableStateListOf<String>()
        private set;
    var shipmentUpdateHistory = mutableStateListOf<ShippingUpdate>()
        private set;
    var expectedShipmentDeliveryDate = mutableStateListOf<String>()
        private set;
    var shipmentStatus by mutableStateOf("")
        private set;


    init {
        shipment.addObserver(this)
        shipmentId = shipment.id
        shipmentStatus = shipment.status
    }

    override fun notify(shipmentStatus: String) {
        this.shipmentStatus = shipmentStatus
    }

}