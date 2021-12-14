package com.example.mychattingapp.viewmodels

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.firebaseutils.FirebaseUtils
import com.example.mychattingapp.firebaseutils.FirebaseUtils.dbReference
import com.example.mychattingapp.model.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChattingPageViewModel:ViewModel() {

    var message=ObservableField<String>()
    var senderId=MutableLiveData<String>()
    var recipientId=MutableLiveData<String>()
    var messageList= ArrayList<Message>()



    public fun Send_Message(view: View)
    {
        val senderUid= FirebaseUtils.firebaseAuth.currentUser?.uid

        val SenderRoom= recipientId.toString() + senderUid
       val RecipientRoom= senderUid + recipientId.toString()
//        val  SenderRoom="AAAAAAAAAAAA"
//        val RecipientRoom="nwdkhafhhjfs"

        val enteredMessage=message.get().toString()
        val message=Message(enteredMessage,senderUid!!)

        SaveChattingMessagesToDB( message ,SenderRoom,RecipientRoom)

    }

    // function to save chatting messages to Firebase DB
    public fun SaveChattingMessagesToDB( message:Message,  senderRoom:String, recipientRoom:String)
    {
       dbReference.child("chats").child("senderRoom").child("messages").push()
           .setValue(message).addOnSuccessListener {
               dbReference.child("chats").child("recipientRoom").child("messages").push()
                   .setValue(message)
           }


    }



    public fun GetMessagesFromDB():ArrayList<Message>
    {
        dbReference.child("chats").child("senderRoom").child("messages")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(itemsnapshot in snapshot.children)
                    {
                      val  m =itemsnapshot.getValue(Message::class.java)
                        messageList.add(m!!)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        return messageList

    }






}