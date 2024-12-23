package com.example.navs_topical.verses.data
import android.content.Context
import kotlinx.serialization.Serializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Serializable
data class Metadata(
    val name: String,
    val shortname: String,
    val module: String,
    val year: String,
    val publisher: String? = null,
    val owner: String? = null,
    val description: String,
    val lang: String,
    val lang_short: String,
    val copyright: Int,
    val copyright_statement: String,
    val url: String? = null,
    val citation_limit: Int,
    val restrict: Int,
    val italics: Int,
    val strongs: Int,
    val red_letter: Int,
    val paragraph: Int,
    val official: Int,
    val research: Int,
    val module_version: String
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
    val metadata: Metadata,
    val verses: List<BibleVerse>
)






fun readJsonFromAssets(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}

fun parseJsonToModel(jsonString: String): BibleData {
    val gson = Gson()
    return gson.fromJson(jsonString, object : TypeToken<BibleData>() {}.type)
}