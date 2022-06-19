package net.zerotask.libraries.android.compose.furigana

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun TextWithReading(
    textContent: List<TextData>,
    showReadings: Boolean = false,
) {
    val (text, inlineContent) = remember(textContent) {
        calculateAnnotatedString(textContent = textContent, showReadings = showReadings)
    }

    Text(text = text, inlineContent = inlineContent)
}

fun calculateAnnotatedString(textContent: List<TextData>, showReadings: Boolean):
        Pair<AnnotatedString, Map<String, InlineTextContent>> {
    val inlineContent = mutableMapOf<String, InlineTextContent>()

    return buildAnnotatedString {
        for (elem in textContent) {
            val text = elem.text
            val reading = elem.reading

            // If there is not reading available, simply add the text and move to the next element.
            if (reading == null) {
                append(text)
                continue
            }

            // Words larger than one character/kanji need a small amount of additional space in their
            // x-dimension.
            val width = (text.length.toDouble() + (text.length - 1) * 0.05).em
            appendInlineContent(text, text)
            inlineContent[text] = InlineTextContent(
                // TODO: find out why height and width need magic numbers.
                placeholder = Placeholder(
                    width = width,
                    height = 1.97.em,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Bottom,
                ),
                children = {
                    val readingFontSize = LocalTextStyle.current.fontSize / 2
                    val boxHeight = with(LocalDensity.current) { readingFontSize.toDp() }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom,
                    ) {
                        Box(modifier = Modifier.requiredHeight(boxHeight + 3.dp)) {
                            if (showReadings) {
                                Text(
                                    modifier = Modifier.wrapContentWidth(unbounded = true),
                                    text = reading,
                                    style = TextStyle.Default.copy(fontSize = readingFontSize)
                                )
                            }
                        }
                        Text(text = text)
                    }
                }
            )
        }
    } to inlineContent
}

@Preview
@Composable
internal fun PreviewTextWithReading() {
    val textContent = listOf(
        TextData(text = "このルールを"),
        TextData(text = "守", reading = "まも"),
        TextData(text = "るらない"),
        TextData(text = "人", reading = "ひと"),
        TextData(text = "は"),
        TextData(text = "旅行", reading = "りょこう"),
        TextData(text = "ができなくなることもあります。"),
    )

    MaterialTheme {
        TextWithReading(textContent = textContent, showReadings = true)
    }
}

@Preview
@Composable
internal fun PreviewTextWithoutReading() {
    val textContent = listOf(
        TextData(text = "このルールを"),
        TextData(text = "守", reading = "まも"),
        TextData(text = "るらない"),
        TextData(text = "人", reading = "ひと"),
        TextData(text = "は"),
        TextData(text = "旅行", reading = "りょこう"),
        TextData(text = "ができなくなることもあります。"),
    )

    MaterialTheme {
        TextWithReading(textContent = textContent, showReadings = true)
    }
}
