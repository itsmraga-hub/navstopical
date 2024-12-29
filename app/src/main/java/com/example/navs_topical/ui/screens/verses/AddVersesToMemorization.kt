package com.example.navs_topical.ui.screens.verses

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVersesToMemorization() {
    // Sample data for books and chapters
    val books = listOf("Psalms", "Proverbs", "John")
    val chapters = listOf(1, 2, 3, 4, 5) // You can dynamically load this based on the selected book

    // State for book, chapter, and verse input
    var selectedBook by remember { mutableStateOf("Psalms") }
    var selectedChapter by remember { mutableStateOf(1) }
    var verseInput by remember { mutableStateOf("") }

    // Dropdown menu for book selection
    var expandedBooks by remember { mutableStateOf(false) }
    var expandedChapters by remember { mutableStateOf(false) }

    // Button to handle verse addition logic
    var versesList by remember { mutableStateOf<List<String>>(emptyList()) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Book Dropdown
        Text("Select Book", style = MaterialTheme.typography.headlineMedium)
        ExposedDropdownMenuBox(
            expanded = expandedBooks,
            onExpandedChange = { expandedBooks = !expandedBooks }
        ) {
            TextField(
                value = selectedBook,
                onValueChange = {},
                readOnly = true,
                label = { Text("Book") },
                trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                modifier = Modifier.fillMaxWidth().clickable { expandedBooks = true }
            )
            ExposedDropdownMenu(
                expanded = expandedBooks,
                onDismissRequest = { expandedBooks = false }
            ) {
                books.forEach { book ->
                    DropdownMenuItem(
                        onClick = {
                            selectedBook = book
                            expandedBooks = false
                        },
                        text = {
                            Text(text = book)
                        },
                        modifier = TODO(),
                        leadingIcon = TODO(),
                        trailingIcon = TODO(),
                        enabled = TODO(),
                        colors = TODO(),
                        contentPadding = TODO()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Chapter Dropdown
        Text("Select Chapter", style = MaterialTheme.typography.headlineLarge)
        ExposedDropdownMenuBox(
            expanded = expandedChapters,
            onExpandedChange = { expandedChapters = !expandedChapters }
        ) {
            TextField(
                value = selectedChapter.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text("Chapter") },
                trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                modifier = Modifier.fillMaxWidth().clickable { expandedChapters = true }
            )
            ExposedDropdownMenu(
                expanded = expandedChapters,
                onDismissRequest = { expandedChapters = false }
            ) {
                chapters.forEach { chapter ->
                    DropdownMenuItem(
                        onClick = {
                            selectedChapter = chapter
                            expandedChapters = false
                        },
                        text = {
                            Text(text = chapter.toString())
                        },
                        modifier = TODO(),
                        leadingIcon = TODO(),
                        trailingIcon = TODO(),
                        enabled = TODO(),
                        colors = TODO(),
                        contentPadding = TODO(),
                        interactionSource = TODO()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Verse Input Field
        Text("Enter Verse(s)", style = MaterialTheme.typography.headlineMedium)
        TextField(
            value = verseInput,
            onValueChange = { verseInput = it },
            label = { Text("Verse(s)") },
            placeholder = { Text("e.g., 1 or 1-5") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add Button
        Button(
            onClick = {
                if (verseInput.isNotEmpty()) {
                    // Process the verse input (range or single verse)
                    val verses = parseVerses(verseInput)
                    versesList = versesList + verses
                    verseInput = "" // Reset the input after adding
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Verses to Memorization")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display added verses
        Text("Added Verses:", style = MaterialTheme.typography.headlineLarge)
        LazyColumn {
            items(versesList) { verse ->
                Text(verse)
            }
        }
    }
}

fun parseVerses(verseInput: String): List<String> {
    val verses = mutableListOf<String>()
    val rangePattern = """(\d+)-(\d+)""".toRegex()

    // Check if the input is a range
    val match = rangePattern.find(verseInput)
    if (match != null) {
        val start = match.groupValues[1].toInt()
        val end = match.groupValues[2].toInt()
        for (i in start..end) {
            verses.add("$i")
        }
    } else {
        // Otherwise, it's a single verse
        verses.add(verseInput)
    }

    return verses
}


@Preview(showBackground = true)
@Composable
fun AddVersesToMemorizationPreview() {
    AddVersesToMemorization()
}