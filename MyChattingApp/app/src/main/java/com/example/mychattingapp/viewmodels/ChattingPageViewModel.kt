package com.example.mychattingapp.viewmodels

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.firebaseutils.FirebaseUtils.dbReference

class ChattingPageViewModel:ViewModel() {

    var message=ObservableField<String>()




    public fun Send_Message(view: View)
    {
        val enteredMessage=message.get().toString()




    }

    // function to save chatting messages to Firebase DB
    public fun SaveChattingMessagesToDB(  message:String)
    {
      //  dbReference.child("chats").child()


    }






}