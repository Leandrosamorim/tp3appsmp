package com.example.myapplication.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Avaliacao (

    @ColumnInfo(name = "idAvaliador") var idAvaliador: String,
    @ColumnInfo(name = "empresa") var empresa: CriptoString?,
    @ColumnInfo(name="bairro") var bairro: CriptoString?,
    @ColumnInfo(name="respostas") var respostas : String?,
    @PrimaryKey(autoGenerate = true)
var id : Int? = null
)
