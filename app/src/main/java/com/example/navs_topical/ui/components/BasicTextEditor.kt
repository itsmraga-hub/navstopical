package com.example.navs_topical.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor


//@Composable
//fun BasicTextEditor(modifier: Modifier = Modifier) {
//    val state = rememberRichTextState()
////    println("BasicTextEditor: $state")
////    println("BasicTextEditor: ${state.toText()}")
////    println("BasicTextEditor: ${state.toMarkdown()}")
//    Box(modifier = modifier) {
//        RichTextEditor(
//            state = state,
//            textStyle = LocalTextStyle.current,
//            modifier = Modifier.fillMaxWidth().fillMaxHeight(1F)
//        )
//    }
//}


@Composable
fun BasicTextEditor(modifier: Modifier = Modifier) {
    val state = rememberRichTextState()

    println("BasicTextEditor: $state")
    println("BasicTextEditor: ${state.toText()}")
    println("BasicTextEditor: ${state.toMarkdown()}")
    Column(modifier = modifier.padding(16.dp)) {
        // Formatting Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Bold Button
            Button(onClick = {
                state.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold))
            }) {
                Text("Bold")
            }

            // Italics Button
            Button(onClick = {
                state.toggleSpanStyle(SpanStyle(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic))
            }) {
                Text("Italics")
            }

            // Header Button
            Button(onClick = {
                state.toggleParagraphStyle(ParagraphStyle(textAlign = TextAlign.Center))
            }) {
                Text("Header")
            }

            // Ordered List Button
            Button(onClick = {
                state.toggleOrderedList()
            }) {
                Text("Ordered List")
            }

            // Unordered List Button
            Button(onClick = {
                state.toggleUnorderedList()
            }) {
                Text("Unordered List")
            }

            // Code Block Button
            Button(onClick = {
                state.toggleCodeSpan()
            }) {
                Text("Code")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Rich Text Editor
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            RichTextEditor(
                state = state,
                textStyle = LocalTextStyle.current,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Export as Markdown and HTML Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                println("Markdown: ${state.toMarkdown()}")
            }) {
                Text("Export Markdown")
            }
            Button(onClick = {
                println("HTML: ${state.toHtml()}")
            }) {
                Text("Export HTML")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BasicTextEditorPreview() {
    Text("Hello, World!")
    BasicTextEditor(modifier = Modifier.fillMaxHeight(1F))
}