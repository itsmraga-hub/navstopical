package com.example.navs_topical.ui.screens.tabs

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.navs_topical.verses.data.parseJsonToModel
import com.example.navs_topical.verses.data.readJsonFromAssets


@Composable
fun MyVersesScreen(modifier: Modifier = Modifier) {
    Text(text = "MyVerses Screen", style = MaterialTheme.typography.headlineMedium)

    val context = LocalContext.current
    val jsonString = readJsonFromAssets(context, "asv.json")
    val asvBible = parseJsonToModel(jsonString)
//    BibleViewer(bible = asvBible, bookName = "Matthew", chapter = 1)
}