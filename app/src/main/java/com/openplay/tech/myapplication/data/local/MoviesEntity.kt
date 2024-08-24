package com.openplay.tech.myapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
class MoviesEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val age: Int,
    val town: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String,
    val email: String,
    val phoneNumber: String,
    val bloodGroup : String,
)
