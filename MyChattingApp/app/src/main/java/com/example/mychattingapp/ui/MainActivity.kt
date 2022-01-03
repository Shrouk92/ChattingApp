package com.example.mychattingapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mychattingapp.R
import com.example.mychattingapp.adapters.MyRecyclerAdapter
import com.example.mychattingapp.databinding.ActivityMainBinding
import com.example.mychattingapp.firebaseutils.FirebaseUtils.firebaseAuth
import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.viewmodels.UsersViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var usersViewModel: UsersViewModel
    private lateinit var myRecyclerAdapter: MyRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        usersViewModel=ViewModelProvider(this).get(UsersViewModel::class.java)
        binding.recyclerbinding=usersViewModel


        // do this one for recycler view of messages // in order not to repeate data
        usersViewModel.userslist.observe(this, Observer {
        myRecyclerAdapter=MyRecyclerAdapter(it)
            myRecyclerAdapter.notifyDataSetChanged()
            binding.usersRecyclerview.adapter=myRecyclerAdapter

        })



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.logout ->{
                firebaseAuth.signOut()
                val intent=Intent(this,LogInActivity::class.java)
                startActivity(intent)
                finish()
              return true
            }
        }
        return true
    }
}