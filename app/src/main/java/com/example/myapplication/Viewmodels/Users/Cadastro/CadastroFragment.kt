package com.example.myapplication.Viewmodels.Users.Cadastro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_cadastro.*
import kotlinx.android.synthetic.main.fragment_login_conta.btn_efetuar_cadastro

class CadastroFragment : Fragment() {

    private lateinit var viewModel: CadastroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CadastroViewModel::class.java)
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_efetuar_cadastro.setOnClickListener {
            var email = editTextTextEmailCadastro.text.toString()
            var senha = editTextTextSenhaCadastro.text.toString()
            var reSenha = editTextTextSenhaConfirmar.text.toString()

            if (senha.compareTo(reSenha) == 0) {

                var task = viewModel.createUser(email, senha)
                task
                    .addOnSuccessListener {
                        viewModel.storeUserInfo(
                            it.user!!.uid,
                            email
                        )
                            .addOnSuccessListener {
                                Toast.makeText(
                                    requireContext(),
                                    "$email cadastrado com sucesso.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    requireContext(),
                                    "Deu erro",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            .addOnCompleteListener {
                                FirebaseAuth.getInstance().signOut()
                                findNavController().popBackStack()
                            }
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            requireContext(),
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }else {
                Toast.makeText(
                    requireContext(),
                    "Senhas não conferem!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        btn_voltar_login.setOnClickListener {
            findNavController().navigate(R.id.action_cadastroFragment_to_loginFragment2)
        }
    }
}