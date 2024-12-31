package com.example.navs_topical.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navs_topical.verses.data.ThemeMode

@Composable
fun ThemeSelector(
    onClose: () -> Unit,
    themeState: MutableState<ThemeMode>,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onClose,
        title = { Text(text = "Change Theme") },
        text = {
            Column {
                RadioButtonRow(
                    label = "Light Theme",
                    isSelected = themeState.value == ThemeMode.LIGHT,
                    onSelect = { themeState.value = ThemeMode.LIGHT }
                )
                RadioButtonRow(
                    label = "Dark Theme",
                    isSelected = themeState.value == ThemeMode.DARK,
                    onSelect = { themeState.value = ThemeMode.DARK }
                )
                RadioButtonRow(
                    label = "System Default",
                    isSelected = themeState.value == ThemeMode.SYSTEM,
                    onSelect = { themeState.value = ThemeMode.SYSTEM }
                )
            }
        },
        confirmButton = {
            Button(onClick = onClose) {
                Text("Close")
            }
        }
    )
}

@Composable
fun RadioButtonRow(label: String, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().clickable(onClick = onSelect).padding(8.dp)
    ) {
        RadioButton(selected = isSelected, onClick = onSelect)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label)
    }
}