package com.example.navs_topical.ui.screens.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.navs_topical.verses.data.BibleData
import com.example.navs_topical.verses.data.BibleVerse
import com.example.navs_topical.verses.data.groupBooksByTestament
import com.example.navs_topical.verses.data.organizeBooksByTestament
import com.example.navs_topical.verses.data.parseJsonToModel
import com.example.navs_topical.verses.data.readJsonFromAssets

@Composable
fun BibleViewer(bible: BibleData, bookName: String, chapter: Int) {
    val visibleVerses = remember { bible.verses } // Full list of verses
    val currentIndex by remember { mutableIntStateOf(visibleVerses.indexOfFirst { it.book_name == bookName && it.chapter == chapter }) }
    val state = rememberLazyListState(initialFirstVisibleItemIndex = currentIndex)

    // Track the displayed book headers
//    val displayedBooks = remember { mutableStateSetOf<String>() }
    val displayedBooks = remember { mutableSetOf<String>() }
    // Handle scroll events to detect entering a new book
    LaunchedEffect(state) {
        snapshotFlow { state.firstVisibleItemIndex }
            .collect { index ->
                if (index in visibleVerses.indices) {
                    val verse = visibleVerses[index]

                    // Add book to displayedBooks set to display its header
                    displayedBooks.add(verse.book_name)
                }
            }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp),
        state = state
    ) {
        // Iterate through all verses and conditionally add headers
        items(visibleVerses) { verse ->
            if (displayedBooks.contains(verse.book_name) &&
                visibleVerses.indexOf(verse) == visibleVerses.indexOfFirst { it.book_name == verse.book_name }
            ) {
                // Display book name header at the first verse of the book
                Text(
                    text = verse.book_name,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Display the verse
            Text(text = "${verse.chapter}:${verse.verse} - ${verse.text}")
        }
    }
}



fun getNextBook(currentBook: String): String {
    val books = listOf(
        "Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy",
        "Joshua", "Judges", "Ruth", "1 Samuel", "2 Samuel",
        "1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles",
        "Ezra", "Nehemiah", "Esther", "Job", "Psalms",
        "Proverbs", "Ecclesiastes", "Song of Solomon", "Isaiah",
        "Jeremiah", "Lamentations", "Ezekiel", "Daniel", "Hosea",
        "Joel", "Amos", "Obadiah", "Jonah", "Micah", "Nahum",
        "Habakkuk", "Zephaniah", "Haggai", "Zechariah", "Malachi",
        "Matthew", "Mark", "Luke", "John", "Acts", "Romans",
        "1 Corinthians", "2 Corinthians", "Galatians", "Ephesians",
        "Philippians", "Colossians", "1 Thessalonians", "2 Thessalonians",
        "1 Timothy", "2 Timothy", "Titus", "Philemon", "Hebrews",
        "James", "1 Peter", "2 Peter", "1 John", "2 John", "3 John",
        "Jude", "Revelation"
    )

    val currentBookIndex = books.indexOf(currentBook)
    return if (currentBookIndex != -1 && currentBookIndex < books.size - 1) {
        books[currentBookIndex + 1]
    } else {
        books.first() // If it's the last book, loop back to the first book
    }
}


fun getPreviousBook(currentBook: String): String? {
    val books = listOf(
        "Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy",
        "Joshua", "Judges", "Ruth", "1 Samuel", "2 Samuel",
        "1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles",
        "Ezra", "Nehemiah", "Esther", "Job", "Psalms",
        "Proverbs", "Ecclesiastes", "Song of Solomon", "Isaiah",
        "Jeremiah", "Lamentations", "Ezekiel", "Daniel", "Hosea",
        "Joel", "Amos", "Obadiah", "Jonah", "Micah", "Nahum",
        "Habakkuk", "Zephaniah", "Haggai", "Zechariah", "Malachi",
        "Matthew", "Mark", "Luke", "John", "Acts", "Romans",
        "1 Corinthians", "2 Corinthians", "Galatians", "Ephesians",
        "Philippians", "Colossians", "1 Thessalonians", "2 Thessalonians",
        "1 Timothy", "2 Timothy", "Titus", "Philemon", "Hebrews",
        "James", "1 Peter", "2 Peter", "1 John", "2 John", "3 John",
        "Jude", "Revelation"
    )
    val currentBookIndex = books.indexOf(currentBook)
    return if (currentBookIndex > 0) {
        books[currentBookIndex - 1]
    } else {
        null // No previous book if we're at the first book
    }
}


@Preview
@Composable
fun BibleViewerPreview(){
    val context = LocalContext.current
    val jsonString = readJsonFromAssets(context, "asv.json")
    val asvBible = parseJsonToModel(jsonString)
    BibleViewer(bible = asvBible, bookName = "Genesis", chapter = 1)
}