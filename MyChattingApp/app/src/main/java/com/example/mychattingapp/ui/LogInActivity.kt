package com.example.mychattingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mychattingapp.R
import com.example.mychattingapp.databinding.ActivityLogInBinding
import com.example.mychattingapp.viewmodels.LogInViewModel

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var logInViewModel: LogInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        // Binding & ViewModel Connection
        binding= DataBindingUtil.setContentView(this, R.layout.activity_log_in)
        logInViewModel=ViewModelProvider(this).get(LogInViewModel::class.java)
        binding.loginviewmodel=logInViewModel



    }
}