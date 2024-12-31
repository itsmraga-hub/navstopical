package com.example.navs_topical.ui.screens.tabs

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.navs_topical.verses.TMSVerseList
import com.example.navs_topical.verses.data.Verse
import com.example.navs_topical.verses.data.parseJsonToModelTMS
import com.example.navs_topical.verses.data.readJsonFromAssets


@Composable
fun TMSScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val tms = readJsonFromAssets(context, "tms.json")
    val parsedTMS = parseJsonToModelTMS(tms)
    println("TMS: $tms")
    println("TMS: $parsedTMS")
    Text(text = "TMS Screen", style = MaterialTheme.typography.headlineMedium)
    TMSVerseList(
        verses = parsedTMS.c,
        modifier = Modifier.padding(16.dp)
    )
}
