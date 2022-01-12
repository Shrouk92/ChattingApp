package com.example.mychattingapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.utils.FirebaseUtils.dbReference
import com.example.mychattingapp.utils.FirebaseUtils.firebaseAuth
import com.example.mychattingapp.model.ChattingUsers
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class UsersViewModel :ViewModel(){
    // livedata list of users names
    val userslist= MutableLiveData<ArrayList<ChattingUsers>>()
    val users= ArrayList<ChattingUsers>()
    init {
        get_users()
    }



    public fun get_users()
    {
        dbReference.child("user").addValueEventListener(object :ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (mysnapshot in snapshot.children)
                {
                    val chattingUsers=mysnapshot.getValue(ChattingUsers::class.java)
                    if(firebaseAuth.currentUser?.email!=chattingUsers?.email) {
                        if (chattingUsers != null) {
                            users.add(chattingUsers)
                        }
                        else
                        {
                          val   c=ChattingUsers("em","em","em")
                            users.add(c)
                        }
                    }
                }
                userslist.postValue(users)

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}