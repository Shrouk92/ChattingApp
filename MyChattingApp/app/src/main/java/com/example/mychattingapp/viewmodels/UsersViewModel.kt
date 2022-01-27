package com.example.mychattingapp.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class UsersViewModel : ViewModel() {
    // livedata list of users names
    var usersList = MutableLiveData<ArrayList<ChattingUsers>>()
    val usersResponse:LiveData<ArrayList<ChattingUsers>>
        get()=usersList

    init {
        runBlocking {
           // usersList=UsersRepository.getAllUsers()

        }
    }







}