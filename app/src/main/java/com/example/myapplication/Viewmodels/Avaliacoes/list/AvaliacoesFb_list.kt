package com.example.myapplication.Viewmodels.Avaliacoes.list

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapters.AvAdapter
import com.example.myapplication.Adapters.AvFbAdapter
import com.example.myapplication.Models.AvaliacaoFB
import com.example.myapplication.R
import com.example.myapplication.Viewmodels.ListViewModelFactory
import com.example.myapplication.db.AppDb
import com.example.myapplication.db.RoomDatabase
import kotlinx.android.synthetic.main.fragment_avaliacoes_list.*


class avaliacoesFb_list : Fragment() {

    private lateinit var listViewModel : AvFbListViewModel
    private lateinit var listViewModelFactory : ListViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        listViewModelFactory = ListViewModelFactory()

        listViewModel =
            ViewModelProvider(this, listViewModelFactory)
                .get(AvFbListViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_avaliacoesfb_list, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel
            .allFb()
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                } else if (querySnapshot != null) {
                    var avaliacoes = querySnapshot.toObjects(AvaliacaoFB::class.java)
                    if (!avaliacoes.isNullOrEmpty() && listViewModel != null) {
                        listViewAvFb.adapter = AvFbAdapter(avaliacoes)
                        listViewAvFb.layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            }

        back.setOnClickListener {
            findNavController().navigate(R.id.action_avaliacoesFb_list_to_menu)
        }
    }


}