import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.Instant


@Composable
fun Message(message: String, removeMessage: () -> Unit) {
    Row {
        Surface(elevation = 1.dp) {
            Row(modifier = Modifier.padding(16.dp)) {
                Text(message)
                Button(removeMessage) {
                    Text("Remove Message")
                }
            }
        }
    }
}

@Composable
fun ShipmentView(viewHelper: TrackerViewHelper, removeMessage: () -> Unit) {
    Row(Modifier.fillMaxWidth()) {
        Surface(elevation = 1.dp) {
            Row(Modifier.padding(8.dp).fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text("Tracking Shipment: ${viewHelper.shipmentId}")
                    Text("Status: ${viewHelper.shipmentStatus}")
                    Text("Type: ${viewHelper.shipmentType}")
                    Text("Location: ${viewHelper.shipmentLocation}")

                    Text("Expected Delivery: ${
                        Instant.ofEpochMilli(viewHelper.expectedShipmentDeliveryDate)}".dropLast(2))
                    Text("Shipment Updates: ")
                    for (item in viewHelper.shipmentUpdateHistory) {
                        Text(item)
                    }
                    Text("Notes: ")
                    for (item in viewHelper.shipmentNotes) {
                        Text(item)
                    }

                }

                Column {
                    Button(removeMessage, modifier = Modifier.align(Alignment.End)) {
                        Text("X")
                    }
                }
            }
        }
    }
}

@Composable
fun App() {

    rememberCoroutineScope().launch { TrackingServer.runServer() }
    MaterialTheme {
        var searchVal by remember { mutableStateOf("") }
        val viewHelpers = remember { mutableStateListOf<TrackerViewHelper>() }
        Column (Modifier.fillMaxWidth()){

            Row (Modifier.fillMaxWidth()){
                TextField(searchVal, onValueChange = {searchVal = it}, singleLine = true, modifier = Modifier.weight(1f))
                Button({
                    TrackingServer.findShipment(searchVal)?.let { it1 -> viewHelpers.add(TrackerViewHelper(it1)) }
                    if(TrackingServer.findShipment(searchVal) == null){
                        searchVal = "Invalid. Try again."
                    }
                }) {
                    Text("Search")
                }
            }
            Row (Modifier.fillMaxWidth()){
                LazyColumn(reverseLayout = true) {
                    items(viewHelpers, key = {
                        it
                    }) { viewHelper ->
                        ShipmentView(viewHelper) { viewHelpers.remove(viewHelper) }
                    }
                }
            }
        }

    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()

    }
}

