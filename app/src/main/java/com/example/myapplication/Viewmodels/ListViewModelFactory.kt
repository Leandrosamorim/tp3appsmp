package com.example.myapplication.Viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Viewmodels.Avaliacoes.list.AvFbListViewModel
import com.example.myapplication.Viewmodels.Avaliacoes.list.AvListViewModel

class ListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AvListViewModel::class.java)){
            return AvListViewModel() as T
        }
        if (modelClass.isAssignableFrom(AvFbListViewModel::class.java)){
            return AvFbListViewModel() as T
        }
        else
            throw IllegalArgumentException(
                "ListViewModelFactory socilita um ListViewModel"
            )
    }
}