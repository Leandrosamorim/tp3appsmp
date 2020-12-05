package com.example.myapplication.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object AppDb {
    private var db: com.example.myapplication.db.RoomDatabase? = null
    fun getInstance(context: Context): com.example.myapplication.db.RoomDatabase {
        if (db == null) {
            db = Room
                .databaseBuilder(
                    context,
                    com.example.myapplication.db.RoomDatabase::class.java,
                    "dbApp"
                )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        return db as com.example.myapplication.db.RoomDatabase
    }
}