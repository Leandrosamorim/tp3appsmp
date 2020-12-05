package com.example.myapplication.Viewmodels.Avaliacoes.list

import androidx.lifecycle.ViewModel
import com.example.myapplication.Models.Avaliacao
import com.example.myapplication.db.RoomDatabase

class AvListViewModel : ViewModel() {
    fun all(db: RoomDatabase): List<Avaliacao> {
        return db.avaliacaoDAO().all().toList()
    }
}