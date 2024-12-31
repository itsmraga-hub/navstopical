package com.example.navs_topical.ui.screens.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navs_topical.ui.screens.verses.VerseSelectionScreen
import com.example.navs_topical.ui.theme.ThemeSelector
import com.example.navs_topical.verses.data.ThemeMode

@Composable
fun MoreScreen(
    onClose: () -> Unit,
    themeState: MutableState<ThemeMode>,
    modifier: Modifier = Modifier) {

    Box (modifier = modifier) {
        Text(text = "More Screen", style = MaterialTheme.typography.headlineMedium)

        ThemeSelector(
            onClose = onClose,
            themeState = themeState, modifier = Modifier.padding(16.dp))
    }
}
