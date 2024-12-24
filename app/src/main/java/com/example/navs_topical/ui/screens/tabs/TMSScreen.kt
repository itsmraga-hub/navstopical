package com.example.navs_topical.ui.screens.tabs

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navs_topical.verses.VerseList
import com.example.navs_topical.verses.data.Verse


@Composable
fun TMSScreen(modifier: Modifier = Modifier) {
    Text(text = "TMS Screen", style = MaterialTheme.typography.headlineMedium)
    val dummyVerses = List(5) {
        Verse(
            verseTopic = "Topic $it",
            verseVersion = "Version $it",
            verseReference = "Reference $it",
            verseContent = "This is the content of verse $it",
            verseTag = "Tag $it"
        )
    }
    VerseList(
        verses = dummyVerses,
        modifier = Modifier.padding(16.dp)
    )
}
