package com.example.mychattingapp.viewmodels

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.utils.FirebaseUtils
import com.example.mychattingapp.utils.FirebaseUtils.dbReference
import com.example.mychattingapp.model.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChattingPageViewModel:ViewModel() {

    var message=ObservableField<String>()
    val recipientId:MutableLiveData<String> by lazy { MutableLiveData<String>() }
    var messageList= MutableLiveData<ArrayList<Message>>()
    var messages =ArrayList<Message>()
    var path=MutableLiveData<String>()

    init {
        getMessagesFromDB(path.value.toString())
    }


    public fun sendMessage(view: View)
    {
        val senderUid= FirebaseUtils.firebaseAuth.currentUser?.uid
        val senderRoom= recipientId.value + senderUid
       val recipientRoom= senderUid + recipientId.value


        val enteredMessage= message.get()


        val messageObj=Message(enteredMessage!!,recipientId.value!!,senderUid!!)
        message.set("")

        saveChattingMessagesToDB( messageObj ,senderRoom,recipientRoom)

    }

    // function to save chatting messages to Firebase DB
    public fun saveChattingMessagesToDB( message:Message,  senderRoom:String, recipientroom:String)
    {

        dbReference.child("chats").child(senderRoom).child("messages").push()
            .setValue(message)
        dbReference.child("chats").child(recipientroom).child("messages").push()
            .setValue(message)


    }


    public fun getMessagesFromDB(path:String)
    {
        dbReference.child("chats").child(path).child("messages")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    messages.clear()
                    for(itemising in snapshot.children)
                    {
                      val  m =itemising.getValue(Message::class.java)
                        messages.add(m!!)
                    }
                    messageList.postValue(messages)




                }

                override fun onCancelled(error: DatabaseError) {
                }

            })

    }






}