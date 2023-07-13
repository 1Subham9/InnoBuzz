package com.amtron.innobuzz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amtron.innobuzz.model.Post

@Database(entities = [Post::class], version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao


    companion object {

        @Volatile
        private var INSTANCE: PostDatabase? = null


        fun getDataBase(context: Context): PostDatabase {

            if (INSTANCE == null) {
                synchronized(this) {

                    INSTANCE = Room.databaseBuilder(
                        context, PostDatabase::class.java, "postDb"
                    ).build()

                }
            }

            return INSTANCE!!
        }


    }
}