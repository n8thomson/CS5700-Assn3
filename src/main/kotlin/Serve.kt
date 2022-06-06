import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import java.io.File

class Global{
    companion object {var shipments = mutableMapOf<String, Shipment>()}

}

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {


                var payload = ""
                call.parameters["say"]?.let { it1 -> payload = it1 }

                println(">  " + payload)
                val item = payload.split(",")
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



                val file = File("webpage.html")
                val contents = file.readText()
                call.respondText(contents, ContentType.Text.Html)

            }
        }
    }.start(wait = true)
}


fun findShipment(id: String): Shipment?{
    return Global.shipments.get(id)
}

fun addShipment(shipment: Shipment) {
    Global.shipments.put(shipment.id, shipment)
}