package com.example.navs_topical.ui.screens.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navs_topical.ui.screens.verses.VerseSelectionScreen

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {

    Box (modifier = modifier) {
        Text(text = "Settings Screen", style = MaterialTheme.typography.headlineMedium)

        VerseSelectionScreen()
    }
}
