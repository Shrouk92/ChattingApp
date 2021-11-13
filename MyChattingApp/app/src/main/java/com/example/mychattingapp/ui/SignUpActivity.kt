package com.example.mychattingapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mychattingapp.R
import com.example.mychattingapp.databinding.ActivitySignUpBinding
import com.example.mychattingapp.viewmodels.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
   private lateinit var binding:ActivitySignUpBinding
   private lateinit var signUpViewModel: SignUpViewModel
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding=DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        signUpViewModel=ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.signupviewmodel=signUpViewModel





    }




}