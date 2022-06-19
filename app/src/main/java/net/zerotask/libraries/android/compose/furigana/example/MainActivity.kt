package net.zerotask.libraries.android.compose.furigana.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.zerotask.libraries.android.compose.furigana.TextData
import net.zerotask.libraries.android.compose.furigana.TextWithReading

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    Content()
                }
            }
        }
    }
}

@Composable
internal fun Content() {
    val textContent = listOf(
        TextData(text = "このルールを"),
        TextData(text = "守", reading = "まも"),
        TextData(text = "るらない"),
        TextData(text = "人", reading = "ひと"),
        TextData(text = "は"),
        TextData(text = "旅行", reading = "りょこう"),
        TextData(text = "ができなくなることもあります。"),
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(24.dp),
        ) {
            TextWithReading(textContent = textContent)
            TextWithReading(textContent = textContent, showReadings = true)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Content()
    }
}