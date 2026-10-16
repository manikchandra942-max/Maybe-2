package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_strategies")
data class StrategyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val songTitle: String,
    val artistName: String,
    val mood: String,
    val genre: String,
    val language: String,
    val lyricsSnippet: String,
    val titlesJson: String,
    val fullDescription: String,
    val tagsJson: String,
    val tagsCommaSeparated: String,
    val thumbnailConceptsJson: String,
    val socialTeasersJson: String,
    val createdAt: Long = System.currentTimeMillis()
)
