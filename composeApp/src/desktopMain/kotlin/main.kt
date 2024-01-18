import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val state = rememberWindowState(WindowPlacement.Floating, size = DpSize(1920.dp, 1080.dp))
    Window(onCloseRequest = ::exitApplication, title = "AlexComposeDemo", state = state) {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}