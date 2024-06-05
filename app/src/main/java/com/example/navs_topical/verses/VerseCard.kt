package com.example.navs_topical.verses

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navs_topical.verses.data.Verse
import com.example.navs_topical.verses.data.verse

@Composable
fun VerseCard(verse: Verse, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)),
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            // .border(BorderStroke(0.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)))
            .padding(8.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .align(alignment = Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(text = verse.verseTopic)
                Text(text = verse.verseVersion)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(vertical = 8.dp, horizontal = 4.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = verse.verseReference, fontWeight = MaterialTheme.typography.labelLarge.fontWeight)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = verse.verseContent)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(text = verse.verseReference,
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                )
            }
            Text(text = verse.verseTag)
        }
    }
}

//@Composable
//@Preview
//fun VerseCardPreview() {
//    val verse = verse
//    VerseCard(verse = verse)
//}

@Composable
@Preview
fun VerseListPreview() {
    val verse = verse
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(4.dp)
        .background(color = MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(content = {
            items(5) {
                VerseCard(verse = verse)
            }
        }, modifier = Modifier
            .fillMaxWidth(0.9F),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        )
    }
}