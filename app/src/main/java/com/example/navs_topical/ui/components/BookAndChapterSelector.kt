package com.example.navs_topical.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navs_topical.verses.data.oldTestamentBooks


@Composable
fun BookAndChapterSelector(books: Map<String, Int> = oldTestamentBooks) {
    // Books and chapter counts
//    val books = oldTestamentBooks

    // States for book selection
    var selectedBook by remember { mutableStateOf(books.keys.first()) }
    var expandedBookDropdown by remember { mutableStateOf(false) }

    // States for chapter selection
    val chapters = books[selectedBook] ?: 1
    var selectedChapter by remember { mutableIntStateOf(1) }
    var expandedChapterDropdown by remember { mutableStateOf(false) }

    println("selectedBook $selectedBook")
    println("selectedChapter $selectedChapter")
    Column(modifier = Modifier.padding(16.dp)) {
        // Book Dropdown
        Text(text = "Select a Book", style = MaterialTheme.typography.titleMedium)
        Box(modifier = Modifier.fillMaxWidth()) {
            TextButton(
                onClick = { expandedBookDropdown = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selectedBook)
            }
            DropdownMenu(
                expanded = expandedBookDropdown,
                onDismissRequest = { expandedBookDropdown = false }
            ) {
                books.keys.forEach { book ->
                    DropdownMenuItem(
                        onClick = {
                            selectedBook = book
                            selectedChapter = 1 // Reset chapter when book changes
                            expandedBookDropdown = false
                        },
                        text = { Text(text = book) },
//                        modifier = TODO(),
//                        leadingIcon = TODO(),
//                        trailingIcon = TODO(),
//                        enabled = TODO(),
//                        colors = TODO(),
//                        contentPadding = TODO()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Chapter Dropdown
        Text(text = "Select a Chapter", style = MaterialTheme.typography.titleMedium)
        Box(modifier = Modifier.fillMaxWidth()) {
            TextButton(
                onClick = { expandedChapterDropdown = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Chapter $selectedChapter")
            }
            DropdownMenu(
                expanded = expandedChapterDropdown,
                onDismissRequest = { expandedChapterDropdown = false }
            ) {
                (1..chapters).forEach { chapter ->
                    DropdownMenuItem(
                        onClick = {
                            selectedChapter = chapter
                            expandedChapterDropdown = false
                        },
                        text = { Text(text = "$chapter") },
//                        modifier = TODO(),
//                        leadingIcon = TODO(),
//                        trailingIcon = TODO(),
//                        enabled = TODO(),
//                        colors = TODO(),
//                        contentPadding = TODO(),
//                        interactionSource = TODO()
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun BookAndChapterSelectorPreview() {
    BookAndChapterSelector()
}