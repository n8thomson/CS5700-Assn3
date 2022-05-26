import java.io.File
import java.io.InputStream
import kotlinx.coroutines.delay


class TrackingSimulator {
    companion object TrackingSimulatorObject{

            private var shipments = mutableListOf<Shipment>()

            suspend fun runSimulation() {
                val inputStream: InputStream = File("test.txt").inputStream()
                val lineList = mutableListOf<String>()

                inputStream.bufferedReader().forEachLine { lineList.add(it) }
                lineList.forEach{
                    println(">  " + it)
                    delay(1000)
                }
            }

            fun findShipment(id: String) {}

            fun addShipment(shipment: Shipment) {}



    }
}