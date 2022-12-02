package com.example.diary.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface MyDao {
    @Query("select * from Diary")
    fun selectAll():List<Diary>

    @Insert(onConflict=IGNORE)
    suspend fun insert(record: Diary)
}