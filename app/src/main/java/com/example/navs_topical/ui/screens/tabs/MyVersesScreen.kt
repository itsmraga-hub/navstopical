package com.example.navs_topical.ui.screens.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.navs_topical.ui.components.SaveDataToExternalStorage
import com.example.navs_topical.ui.components.readVerses
import com.example.navs_topical.ui.screens.verses.AddVersesToMemorization
import com.example.navs_topical.verses.MyMemorizedVersesList
import com.example.navs_topical.verses.data.BibleVerse
import com.example.navs_topical.verses.data.parseJsonToModel
import com.example.navs_topical.verses.data.readJsonFromAssets


@Composable
fun MyVersesScreen(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val message = ""
//    val jsonString = readJsonFromAssets(context, "asv.json")
//    val asvBible = parseJsonToModel(jsonString)
//    BibleViewer(bible = asvBible, bookName = "Matthew", chapter = 1)

    var verses by remember { mutableStateOf(emptyList<BibleVerse>()) }

    LaunchedEffect(Unit) {
        verses = readVerses(context, "tms.json")
        println("verses $verses")
    }

    Box(modifier = modifier) {
//        Text(text = "MyVerses Screen", style = MaterialTheme.typography.headlineMedium)
//        AddVersesToMemorization()
        MyMemorizedVersesList(verses)
//        SaveDataToExternalStorage(context = context, msg = message)
    }
}