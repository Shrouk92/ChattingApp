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
import com.example.mychattingapp.firebaseutils.FirebaseUtils
import com.example.mychattingapp.firebaseutils.FirebaseUtils.firebaseAuth
import com.example.mychattingapp.model.Message
import com.example.mychattingapp.viewmodels.ChattingPageViewModel

class ChattingActivity : AppCompatActivity() {
    private lateinit var chattingBinding: ActivityChattingBinding
    private lateinit var chattingPageViewModel: ChattingPageViewModel
    private lateinit var messageAdapter: MessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.show()

        chattingBinding = DataBindingUtil.setContentView(this, R.layout.activity_chatting)
        chattingPageViewModel = ViewModelProvider(this).get(ChattingPageViewModel::class.java)
        chattingBinding.chattingbinding = chattingPageViewModel
        val recipientUid = intent.getStringExtra("RecipientUid")
        chattingPageViewModel.recipientId.value=recipientUid!!
        val senderUid= firebaseAuth.currentUser?.uid
        val  path=senderUid+recipientUid
        chattingPageViewModel.path.postValue(path)
        chattingPageViewModel.GetMessagesFromDB(path)


        chattingPageViewModel.messageList.observe(this, Observer {

            messageAdapter = MessageAdapter(it)
            messageAdapter.notifyDataSetChanged()
            chattingBinding.chatrecyclerview.adapter = messageAdapter
            chattingBinding.chatrecyclerview.smoothScrollToPosition(it.size)
        })

        // set the page of chatting with the other person
        val recieverUserName = intent.getStringExtra("RecipientName")
        supportActionBar?.title = recieverUserName




    }
}





