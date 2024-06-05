package com.example.navs_topical

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navs_topical.ui.theme.NavstopicalTheme

@Composable
fun HomeViewPage() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(text = "Home View Page")
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPagePreview() {
    NavstopicalTheme {
        HomeViewPage()
    }
}