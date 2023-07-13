package com.amtron.innobuzz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amtron.innobuzz.model.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPosts(posts : List<Post>)

    @Query("SELECT * FROM post")
    fun getPosts() : List<Post>
}