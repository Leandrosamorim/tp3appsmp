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
import com.example.myapplication.R
import com.example.myapplication.Viewmodels.ListViewModelFactory
import com.example.myapplication.db.AppDb
import kotlinx.android.synthetic.main.fragment_avaliacoes_list.*


class avaliacoes_list : Fragment() {

    private lateinit var listViewModel : AvListViewModel
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
                .get(AvListViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_avaliacoes_list, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recycleListView = listViewAvFb
        recycleListView.adapter = AvAdapter(listViewModel.all(AppDb.getInstance(requireContext())))
        recycleListView.layoutManager = LinearLayoutManager(requireContext())

        back.setOnClickListener {
            findNavController().navigate(R.id.action_avaliacoes_list_to_menu)
        }
    }


}