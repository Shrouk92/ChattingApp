package com.example.mychattingapp.viewmodels

import android.app.Application
import android.content.Context
import android.util.TimeUtils
import androidx.lifecycle.*
import com.example.mychattingapp.database.UsersDataBase
import com.example.mychattingapp.database.getDatabase
import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.repository.UsersRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow


@InternalCoroutinesApi
class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob= SupervisorJob()
    private val viewModelScope= CoroutineScope(viewModelJob+Dispatchers.Main)
    @InternalCoroutinesApi
    private val dataBase = getDatabase(application)
    @InternalCoroutinesApi
    private val usersRepository=UsersRepository(dataBase)



    init {
        viewModelScope.launch {
            usersRepository.refreshDatabase()

        }
    }
    // livedata list of users names
    val userLiveData=usersRepository.usersLiveList



    // cancel coroutines when view model cleared
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}