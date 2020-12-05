package com.example.myapplication.Viewmodels.Avaliacoes.create

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplication.DAO.AvaliacaoDAO
import com.example.myapplication.Models.Avaliacao
import com.example.myapplication.Models.AvaliacaoFB
import com.example.myapplication.Models.CriptoConverter
import com.example.myapplication.Models.CriptoString
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Transaction

class CreateAvaliacaoViewModel : ViewModel() {
    fun store(
            avaliacaoDAO: AvaliacaoDAO,
            avaliacao: Avaliacao
    ) {
        avaliacaoDAO.store(avaliacao)
    }

    fun storeFb(avaliacaoFB: AvaliacaoFB) : Task<Transaction> {

        var db = FirebaseFirestore.getInstance()
        val sfDocRef = db.collection("avaliacoes").document(avaliacaoFB.bairro!!)

        val task = db.runTransaction { transaction ->
                val snapshot = transaction.get(sfDocRef)
                val obj = snapshot.toObject(AvaliacaoFB::class.java)
                if (obj != null) {
                    val newCount = obj!!.count?.plus(1)
                    val newAvg = (obj.avg!! + avaliacaoFB.avg!!) / newCount!!
                    transaction.update(sfDocRef, "avg", newAvg)
                    transaction.update(sfDocRef, "count", newCount)
                }else{
                    transaction.set(sfDocRef, avaliacaoFB)
                }


            }
                return task
    }
}







           /* if(snapshot.data.isNullOrEmpty())
            {
                db.collection("avaliacoes").document(avaliacaoFB.bairro!!).set(avaliacaoFB)

            }
            else
            {
                var newCount : Double
                if (snapshot.getDouble("avaliacoes")!! != null){
                    newCount = snapshot.getDouble("avaliacoes")!! + 1.0
                }else
                {
                    newCount = 1.0
                }
                var newAvg : Double
                if (snapshot.getDouble("avg")!! != null){
                            newAvg = (snapshot.getDouble("avg")!! + avaliacaoFB.avg!!)/ newCount
                        }else
                        {
                            newAvg = avaliacaoFB.avg!!
                        }
                transaction.update(sfDocRef, "avg", newAvg)
            }*/

            // Note: this could be done without a transaction
            //       by updating the population using FieldValue.increment()


