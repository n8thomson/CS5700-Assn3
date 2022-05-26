import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TrackerViewHelper(var shipment: Shipment): Observer {
    var shipmentId by mutableStateOf("")
        private set;
    var shipmentNotes: MutableList<String> = mutableStateListOf()
        private set;
    var shipmentUpdateHistory : MutableList<String> = mutableStateListOf()
        private set;
    var expectedShipmentDeliveryDate by mutableStateOf(0L)
        private set;
    var shipmentStatus by mutableStateOf("")
        private set;
    var shipmentLocation by mutableStateOf("")
        private set;


    init {
        shipment.addObserver(this)
        shipmentId = shipment.id
        shipmentStatus = shipment.status
        shipmentNotes = shipment.notes
        shipmentUpdateHistory = shipment.updateHistory
        expectedShipmentDeliveryDate = shipment.expectedDeliveryDateTimeStamp
        shipmentLocation = shipment.currentLocation

    }

    override fun notify(shipmentId: String, shipmentStatus: String, shipmentNotes: MutableList<String>, shipmentUpdateHistory: MutableList<String>, expectedShipmentDeliveryDate: Long, shipmentLocation: String) {
        this.shipmentId = shipmentId
        this.shipmentStatus = shipmentStatus
        this.shipmentNotes = shipmentNotes
        this.shipmentUpdateHistory = shipmentUpdateHistory
        this.expectedShipmentDeliveryDate = expectedShipmentDeliveryDate
        this.shipmentLocation = shipmentLocation

    }

}