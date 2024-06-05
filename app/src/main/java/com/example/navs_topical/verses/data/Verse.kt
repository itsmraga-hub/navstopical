package com.example.navs_topical.verses.data

data class Verse(
    val verseTopic: String,
    val verseReference: String,
    val verseContent: String,
    val verseVersion: String,
    val verseTag: String,
)
val verse = Verse("His Spirit",
    "1 Corinthians 3:16",
    "Don't you know that you yourselves are God's temple and that God's Spirit lives in you?",
    "NIV",
    "C-1: Rely on God's Resources")
val verses = listOf<Verse>(
    Verse("His Spirit",
        "1 Corinthians 3:16",
        "Don't you know that you yourselves are God's temple and that God's Spirit lives in you?",
        "NIV",
        "C-1: Rely on God's Resources"),
    Verse("His Spirit",
        "1 Corinthians 3:16",
        "Don't you know that you yourselves are God's temple and that God's Spirit lives in you?",
        "NIV",
        "C-1: Rely on God's Resources"),
    Verse("His Spirit",
        "1 Corinthians 3:16",
        "Don't you know that you yourselves are God's temple and that God's Spirit lives in you?",
        "NIV",
        "C-1: Rely on God's Resources"),
    Verse("His Spirit",
        "1 Corinthians 3:16",
        "Don't you know that you yourselves are God's temple and that God's Spirit lives in you?",
        "NIV",
        "C-1: Rely on God's Resources"),
    Verse("His Spirit",
        "1 Corinthians 3:16",
        "Don't you know that you yourselves are God's temple and that God's Spirit lives in you?",
        "NIV",
        "C-1: Rely on God's Resources")
)
