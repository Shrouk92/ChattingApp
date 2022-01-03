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
    val recipientId:MutableLiveData<String> by lazy { MutableLiveData<String>() }
    var messageList= ArrayList<Message>()




    public fun Send_Message(view: View)
    {
        val senderUid= FirebaseUtils.firebaseAuth.currentUser?.uid


        val senderRoom= recipientId.value + senderUid
       val recipientRoom= senderUid + recipientId.value


        val enteredMessage= message.get()

            val message= enteredMessage?.let { recipientId.value?.let { it1 ->
                Message(it,senderUid!!,
                    it1
                )
            } }
            if (message != null) {
                saveChattingMessagesToDB( message ,senderRoom,recipientRoom)
            }



    }

    // function to save chatting messages to Firebase DB
    public fun saveChattingMessagesToDB( message:Message,  senderRoom:String, recipientroom:String)
    {

        dbReference.child("chats").child(senderRoom).child("messages").push()
            .setValue(message)
        dbReference.child("chats").child(recipientroom).child("messages").push()
            .setValue(message)


    }


    public fun GetMessagesFromDB():ArrayList<Message>
    {
        dbReference.child("chats").child("senderRoom").child("messages")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(itemising in snapshot.children)
                    {
                      val  m =itemising.getValue(Message::class.java)
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