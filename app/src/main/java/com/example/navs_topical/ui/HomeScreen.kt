package com.example.navs_topical.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.navs_topical.ui.components.BottomSheetComponent
import com.example.navs_topical.verses.VerseList
import com.example.navs_topical.verses.data.Verse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var newVerse: Verse = Verse(
        verseTopic = "",
        verseReference = "",
        verseContent = "",
        verseVersion = "",
        verseTag = ""
    )
    fun toggleBottomSheet() {
        showBottomSheet = false
    }
    // VerseList()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Hi William," , textAlign = TextAlign.Left)
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    text = "Bottom app bar",
//                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/showBottomSheet = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        if (showBottomSheet) {
            BottomSheetComponent(
                sheetState = sheetState,
                scope = scope,
                dismissModal = { toggleBottomSheet() },
                verse = newVerse,
                modifier = Modifier.padding(innerPadding))
        }
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
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}