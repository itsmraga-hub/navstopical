package com.example.navs_topical.ui.screens.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navs_topical.ui.components.BasicTextEditor
import com.example.navs_topical.ui.screens.verses.VerseSelectionScreen

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {

    Box (modifier = modifier) {
//        Text(text = "Settings Screen", style = MaterialTheme.typography.headlineMedium)

//        VerseSelectionScreen()
        BasicTextEditor(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.75F).padding(16.dp))
    }
}
