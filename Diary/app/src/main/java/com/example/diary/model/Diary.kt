package com.example.diary.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diary(
    @PrimaryKey val time:String,
    val title: String,
    val content:String
)
