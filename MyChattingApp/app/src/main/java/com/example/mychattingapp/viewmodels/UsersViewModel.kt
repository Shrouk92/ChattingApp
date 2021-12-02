package com.example.mychattingapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.firebaseutils.FirebaseUtils.dbReference
import com.example.mychattingapp.firebaseutils.FirebaseUtils.firebaseAuth
import com.example.mychattingapp.model.ChattingUsers
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class UsersViewModel :ViewModel(){
    // livedata list of users names
    val userslist= MutableLiveData<ArrayList<ChattingUsers>>()
    val users= ArrayList<ChattingUsers>()
    init {
        getusers()
    }



    public fun getusers()
    {

        dbReference.child("user").addValueEventListener(object :ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (mysnapshot in snapshot.children)
                {
                    val chattingUsers=mysnapshot.getValue(ChattingUsers::class.java)
                    if(firebaseAuth.currentUser?.email!=chattingUsers?.email) {
                        users.add(chattingUsers!!)
                        userslist.postValue(users)
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

















}