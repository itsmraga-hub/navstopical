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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
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
fun BibleViewer(bible: BibleData) {

    val chaptersPerBook = bible.verses
        .groupBy { it.book_name }
        .mapValues { (_, verses) ->
            verses.map { it.chapter }.distinct().count()
        }

    val versesPerChapter = bible.verses
        .groupBy { it.book_name } // Group by book name
        .mapValues { (_, versesInBook) ->
            versesInBook.groupBy { it.chapter } // Group by chapter within each book
                .mapValues { (_, versesInChapter) ->
                    versesInChapter.count() // Count the verses in each chapter
                }
        }
    var currentBook by remember { mutableStateOf("Matthew") }
    var currentChapter by remember { mutableIntStateOf(10) }
    var visibleVerses by remember { mutableStateOf<List<BibleVerse>>(emptyList()) }

    val loadVersesForChapter: (String, Int) -> List<BibleVerse> = { bookName, chapter ->
        val previousChapterVerses = bible.verses.filter { it.book_name == bookName && it.chapter == chapter - 1 }
        val currentChapterVerses = bible.verses.filter { it.book_name == bookName && it.chapter == chapter }
        val nextChapterVerses = bible.verses.filter { it.book_name == bookName && it.chapter == chapter + 1 }
        previousChapterVerses + currentChapterVerses + nextChapterVerses
    }

    var state = rememberLazyListState()
    println("state: ${state.firstVisibleItemIndex}")
    // Load initial verses
    LaunchedEffect(currentBook, currentChapter) {
        visibleVerses = loadVersesForChapter(currentBook, currentChapter)
//        state = visibleVerses
        println("visibleVerses $visibleVerses")
        println("currentChapter $currentChapter")
        println("currentBook $currentBook")
    }

//    LaunchedEffect(state) {
//        snapshotFlow { state.firstVisibleItemIndex }
//            .collect { index ->
//                when (index) {
//                    0 -> {
//                        // User scrolled to the top
//                        if (currentChapter > 1) {
//                            currentChapter -= 1
//                        } else {
//                            // Handle book change if needed
//                        }
//                    }
//                    visibleVerses.lastIndex -> {
//                        // User scrolled to the bottom
//                        currentChapter += 1
//                    }
//                }
//
//                visibleVerses = loadVersesForChapter(currentBook, currentChapter)
//            }
//    }

    LaunchedEffect(state) {
        snapshotFlow { state.firstVisibleItemIndex }
            .collect { index ->
                val lastItemIndex = visibleVerses.lastIndex
                val chapterMaxIndex = versesPerChapter[currentBook]?.get(currentChapter) ?: 0

                // Scroll to top: Move to previous chapter if possible
                if (index == 0 && currentChapter > 1) {
                    currentChapter -= 1
                }
                // Scroll to bottom: Move to next chapter if available
                else if (index == lastItemIndex && index == chapterMaxIndex - 1) {
                    // If we've reached the last verse of the chapter, go to the next chapter
                    if (currentChapter < (chaptersPerBook[currentBook] ?: 1)) {
                        currentChapter += 1
                    }
                    // If it's the last chapter of the book, go to the next book
                    else if (currentBook != bible.verses.lastOrNull()?.book_name) {
                        val nextBook = getNextBook(currentBook)
                        currentBook = nextBook
                        currentChapter = 1 // Start from chapter 1 of the next book
                    }
                }
                visibleVerses = loadVersesForChapter(currentBook, currentChapter)
            }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 100.dp, bottom = 100.dp),
        state = state
    ) {
        items(visibleVerses) { verse ->
            Text(text = "${verse.chapter}:${verse.verse} - ${verse.text}")
        }

        // Handle scroll events


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



@Preview
@Composable
fun BibleViewerPreview(){
    val context = LocalContext.current
    val jsonString = readJsonFromAssets(context, "asv.json")
    val asvBible = parseJsonToModel(jsonString)
    BibleViewer(bible = asvBible)
}