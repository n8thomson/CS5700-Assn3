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
    Row {
        Surface(elevation = 1.dp) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text("Tracking Shipment: ")
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

    rememberCoroutineScope().launch { TrackingSimulator.runSimulation() }
    MaterialTheme {
        var searchVal by remember { mutableStateOf("") }
        val viewHelpers = remember { mutableStateListOf<TrackerViewHelper>() }
        Column {

            Row {
                TextField(searchVal, onValueChange = {searchVal = it})
                Button({
                    TrackingSimulator.findShipment(searchVal)?.let { it1 -> viewHelpers.add(TrackerViewHelper(it1)) }
                }) {
                    Text("Search")
                }
            }
            Row {
                LazyColumn {
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

