import java.io.File
import java.io.InputStream
import kotlinx.coroutines.delay


class TrackingSimulator {
    companion object TrackingSimulatorObject{

            private var shipments = mutableMapOf<String, Shipment>()

            suspend fun runSimulation() {
                val inputStream: InputStream = File("test.txt").inputStream()
                val lineList = mutableListOf<String>()

                inputStream.bufferedReader().forEachLine { lineList.add(it) }
                lineList.forEach{
                    println(">  " + it)
                    val item = it.split(",")
                    when (item[0]){
                        "created" -> {addShipment(CreatedUpdate(item[1], item[2].toLong(), item[3]).newShipment)}
                        "shipped" -> {
                            findShipment(item[1])?.let { it1 -> ShippedUpdate(it1, item[2].toLong(), item[3].toLong()) }
                        }
                        "location" -> {
                            findShipment(item[1])?.let { it1 -> LocationUpdate(it1, item[2].toLong(), item[3]) }
                        }
                        "delivered" -> {
                            findShipment(item[1])?.let { it1 -> DeliveredUpdate(it1, item[2].toLong()) }
                        }
                        "delayed" -> {
                            findShipment(item[1])?.let { it1 -> DelayedUpdate(it1, item[2].toLong(), item[3].toLong()) }
                        }
                        "lost" -> {
                            findShipment(item[1])?.let { it1 -> LostUpdate(it1, item[2].toLong())}
                        }
                        "canceled" -> {
                            findShipment(item[1])?.let { it1 -> CanceledUpdate(it1, item[2].toLong())}
                        }
                        "noteadded" -> {
                            findShipment(item[1])?.let { it1 -> NoteAddedUpdate(it1, item[2].toLong(), item[3])}
                        }

                        else -> {
                            println("Strategy not found")
                        }
                    }
                    if(findShipment(item[1]) == null){
                        println(">  Added")
                    } else {
                        println(">  Found")
                    }


                    delay(1000)
                }
            }

            fun findShipment(id: String): Shipment?{
                return shipments.get(id)
            }

            fun addShipment(shipment: Shipment) {
                shipments.put(shipment.id, shipment)
            }



    }
}