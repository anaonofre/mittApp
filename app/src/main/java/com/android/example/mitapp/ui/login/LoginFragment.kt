package com.android.example.mitapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.example.mitapp.R
import com.android.example.mitapp.databinding.FragmentLoginBinding
import com.android.example.mitapp.ui.home.HomeActivity
import com.android.example.mitapp.util.validatePWD
import com.android.example.mitapp.util.validateText

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel : LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.txtInputUser.validateText()

        binding.txtInputPwd.validatePWD()

        binding.btnLogin.setOnClickListener {

            val validUser = viewModel.validText(binding.txtInputUser.text.toString())
            val validPwd = viewModel.validText(binding.txtInputPwd.text.toString())

            if(validPwd && validUser){

                startActivity(Intent(context, HomeActivity::class.java))
            }else{
                if (!validPwd){
                    binding.txtInputPwd.setError("La constraseña debe de tener 6 caracteres")
                }
                if(!validUser){
                    binding.txtInputUser.setError("El usuario debe de tener 6 caracteres")
                }
            }


        }



        return binding.root
    }




}




