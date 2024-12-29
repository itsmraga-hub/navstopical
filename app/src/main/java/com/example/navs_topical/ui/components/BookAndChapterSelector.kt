package com.example.navs_topical.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import com.example.navs_topical.verses.data.BibleData
import com.example.navs_topical.verses.data.BibleVerse
import com.example.navs_topical.verses.data.oldTestamentBooks


@Composable
fun BookAndChapterSelector(books: Map<String, Int> = oldTestamentBooks, bible: BibleData) {
    // Books and chapter counts
//    val books = oldTestamentBooks

    // States for book selection
    var selectedBook by remember { mutableStateOf(books.keys.first()) }
//    var selectedVerses by remember { mutableStateOf() }
    var expandedBookDropdown by remember { mutableStateOf(false) }
    var selectedBibleVerses by remember { mutableStateOf<List<BibleVerse>>(emptyList()) }

    // States for chapter selection
    val chapters = books[selectedBook] ?: 1
    var selectedChapter by remember { mutableIntStateOf(1) }
    var expandedChapterDropdown by remember { mutableStateOf(false) }

    var verseInput by remember { mutableStateOf("") }
    var versesList by remember { mutableStateOf<List<BibleVerse>>(emptyList()) }


//    selectedBibleVerses = bible.verses.filter { verse ->
//        verse.book_name == selectedBook
//    }

//    println("selectedBibleVerses $selectedBibleVerses")
//    selectedBibleVerses = bible.verses.filter { verse ->
//        verse.chapter == selectedChapter
//    }

    val setBibleChapter: (i: Int) -> Unit = { i ->
        println("selectedBibleVersesChapter0: $selectedBibleVerses")
        println("i $i")
        selectedBibleVerses = selectedBibleVerses.filter { bk ->
            bk.chapter == i
        }
        println("selectedBibleVersesChapter: $selectedBibleVerses")
    }

    val setBibleBook: (bk: String) -> Unit = { bk ->
        selectedBibleVerses = bible.verses.filter { book ->
            book.book_name == bk
        }
        println("selectedBibleVersesChapter: $selectedBibleVerses")
    }

//    println("selectedBook $selectedBook")
//    println("selectedChapter $selectedChapter")
//
//    println("selectedBibleVerses $selectedBibleVerses")

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
                            setBibleBook(book)
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
                            setBibleChapter(chapter)
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

        Box(modifier = Modifier.fillMaxWidth()) {
            Text("Enter Verse(s)", style = MaterialTheme.typography.headlineMedium)
            TextField(
                value = verseInput,
                onValueChange = {
                    verseInput = it
                    println("verseInput $verseInput") },
                label = { Text("Verse(s)") },
                placeholder = { Text("e.g., 1 or 1-5") },
                modifier = Modifier.fillMaxWidth()
            )
        }


        Button(
            onClick = {
                if (verseInput.isNotEmpty()) {
                    // Process the verse input (range or single verse)
                    val verses = parseVerses(verseInput, selectedBibleVerses)
                    println("verses: $verses")

                    versesList = versesList + verses
                    println("versesList: $versesList")
                    verseInput = "" // Reset the input after adding
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Verses to Memorization")
        }

        Text("Added Verses:", style = MaterialTheme.typography.headlineLarge)
        LazyColumn {
            items(versesList) { verse ->
                Text(verse.chapter.toString())
                Text(verse.verse.toString())
                Text(verse.text)
            }
        }
    }
}


fun parseVerses(verseInput: String, selectedBibleVerses: List<BibleVerse>): List<BibleVerse> {
    val verses = mutableListOf<BibleVerse>()
    val rangePattern = """(\d+)-(\d+)""".toRegex()

    // Check if the input is a range
    val match = rangePattern.find(verseInput)
    println("match $match")
    if (match != null) {
        val start = match.groupValues[1].toInt()
        val end = match.groupValues[2].toInt()
        println("match $match")
        println("start $start")
        println("end $end")
        for (i in start..end) {
//            verses.add("$i")
            verses.add(selectedBibleVerses[i - 1])
        }
    } else {
        // Otherwise, it's a single verse
        verses.add(selectedBibleVerses[0])
    }

    return verses
}

@Preview
@Composable
fun BookAndChapterSelectorPreview() {
//    BookAndChapterSelector()
}