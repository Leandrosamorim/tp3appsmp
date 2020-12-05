package com.example.myapplication.Viewmodels.Avaliacoes.list

import androidx.lifecycle.ViewModel
import com.example.myapplication.Models.Avaliacao
import com.example.myapplication.Models.AvaliacaoFB
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class AvFbListViewModel : ViewModel() {
    fun allFb(): CollectionReference {
        var db = FirebaseFirestore.getInstance()
            .collection("avaliacoes")
        return db
    }
}