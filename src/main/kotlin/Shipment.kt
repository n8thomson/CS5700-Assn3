import kotlinx.coroutines.delay
import androidx.compose.runtime.mutableStateListOf

abstract class Shipment(status: String, id: String, created: Long, type: String): Observable {
    var status: String = ""
        set(value) {
            field = value
            notifyObservers()
        }
    var id: String = ""
        private set(value) {
            field = value
            notifyObservers()
        }
    var notes: MutableList<String> = mutableStateListOf()
        private set(value) {
            field = value
            notifyObservers()
        }
    var updateHistory: MutableList<String> = mutableStateListOf()
        private set(value) {
            field = value
            notifyObservers()
        }
    var created: Long = 0L
        private set;

    var type: String = ""
        private set(value) {
            field = value
            notifyObservers()
        }

    var expectedDeliveryDateTimeStamp: Long = 0L
        set(value) {
            field = value
            notifyObservers()
        }
    var currentLocation: String = "Unknown"
        set(value) {
            field = value
            notifyObservers()
        }


   abstract var errorMessage: String

    private val observers = mutableListOf<Observer>()

    init {
        this.status = status
        this.id = id
        this.created = created
        this.type = type
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
        observers.forEach { it.notify(id, status, notes, updateHistory, expectedDeliveryDateTimeStamp, currentLocation, type)}
    }


    abstract fun isValid(): Boolean



}