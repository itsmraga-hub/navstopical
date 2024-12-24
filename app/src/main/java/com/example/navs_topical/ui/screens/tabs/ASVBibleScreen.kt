package com.example.navs_topical.ui.screens.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.navs_topical.verses.data.BibleData


@Composable
fun ASVBibleScreen(
    bible: BibleData,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Text(
            text = "ASV Bible Screen",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Center)
        )
        // Text(text = "Bible Screen", style = MaterialTheme.typography.headlineMedium)
    }
}
// Compare this snippet from app/src/main/java/com/example/navs_topical/ui/screens/tabs/SettingsScreen.kt: