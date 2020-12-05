package com.example.myapplication.Viewmodels.Users.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_login_conta.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_conta, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        btn_efetuar_cadastro.setOnClickListener {
            var email = editTextTextEmailLogin.text.toString()
            var senha = editTextTextSenhaLogin.text.toString()

            viewModel.loginUser(email, senha)
                .addOnSuccessListener {
                    findNavController().navigate(R.id.action_loginFragment_to_menu)
                }
                .addOnFailureListener {
                    Toast.makeText(
                        requireContext(),
                        "Não foi",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
        btn__login_cadastrar.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment)
        }
    }
}