package com.example.navs_topical.ui.screens.verses

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
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun VerseSelectionScreen() {
    var selectedBook by remember { mutableStateOf("") }
    var selectedChapter by remember { mutableStateOf("") }
    var versesInput by remember { mutableStateOf("") }
    var selectedVerses by remember { mutableStateOf(listOf<String>()) }

    val books = listOf("Psalms", "Proverbs", "Genesis") // Example book list
    val chapters = (1..150).map { it.toString() } // Example chapter range

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Dropdown for selecting the book
        DropdownMenuField(
            label = "Select Book",
            items = books,
            selectedItem = selectedBook,
            onItemSelected = { selectedBook = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown for selecting the chapter
        DropdownMenuField(
            label = "Select Chapter",
            items = chapters,
            selectedItem = selectedChapter,
            onItemSelected = { selectedChapter = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TextField for verse(s) input
        OutlinedTextField(
            value = versesInput,
            onValueChange = { versesInput = it },
            label = { Text("Enter Verse(s) (e.g., 1 or 1-5)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to add the verse selection
        Button(
            onClick = {
                if (selectedBook.isNotEmpty() && selectedChapter.isNotEmpty()) {
                    selectedVerses = selectedVerses + "$selectedBook $selectedChapter:$versesInput"
                    versesInput = ""
                }
            },
            enabled = selectedBook.isNotEmpty() && selectedChapter.isNotEmpty() && versesInput.isNotEmpty()
        ) {
            Text("Add Verse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the selected verses
        LazyColumn(
            modifier = Modifier.padding(4.dp)
        ) {
            items(selectedVerses) {
                Text(text = it, modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuField(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedItem,
            onValueChange = { },
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            onItemSelected(item)
                            expanded = false
                        },
                        text = {
                            Text(item)
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
    }
}


@Preview
@Composable
fun VerseSelectionScreenPreview() {
    VerseSelectionScreen()
}