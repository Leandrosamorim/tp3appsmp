package com.example.myapplication.Viewmodels.Avaliacoes.create

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.myapplication.Models.*
import com.example.myapplication.R
import com.example.myapplication.db.AppDb
import com.example.myapplication.db.RoomDatabase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_create_avaliacao.*


class CreateAvaliacao : Fragment() {

    private lateinit var viewModel: CreateAvaliacaoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CreateAvaliacaoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_create_avaliacao, container, false)
        return view


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmarMorador.setOnClickListener {
            var id = FirebaseAuth.getInstance().currentUser?.uid
            var empresa = nomeEmpresa.text.toString()
            var bairro = edtTextBairro.text.toString()
            var r1: RadioButton = view.findViewById(rdio1.checkedRadioButtonId);
            var r2: RadioButton = view.findViewById(radioGrp2.checkedRadioButtonId)
            var r3: RadioButton = view.findViewById(radioGrp3.checkedRadioButtonId)
            var r4: RadioButton = view.findViewById(rdioGrp4.checkedRadioButtonId)
            var r5: RadioButton = view.findViewById(rdioGrp5.checkedRadioButtonId)
            var resposta1 = r1.text.toString()
            var resposta2 = r2.text.toString()
            var resposta3 = r3.text.toString()
            var resposta4 = r4.text.toString()
            var resposta5 = r5.text.toString()

            var db : RoomDatabase = AppDb.getInstance(requireContext().applicationContext)

            var criptoEmpresa: CriptoString = CriptoConverter().toCriptoString(empresa)!!
            var criptoBairro: CriptoString = CriptoConverter().toCriptoString(bairro)!!

            var respostas : List<String> = listOf(resposta1,resposta2,resposta3,resposta4,resposta5)

            var avaliacao = Avaliacao(
                id!!, criptoEmpresa, criptoBairro, ListConverter().listToString(respostas)
            )

            if (id != null) {
                viewModel.store(
                    db.avaliacaoDAO(),
                    avaliacao
                )
            }
            var media : Double = 0.0

            for (resposta in respostas)
                if(resposta.equals("Sim")) {
                    media = media + 1
                }

            media = media / 5
                var avaliacaoFb = AvaliacaoFB(bairro,media)
                viewModel.storeFb(avaliacaoFb)
                    .addOnSuccessListener {
                        Toast.makeText(
                            requireContext(),
                            "Avaliação adicionada",
                            Toast.LENGTH_LONG
                        ).show()
                        findNavController().popBackStack()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            requireContext(),
                            "Falhou",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
        }
    }
