package com.amtron.innobuzz.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "post")
data class Post(
    val body: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val userId: Int
)