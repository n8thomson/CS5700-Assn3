import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import java.io.File

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                val file = File("webpage.html")
                val contents = file.readText()
                call.respondText(contents, ContentType.Text.Html)
              call.respondText("Hello, world!", ContentType.Text.Html)
            }
        }
    }.start(wait = true)
}