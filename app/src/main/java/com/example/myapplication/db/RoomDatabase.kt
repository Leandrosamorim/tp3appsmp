package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.DAO.AvaliacaoDAO
import com.example.myapplication.Models.Avaliacao
import com.example.myapplication.Models.CriptoConverter
import com.example.myapplication.Models.ListConverter

@Database(
    entities = arrayOf(
        Avaliacao::class
    ),
    version = 2
)
@TypeConverters(CriptoConverter::class, ListConverter::class)
abstract class RoomDatabase : RoomDatabase() {
    abstract  fun avaliacaoDAO(): AvaliacaoDAO
}