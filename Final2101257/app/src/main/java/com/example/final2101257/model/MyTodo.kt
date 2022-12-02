package com.example.final2101257.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyTodo(
    @PrimaryKey(autoGenerate = true) val rid:Int,
    val time:String,
    val content:String
)
