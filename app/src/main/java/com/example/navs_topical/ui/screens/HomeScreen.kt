package com.example.navs_topical.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navs_topical.ui.components.BottomSheetComponent
import com.example.navs_topical.ui.screens.tabs.ASVBibleScreen
import com.example.navs_topical.ui.screens.tabs.MyVersesScreen
import com.example.navs_topical.ui.screens.tabs.SettingsScreen
import com.example.navs_topical.ui.screens.tabs.TMSScreen
import com.example.navs_topical.verses.VerseList
import com.example.navs_topical.verses.data.Verse
import com.example.navs_topical.verses.data.parseJsonToModel
import com.example.navs_topical.verses.data.readJsonFromAssets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val jsonString = readJsonFromAssets(context, "asv.json")
    val asvBible = parseJsonToModel(jsonString)
    var selectedTab by remember { mutableIntStateOf(0) }

    // println(asvBible?.metadata.name);
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val newVerse: Verse = Verse(
        verseTopic = "",
        verseReference = "",
        verseContent = "",
        verseVersion = "",
        verseTag = ""
    )
    fun toggleBottomSheet() {
        showBottomSheet = false
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    // VerseList()
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Hi William," , textAlign = TextAlign.Left)
                },
                scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
//                cutoutShape = MaterialTheme.shapes.small,
                tonalElevation = 8.dp

            ) {
                val tabs = listOf("My Verses", "TMS", "ASV", "Settings", "More")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Render left tabs
                    tabs.subList(0, 2).forEachIndexed { index, tab ->
                        TextButton(
                            onClick = { selectedTab = index },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = if (selectedTab == index) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        ) {
                            Text(
                                text = tab,
                                textAlign = TextAlign.Center,
                                color = if (selectedTab == index) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        }
                    }

                    // Centered "My Verses" tab
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .border(
                                width = 2.dp,
                                color = if (selectedTab == 2) MaterialTheme.colorScheme.primary else Color.Gray,
                                shape = RoundedCornerShape(50)
                            )
                            .clickable { selectedTab = 2 }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "ASV",
                            color = if (selectedTab == 2) MaterialTheme.colorScheme.primary else Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }

                    // Render right tabs
                    tabs.subList(3, tabs.size).forEachIndexed { index, tab ->
                        TextButton(
                            onClick = { selectedTab = index + 3 },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = if (selectedTab == index + 3) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        ) {
                            Text(
                                text = tab,
                                textAlign = TextAlign.Center,
                                color = if (selectedTab == index + 3) MaterialTheme.colorScheme.primary else Color.Gray
                            )
                        }

                    }
                }
            }



        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/showBottomSheet = true },
                        elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                pressedElevation = 8.dp
            )
            ){
                Icon(Icons.Default.Add, contentDescription = "Add")
            }

        }
    ) { innerPadding ->
        if (showBottomSheet) {
            BottomSheetComponent(
                sheetState = sheetState,
                scope = scope,
                dismissModal = { toggleBottomSheet() },
                verse = newVerse,
                bible = asvBible,
                modifier = Modifier.padding(innerPadding))
        }
        when (selectedTab) {
            0 -> {
                MyVersesScreen(modifier = Modifier.padding(top = 64.dp, start = 8.dp, end = 8.dp))
            }
            1 -> {
                TMSScreen(modifier = Modifier.padding(top = 64.dp, start = 8.dp, end = 8.dp))
            }
            2 -> {
                ASVBibleScreen(bible = asvBible, modifier = Modifier.padding(top = 64.dp, start = 8.dp, end = 8.dp))
            }
            3 -> {
                SettingsScreen(modifier = Modifier.padding(top = 64.dp, start = 8.dp, end = 8.dp))
            }
            4 -> {
                Text("More")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}