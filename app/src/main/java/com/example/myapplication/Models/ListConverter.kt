package com.example.myapplication.Models

import androidx.room.TypeConverter

private const val SEPARATOR = ","
class ListConverter{



    @TypeConverter
    fun listToString(respostas: List<String>?): String? {
        return respostas?.map { it }?.joinToString(separator = SEPARATOR)
    }

    @TypeConverter
    fun stringToList(respostas: String?): List<String>? {
        return respostas?.split(SEPARATOR)!!
    }

}