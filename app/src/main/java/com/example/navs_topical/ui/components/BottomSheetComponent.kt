package com.example.navs_topical.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navs_topical.verses.data.BibleData
import com.example.navs_topical.verses.data.Verse
import com.example.navs_topical.verses.data.newTestamentBooks
import com.example.navs_topical.verses.data.oldTestamentBooks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetComponent(
    sheetState: SheetState,
    scope: CoroutineScope,
    dismissModal: () -> Unit,
    verse: Verse,
    bible: BibleData,
    modifier: Modifier =Modifier) {

    var newTestamentBooks = newTestamentBooks
    var oldTestamentBooks = oldTestamentBooks

    ModalBottomSheet(onDismissRequest = { /*TODO*/
            dismissModal()
        },
        sheetState = sheetState,
        modifier = Modifier.fillMaxHeight(1F)
    ) {


        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Button(onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        dismissModal()
                    }
                }
            }) {
                Text("Close")
            }
            BookAndChapterSelector(bible = bible, books = oldTestamentBooks + newTestamentBooks)
//            BookAndChapterSelector(bible = bible, books = oldTestamentBooks)
//            TextField(value = verse.verseTopic, onValueChange = {})
//            TextField(value = verse.verseReference, onValueChange = {})
//            TextField(value = verse.verseContent, onValueChange = {})
//            TextField(value = verse.verseVersion, onValueChange = {})
//            TextField(value = verse.verseTag, onValueChange = {})
        }
    }
}