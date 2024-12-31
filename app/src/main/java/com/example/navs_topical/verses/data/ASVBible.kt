package com.example.navs_topical.verses.data
import android.content.Context
import kotlinx.serialization.Serializable
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken


@Serializable
data class Metadata(
    val name: String? = null,
    val shortname: String? = null,
    val module: String? = null,
    val year: String? = null,
    val publisher: String? = null,
    val owner: String? = null,
    val description: String? = null,
    val lang: String? = null,
    val lang_short: String? = null,
    val copyright: Int? = null,
    val copyright_statement: String? = null,
    val url: String? = null,
    val citation_limit: Int? = null,
    val restrict: Int? = null,
    val italics: Int? = null,
    val strongs: Int? = null,
    val red_letter: Int? = null,
    val paragraph: Int? = null,
    val official: Int? = null,
    val research: Int? = null,
    val module_version: String? = null,
)

@Serializable
data class BibleVerse(
    val book_name: String,
    val book: Int,
    val chapter: Int,
    val verse: Int,
    val text: String
)

@Serializable
data class BibleData(
    val metadata: Metadata? = null,
    val verses: List<BibleVerse>
)


data class TMS(
    val a: List<TMSVerse> = emptyList(),
    val b: List<TMSVerse> = emptyList(),
    val c: List<TMSVerse> = emptyList()
)

data class TMSVerse(
    val tag: String,
    val topic: String,
    val version: String,
    val book_name: String,
    val chapter: Int,
    val verse: Int,
    val content: String,
    val background: String,
    val category: String
)



fun readJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}

fun parseJsonToModel(jsonString: String): BibleData {
    val gson = Gson()
    return gson.fromJson(jsonString, object : TypeToken<BibleData>() {}.type)
}

fun parseJsonToModelTMS(json: String): TMS {
    val gson = Gson()
    val jsonObject = gson.fromJson(json, JsonObject::class.java)

    // Extract lists dynamically
    val aList = jsonObject["A"]?.let { gson.fromJson(it, Array<TMSVerse>::class.java).toList() } ?: emptyList()
    val bList = jsonObject["B"]?.let { gson.fromJson(it, Array<TMSVerse>::class.java).toList() } ?: emptyList()
    val cList = jsonObject["C"]?.let { gson.fromJson(it, Array<TMSVerse>::class.java).toList() } ?: emptyList()

    return TMS(a = aList, b = bList, c = cList)
//    return gson.fromJson(jsonString, object : TypeToken<TMS>() {}.type)
}

fun organizeBooksByTestament(verses: List<BibleVerse>): Map<String, String> {
    // Define Old Testament and New Testament book ranges
    val oldTestamentBooks = 1..39
    val newTestamentBooks = 40..66

    // Create a map to store the result
    val booksByTestament = mutableMapOf<String, String>()

    // Traverse the verses list
    verses.forEach { verse ->
        if (!booksByTestament.containsKey(verse.book_name)) {
            // Determine testament based on book number
            val testament = when (verse.book) {
                in oldTestamentBooks -> "Old Testament"
                in newTestamentBooks -> "New Testament"
                else -> "Unknown"
            }
            booksByTestament[verse.book_name] = testament
        }
    }

    return booksByTestament
}

fun groupBooksByTestament(verses: List<BibleVerse>): Map<String, List<String>> {
    // Define Old Testament and New Testament book ranges
    val oldTestamentBooks = 1..39
    val newTestamentBooks = 40..66

    // Use a mutable map to store the results
    val booksByTestament = mutableMapOf(
        "Old Testament" to mutableSetOf<String>(),
        "New Testament" to mutableSetOf<String>()
    )

    // Traverse the verses list
    verses.forEach { verse ->
        when (verse.book) {
            in oldTestamentBooks -> booksByTestament["Old Testament"]?.add(verse.book_name)
            in newTestamentBooks -> booksByTestament["New Testament"]?.add(verse.book_name)
        }
    }

    // Convert the sets to lists for the final output
    return booksByTestament.mapValues { it.value.toList() }
}