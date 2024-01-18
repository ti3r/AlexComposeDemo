import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

data class ValoresDeFormaState(
    val nombre: MutableState<String> = mutableStateOf(""),
    val apellido: MutableState<String> = mutableStateOf(""),
    val telefono: MutableState<String> = mutableStateOf("")
) {
    fun toTriple(): Triple<String, String, String> = Triple(nombre.value, apellido.value, telefono.value)
    fun clear() {
        nombre.value = ""
        apellido.value = ""
        telefono.value = ""
    }
}


@Composable
fun FormaDePersona(
    onSubmit: (Triple<String, String, String>) -> Unit = {}
){
    var valores = remember{ ValoresDeFormaState() }
    Column(
        modifier = getFormModifier()
        , verticalArrangement = Arrangement.spacedBy(10.dp)
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextoDeForma(etiqueta = "Nombre", valor = valores.nombre.value) {
            valores.nombre.value = it
        }
        TextoDeForma("Apellido", valor = valores.apellido.value) {
            valores.apellido.value = it
        }
        TextoDeForma(
            "Telefono",
            valor = valores.telefono.value,
            transformacion = TelefonoVisualTransformation()
        ) {
            valores.telefono.value = it
        }
        Button(onClick = {
            onSubmit(valores.toTriple())
            //Clear the state once done
            valores.clear()
        }) {
            Text("Enviar")
        }
    }
}

@Composable
fun getFormModifier(): Modifier {
    val platform = getPlatform()
    return when (platform.platform) {
        SupportedPlatform.ANDROID -> Modifier.padding(10.dp).fillMaxWidth()
        else -> Modifier.padding(25.dp).wrapContentWidth(Alignment.CenterHorizontally, true)
    }
}

@Composable
fun TextoDeForma(
    etiqueta: String,
    valor: String = "",
    transformacion: VisualTransformation? = null,
    onChange: (valor: String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = valor,
        onValueChange = onChange,
        label = { Text(etiqueta) },
        visualTransformation = transformacion ?: VisualTransformation.None,
    )
}

class TelefonoVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return when {
            (text.length == 10 ) -> {
                TransformedText(AnnotatedString("${text.subSequence(0,3)}-${text.substring(3,6)}-${text.substring(6,10)}"), telefonoOffsetMapping)
            }
            else -> {
                TransformedText(text, OffsetMapping.Identity)
            }
//            else -> {
//                TransformedText(AnnotatedString(text.substring(0,10)), OffsetMapping.Identity)
//            }
        }
    }

}


val telefonoOffsetMapping = object : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        if (offset <= 2) return offset
        if (offset <= 5) return offset + 1
        if (offset <= 9) return offset + 2
        return 9
    }

    override fun transformedToOriginal(offset: Int): Int {
        if (offset <= 2) return offset
        if (offset <= 6) return offset - 1
        if (offset <= 12) return offset - 2
        return 12
    }
}