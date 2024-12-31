package com.example.navs_topical.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.navs_topical.verses.data.CustomTab


@Composable
fun CustomizedBottomAppBar(tabs: List<CustomTab>, selectedTab: Int, selectTab: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Render left tabs
        tabs.subList(0, 2).forEachIndexed { index, tab ->
            TextButton(
                onClick = { selectTab(index) },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (selectedTab == index) MaterialTheme.colorScheme.primary else Color.Gray
                )
            ) {
                Text(
                    text = tab.text,
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
                .clickable { selectTab(2) }
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
                onClick = {
                    selectTab(index + 3)
                          },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (selectedTab == index + 3) MaterialTheme.colorScheme.primary else Color.Gray
                ),

            ) {
                Text(
                    text = tab.text,
                    textAlign = TextAlign.Center,
                    color = if (selectedTab == index + 3) MaterialTheme.colorScheme.primary else Color.Gray,
                )
                Icon(
                    tab.icon,
                    contentDescription = "settings icon",
                )

            }

        }

    }
}