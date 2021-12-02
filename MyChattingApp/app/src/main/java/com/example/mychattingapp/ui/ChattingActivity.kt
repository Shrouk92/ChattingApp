package com.example.mychattingapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mychattingapp.R
import com.example.mychattingapp.databinding.ActivityChattingBinding
import com.example.mychattingapp.viewmodels.ChattingPageViewModel

class ChattingActivity : AppCompatActivity() {
    private lateinit var chattingBinding: ActivityChattingBinding
    private lateinit var chattingPageViewModel: ChattingPageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chattingBinding=DataBindingUtil.setContentView(this,R.layout.activity_chatting)
        chattingPageViewModel=ViewModelProvider(this).get(ChattingPageViewModel::class.java)
        chattingBinding.chattingbinding=chattingPageViewModel

        val username=intent.getStringExtra("name")
           supportActionBar?.title=username
    }
}