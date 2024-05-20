package com.example.kotlin_corutines.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
//by using @Entity, table - Todo and the columns are id and item
data class Todo(var item:String?){
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}


