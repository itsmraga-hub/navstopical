package com.example.navs_topical.ui.screens.tabs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navs_topical.verses.data.BibleData
import com.example.navs_topical.verses.data.parseJsonToModel
import com.example.navs_topical.verses.data.readJsonFromAssets



@Composable
fun BibleViewer(bible: BibleData, bookName: String, chapter: Int, modifier: Modifier = Modifier) {
    println("chapter11 $chapter")
    println("bookName11 $bookName")
    println("bible11 $bible")
    val verses = remember { bible.verses }
    val currentIndex by remember { mutableIntStateOf(verses.indexOfFirst { it.book_name == bookName && it.chapter == chapter }) }
    val state = rememberLazyListState(initialFirstVisibleItemIndex = currentIndex)

    val displayedBooks = remember { mutableSetOf<String>() }

    println("displayedBooks $displayedBooks")
    println("chapter $chapter")
    println("bookName $bookName")
    println("verses $verses")
    LaunchedEffect(state) {
        snapshotFlow { state.firstVisibleItemIndex }
            .collect { index ->
                if (index in verses.indices) {
                    val verse = verses[index]

                    displayedBooks.add(verse.book_name)
                }
            }
    }

    LazyColumn(
        modifier = modifier,
        state = state
    ) {
        items(verses) { verse ->
            if (displayedBooks.contains(verse.book_name) &&
                verses.indexOf(verse) == verses.indexOfFirst { it.book_name == verse.book_name }
            ) {
                Text(
                    text = verse.book_name,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
            }

            SelectionContainer {
                Text(text = "${verse.chapter}:${verse.verse} - ${verse.text}")
            }
        }
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