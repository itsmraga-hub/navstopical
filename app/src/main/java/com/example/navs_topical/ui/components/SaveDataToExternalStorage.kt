package com.example.navs_topical.ui.components

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navs_topical.verses.data.BibleVerse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID

@Composable
fun SaveDataToExternalStorage(context: Context, msg: String) {
    // on below line creating a variable for message.
    val message = remember {
        mutableStateOf("")
    }
    val txtMsg = remember {
        mutableStateOf("")
    }
    var verses by remember{ mutableStateOf<List<BibleVerse>>(emptyList()) }
    val activity = context as Activity

    // on below line we are creating a column,
    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        // on below line we are adding a text for heading.
        Text(
            // on below line we are specifying text
            text = "Internal Storage in Android",
            // on below line we are specifying text color,
            // font size and font weight
            color = Color.Green,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(10.dp))
        // on below line we are creating a text field
        // for our message number.
        TextField(
            // on below line we are specifying value
            // for our message text field.
            value = message.value,
            // on below line we are adding on value
            // change for text field.
            onValueChange = { message.value = it },
            // on below line we are adding place holder as
            // text as "Enter your email"
            placeholder = { Text(text = "Enter your message") },
            // on below line we are adding modifier to it
            // and adding padding to it and filling max width
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            // on below line we are adding text style
            // specifying color and font size to it.
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            // on below line we ar adding single line to it.
            singleLine = true,
        )

        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(10.dp))


        // on below line adding a button.
        Button(
            onClick = {
                // on below line we are saving data to internal storage
                saveVerse(context, BibleVerse(book_name = "Matthew", book = 1, chapter = 1, verse = 1, text = "This is the content of verse 1"))
//                message.value = ""
                Toast.makeText(context, "Data saved successfully..", Toast.LENGTH_SHORT).show()

            },
            // on below line adding a modifier for our button.
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            // on below line adding a text for our button.
            Text(text = "Write Data to Internal Storage", textAlign = TextAlign.Center)
        }
        // on below line adding a spacer
        Spacer(modifier = Modifier.width(10.dp))

        // on below line adding a text view to display our data.
        Text(
            // on below line we are specifying text
            text = "Data will appear below : \n" + txtMsg.value,
            // on below line we are specifying text color,
            // font size and font weight
            color = Color.Green,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        // adding spacer on below line.
        Spacer(modifier = Modifier.height(20.dp))
        // on below line creating a button
        Button(
            onClick = {
                // on below line we are reading the
                // data which we stored in our file.
                verses = readVerses(context, "tms.json")
                println("verses $verses")
            },
            // on below line adding a
            // modifier for our button.
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            // on below line adding a text for our button.
            Text(text = "Read Data from Internal Storage", textAlign = TextAlign.Center)
        }
    }
}



fun saveVerse(context: Context, bibleVerse: BibleVerse) {
    try {
        val fileName = "tms.json"
        val gson = Gson()
        val file = File(context.filesDir, fileName)
        val type = object : TypeToken<MutableList<Map<String, Any>>>() {}.type

        // Step 1: Load existing data if the file exists
        val existingData: MutableList<Map<String, Any>> = if (file.exists()) {
            val fis = FileInputStream(file)
            val jsonString = fis.bufferedReader().use { it.readText() }
            gson.fromJson(jsonString, type) ?: mutableListOf()
        } else {
            mutableListOf()
        }

        // Step 2: Create a new object with a unique key
        val newEntry = mapOf(
            "id" to UUID.randomUUID().toString(), // Generate a unique key
            "bibleVerse" to bibleVerse
        )
        println("newEntry $newEntry")
        // Step 3: Add the new object to the existing list
        existingData.add(newEntry)

        // Step 4: Save the updated list back to the file
        val jsonString = gson.toJson(existingData)
        val fos: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        fos.write(jsonString.toByteArray())
        fos.flush()
        fos.close()

        // Clear the message value
//        message = ""

        // Show a success toast message
        Toast.makeText(context, "Verse saved successfully.", Toast.LENGTH_SHORT).show()

    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error saving data.", Toast.LENGTH_SHORT).show()
    }
}


fun readVerses(context: Context, fileName: String): List<BibleVerse> {
    try {
//        val fileName = "tms.json"
        val gson = Gson()
        val file = File(context.filesDir, fileName)
        val type = object : TypeToken<MutableList<Map<String, Any>>>() {}.type

        // Step 1: Load existing data if the file exists
        val existingData: MutableList<Map<String, Any>> = if (file.exists()) {
            val fis = FileInputStream(file)
            val jsonString = fis.bufferedReader().use { it.readText() }
            gson.fromJson(jsonString, type) ?: mutableListOf()
        } else {
            mutableListOf()
        }

        // Step 2: Extract BibleVerse objects from the data
        val bibleVerses = mutableListOf<BibleVerse>()
        for (entry in existingData) {
            val bibleVerseMap = entry["bibleVerse"] as? Map<*, *>
            if (bibleVerseMap != null) {
                // Convert the map to a BibleVerse object
                val bibleVerse = gson.fromJson(gson.toJson(bibleVerseMap), BibleVerse::class.java)
                bibleVerses.add(bibleVerse)
            }
        }
//        verses = bibleVerses
        return bibleVerses
    } catch (e: IOException) {
        e.printStackTrace()
        Toast.makeText(context, "Error reading data.", Toast.LENGTH_SHORT).show()
        return emptyList()
    }
}
