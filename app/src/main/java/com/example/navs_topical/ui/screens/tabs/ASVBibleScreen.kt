package com.example.navs_topical.ui.screens.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun ASVBibleScreen(
    bible: BibleData,
    modifier: Modifier = Modifier
) {

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
    var selectedTabIndex by remember { mutableIntStateOf(0) } // Track the selected tab

    val bibleMap = organizeBooksByTestament(bible.verses);
    val testamentMap = groupBooksByTestament(bible.verses);

    var book by remember { mutableStateOf("Genesis") }
    var chaptersVisible by remember { mutableStateOf(false) }
    var bookVisible by remember { mutableStateOf(false) }
    var wordVisible by remember { mutableStateOf(false) }

    var selectedBookChapters by remember { mutableStateOf(HashMap<String, String>()) }
    var selectedBookVerses by remember { mutableStateOf(listOf<BibleVerse>()) }

    val setBook: (String) -> Unit = { bk ->
        book = bk
        selectedBookVerses = bible.verses.filter { verse ->
            verse.book_name == bk
        }


        println("$chaptersPerBook")
        println("$versesPerChapter")

        println("$selectedBookVerses")
    }

    val tabs = listOf("Old Testament", "New Testament");
    Box(modifier = modifier) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) MaterialTheme.colorScheme.primary else Color.Gray,
                            fontStyle = FontStyle.Normal,
                            fontSize = TextUnit.Unspecified
                        )
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> {
                TestamentScreen(
                    testament = "Old Testament",
                    book = book,
                    books = testamentMap["Old Testament"]!!,
                    chaptersVisible = chaptersVisible,
                    bookVisible = bookVisible,
                    setBook = setBook,
                    modifier = Modifier
                        .padding(top = 128.dp)
                        .border(
                            border = BorderStroke(8.dp, color = Color.Green),
                            shape = RoundedCornerShape(24.dp)
                        ),
                    wordVisible = wordVisible
                )
            }

            1 -> {
                TestamentScreen(
                    testament = "New Testament",
                    book = book,
                    setBook = setBook,
                    chaptersVisible = chaptersVisible,
                    bookVisible = bookVisible,
                    wordVisible = wordVisible,
                    books = testamentMap["New Testament"]!!,
                    modifier = Modifier
                        .padding(top = 128.dp)
                )
            }
        }
    }
}
// Compare this snippet from app/src/main/java/com/example/navs_topical/ui/screens/tabs/SettingsScreen.kt:


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TestamentScreen(
    testament: String,
    book: String,
    chaptersVisible: Boolean,
    bookVisible: Boolean,
    wordVisible: Boolean,
    books: List<String>,
    setBook: (String) -> Unit,
    modifier: Modifier = Modifier
) {
        LazyColumn (
            modifier = Modifier.padding(top = 48.dp)
        ) {
            items(books) {
                Button(
                    onClick = { setBook(it) },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = Modifier.padding(0.dp)

                ) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .padding(0.dp)
                        // modifier = Modifier.alignBy(Alignment.Center)
                    )
                }

                FlowRow(
                    modifier = Modifier
//                        .padding(8.dp)
//                        .width(300.dp)
//                        .height(400.dp)
                        .wrapContentWidth(Alignment.Start)
                        .wrapContentSize()

                ) {
                    for (item in 1..20) {
                        Button(
                            onClick = {  },
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            modifier = Modifier.padding(0.dp),

                        ) {
                            Text(
                                text = "$item",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier
                                    .padding(0.dp),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
//}


fun setBibleBook(book: String) {

}

@Preview(showBackground = true)
@Composable
fun ASVBibleScreenPreview() {
    val context = LocalContext.current
    val jsonString = readJsonFromAssets(context, "asv.json")
    val asvBible = parseJsonToModel(jsonString)
    ASVBibleScreen(bible = asvBible)
}