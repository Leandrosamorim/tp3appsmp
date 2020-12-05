package com.example.myapplication.DAO

import androidx.room.*
import com.example.myapplication.Models.Avaliacao

@Dao
interface AvaliacaoDAO {

        @Insert
        fun store(avaliacao: Avaliacao)

        @Update
        fun update(avaliacao: Avaliacao)

        @Delete
        fun delete(avaliacao: Avaliacao)

        @Query("SELECT * FROM Avaliacao")
        fun all(): List<Avaliacao>
    }