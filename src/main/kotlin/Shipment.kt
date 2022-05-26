import kotlinx.coroutines.delay
import androidx.compose.runtime.mutableStateListOf

class Shipment(status: String, id: String, updateHistory: MutableList<ShippingUpdate>, expectedDeliveryDateTimeStamp: Long): Observable {
    var status: String = ""
    var id: String = ""
    var notes: MutableList<String> = mutableStateListOf()
        private set;
    var updateHistory: MutableList<ShippingUpdate> = mutableStateListOf()
        private set;
    var expectedDeliveryDateTimeStamp: Long = 0L
    var currentLocation: String = ""

    private val observers = mutableListOf<Observer>()
    private var numSecondsPassed = 0
    private var running = false
    init {
        this.status = status
        this.id = id
        this.updateHistory = updateHistory
        this.expectedDeliveryDateTimeStamp = expectedDeliveryDateTimeStamp
    }

    fun addNote(note: String) {
        this.notes.add(note)
    }

    fun addUpdate(update: ShippingUpdate) {
        this.updateHistory.add(update)
    }

    override fun addObserver(observer: Observer) {
        observers.add(observer)
    }

   override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    private fun notifyObservers() {
        observers.forEach { it.notify(numSecondsPassed)}
    }

    suspend fun start() {
        running = true;
        while (running) {
            delay(1000)
            numSecondsPassed += 1
            notifyObservers()
        }
    }

    fun stop() {
        running = false
    }
}