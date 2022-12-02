package com.example.final2101257.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface MyDao {
    @Query("select * from MyTodo order by time desc")
    fun selectAll():List<MyTodo>

    @Insert(onConflict = IGNORE)
    suspend fun insert(record:MyTodo)
}