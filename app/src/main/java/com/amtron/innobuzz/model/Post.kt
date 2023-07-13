package com.amtron.innobuzz.model

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "post")
data class Post(


    @ColumnInfo(name = "body") val body: String,
    @PrimaryKey(autoGenerate = false)
@ColumnInfo(name = "that") val that: String,
@ColumnInfo(name = "title") val title: String,
@ColumnInfo(name = "userId") val userId: Int
)