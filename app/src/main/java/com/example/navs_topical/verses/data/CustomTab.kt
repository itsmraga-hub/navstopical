package com.example.navs_topical.verses.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class CustomTab(
    val text: String,
    val icon: ImageVector,
    val onClick : () -> Unit = {}
)