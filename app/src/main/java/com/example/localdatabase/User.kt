package com.example.localdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_Table")
data class User(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val email: String,
    val password : String
)
