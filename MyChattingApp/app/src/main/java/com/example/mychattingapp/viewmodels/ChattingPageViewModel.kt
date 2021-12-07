package com.example.mychattingapp.viewmodels

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.firebaseutils.FirebaseUtils.dbReference

class ChattingPageViewModel:ViewModel() {

    var message=ObservableField<String>()
    val senderId=MutableLiveData<String>()
    var recipientId=MutableLiveData<String>()





    public fun Send_Message(view: View)
    {
        val enteredMessage=message.get().toString()





    }

    // function to save chatting messages to Firebase DB
    public fun SaveChattingMessagesToDB(  message:String,senderId:String,recieverId:String)
    {
      //  dbReference.child("chats").child()


    }






}