package com.example.mychattingapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mychattingapp.R
import com.example.mychattingapp.adapters.MessageAdapter
import com.example.mychattingapp.databinding.ActivityChattingBinding
import com.example.mychattingapp.firebaseutils.FirebaseUtils.firebaseAuth
import com.example.mychattingapp.model.Message
import com.example.mychattingapp.viewmodels.ChattingPageViewModel

class ChattingActivity : AppCompatActivity() {
    private lateinit var chattingBinding: ActivityChattingBinding
    private lateinit var chattingPageViewModel: ChattingPageViewModel
    private lateinit var messageAdapter: MessageAdapter
    var messageList = ArrayList<Message>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        chattingBinding = DataBindingUtil.setContentView(this, R.layout.activity_chatting)
        chattingPageViewModel = ViewModelProvider(this).get(ChattingPageViewModel::class.java)
        chattingBinding.chattingbinding = chattingPageViewModel
        val recipientUid = intent.getStringExtra("RecipientUid")
        chattingPageViewModel.recipientId.value=recipientUid!!
       // Toast.makeText(applicationContext, recipientUid, Toast.LENGTH_LONG).show()

        // fill the array of messages
        //  messageList=ArrayList()
        messageList = chattingPageViewModel.GetMessagesFromDB()
        chattingBinding.chatrecyclerview.layoutManager = LinearLayoutManager(this)
        messageAdapter = MessageAdapter(messageList)
        chattingBinding.chatrecyclerview.adapter = messageAdapter
        // set the page of chatting with the other person
        val recieverUserName = intent.getStringExtra("RecipientName")
        supportActionBar?.title = recieverUserName



    }
}





