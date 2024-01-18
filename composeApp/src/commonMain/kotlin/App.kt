import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var greeting by remember { mutableStateOf("") }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            FormaDePersona { valores ->
                greeting = "Ok ${valores.first} ${valores.second} te llamare al ${valores.third}"
                showContent = true
            }
            AnimatedVisibility(showContent) {
                ComposeMutliplaformSurface(greeting) { showContent = false }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ComposeMutliplaformSurface(
    greeting: String,
    onDismiss: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable { onDismiss() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource("compose-multiplatform.xml"), null)
        Text(greeting)
    }
}