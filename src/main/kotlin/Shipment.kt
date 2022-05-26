import kotlinx.coroutines.delay
import androidx.compose.runtime.mutableStateListOf

class Shipment(status: String, id: String): Observable {
    var status: String = ""
    var id: String = ""
    var notes: MutableList<String> = mutableStateListOf()
        private set;
    var updateHistory: MutableList<String> = mutableStateListOf()
        private set;
    var expectedDeliveryDateTimeStamp: Long = 0L
    var currentLocation: String = "Unknown"

    private val observers = mutableListOf<Observer>()
    private var running = false
    init {
        this.status = status
        this.id = id

    }

    fun addNote(note: String) {
        this.notes.add(note)
    }

    fun addUpdate(update: String) {
        this.updateHistory.add(update)
    }

    override fun addObserver(observer: Observer) {
        observers.add(observer)
    }

   override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    private fun notifyObservers() {
        observers.forEach { it.notify(status)}
    }

    suspend fun start() {
        running = true;
        while (running) {
            delay(1000)
            notifyObservers()
        }
    }

    fun stop() {
        running = false
    }
}