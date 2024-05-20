package com.example.kotlin_corutines.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Insert
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun  deleteTodo(todo: Todo)

    @Query("SELECT * FROM Todo")
    fun getAllTodoItems():List<Todo>
}