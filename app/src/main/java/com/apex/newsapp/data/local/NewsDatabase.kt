package com.apex.newsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.apex.newsapp.data.model.NewsArticle

@Database(
    entities = [NewsArticle::class],
    version = 1 ,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

    companion object {
        @Volatile
        private var instance: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        //Build a local database to store data
        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, NewsDatabase::class.java, "news_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}