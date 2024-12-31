package com.example.navs_topical.verses

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.navs_topical.verses.data.BibleVerse
import com.example.navs_topical.verses.data.TMSVerse
import com.example.navs_topical.verses.data.Verse
import com.example.navs_topical.verses.data.verse
import kotlin.math.absoluteValue

@Composable
fun TMSVerseCard(verse: TMSVerse, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(0.dp, 64.dp)
            .fillMaxHeight(0.3F)
//            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)),
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            // .border(BorderStroke(0.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)))
            .padding(4.dp, 32.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .align(alignment = Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(text = verse.topic)
                Text(text = verse.version)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(vertical = 8.dp, horizontal = 4.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = "${verse.book_name} ${verse.chapter}: ${verse.verse}",
                    fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = verse.content)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(text = "${verse.book_name} ${verse.chapter}: ${verse.verse}",
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                )
            }
            Text(text = "${verse.tag}: ${verse.category}")
        }
    }
}

//@Composable
//fun VerseList(modifier: Modifier = Modifier) {
//    val verse = verse
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .padding(4.dp)
//        .background(color = MaterialTheme.colorScheme.secondary),
//        contentAlignment = Alignment.Center
//    ) {
//        LazyColumn( modifier = Modifier
//            .fillMaxWidth(0.9F),
//            verticalArrangement = Arrangement.spacedBy(4.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            items(5) {
//                VerseCard(verse = verse)
//            }
//        }
//    }
//}
//@Composable
//@Preview
//fun VerseCardPreview() {
//    val verse = verse
//    VerseCard(verse = verse)
//}

//@OptIn(ExperimentalPagerApi::class)
@Composable
fun TMSVerseList(verses: List<TMSVerse>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState {verses.size}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(4.dp),
        // contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            beyondViewportPageCount = verses.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp),
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            // val scale = if (pagerState.currentPage == page) 1.0f else 0.8f
            val isCurrentPage = pagerState.currentPage == page
            val zIndex = if (isCurrentPage) 1f else 0f

            val distanceFromCurrent = (page - pagerState.currentPage).absoluteValue

            // Dynamic scale: closer pages have higher scale, farther pages have lower scale
            val scale = 1.0f - (0.2f * distanceFromCurrent.coerceAtMost(4))

            val translationX = when {
                page < pagerState.currentPage -> 300.dp  // Positive offset for left elements
                page > pagerState.currentPage -> (-300).dp  // Negative offset for right elements
                else -> 0.dp  // No offset for the focused element
            }
            println("Current page: ${pagerState.currentPage}")
            println("isCurrentPage: $isCurrentPage")
            println("page: $page")
            TMSVerseCard(
                verse = verses[page],
                modifier = Modifier
                    .scale(scale)
                    .zIndex(zIndex)
                    .padding(0.dp, 12.dp)
                    .absoluteOffset(x = translationX)
                    // .height(240.dp)
                    .fillMaxWidth(0.9f) // Adjust the visible width
            )
        }
    }
}


@Composable
fun MemorizedVerseCard(verse: BibleVerse, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(0.dp, 64.dp)
            .fillMaxHeight(0.3F)
//            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)),
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            // .border(BorderStroke(0.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)))
            .padding(4.dp, 32.dp)
        ) {
//            Row(modifier = Modifier
//                .fillMaxWidth()
//                .padding(4.dp)
//                .align(alignment = Alignment.CenterHorizontally),
//                horizontalArrangement = Arrangement.SpaceBetween
//
//            ) {
//                Text(text = verse.topic)
//                Text(text = verse.version)
//            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(vertical = 8.dp, horizontal = 4.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = "${verse.book_name} ${verse.chapter}: ${verse.verse}",
                    fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = verse.text)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(text = "${verse.book_name} ${verse.chapter}: ${verse.verse}",
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                )
            }
//            Text(text = "${verse.tag}: ${verse.category}")
        }
    }
}



@Composable
fun MyMemorizedVersesList(verses: List<BibleVerse>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState {verses.size}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(4.dp),
        // contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            beyondViewportPageCount = verses.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 32.dp),
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            // val scale = if (pagerState.currentPage == page) 1.0f else 0.8f
            val isCurrentPage = pagerState.currentPage == page
            val zIndex = if (isCurrentPage) 1f else 0f

            val distanceFromCurrent = (page - pagerState.currentPage).absoluteValue

            // Dynamic scale: closer pages have higher scale, farther pages have lower scale
            val scale = 1.0f - (0.2f * distanceFromCurrent.coerceAtMost(4))

            val translationX = when {
                page < pagerState.currentPage -> 300.dp  // Positive offset for left elements
                page > pagerState.currentPage -> (-300).dp  // Negative offset for right elements
                else -> 0.dp  // No offset for the focused element
            }
            println("Current page: ${pagerState.currentPage}")
            println("isCurrentPage: $isCurrentPage")
            println("page: $page")
            MemorizedVerseCard(
                verse = verses[page],
                modifier = Modifier
                    .scale(scale)
                    .zIndex(zIndex)
                    .padding(0.dp, 12.dp)
                    .absoluteOffset(x = translationX)
                    // .height(240.dp)
                    .fillMaxWidth(0.9f) // Adjust the visible width
            )
        }
    }
}

//@Composable
//@Preview
//fun VerseListPreview() {
//    VerseList()
//}

//@Composable
//@Preview
//fun PreviewVerseList() {
//    val dummyVerses = List(5) {
//        Verse(
//            verseTopic = "Topic $it",
//            verseVersion = "Version $it",
//            verseReference = "Reference $it",
//            verseContent = "This is the content of verse $it",
//            verseTag = "Tag $it"
//        )
//    }
//    VerseList(verses = dummyVerses)
//}