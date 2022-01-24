package com.example.mychattingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UsersViewModel : ViewModel() {
    // livedata list of users names
    val usersList : MutableLiveData<ArrayList<ChattingUsers>> by lazy {
        MutableLiveData<ArrayList<ChattingUsers>>()
    }



    suspend fun getUsers()
    {
        withContext(Dispatchers.IO)
        {
            usersList.postValue(UsersRepository.getAllUsers())

        }
    }



}