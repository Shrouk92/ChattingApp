package com.example.mychattingapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mychattingapp.R
import com.example.mychattingapp.databinding.UserItemBinding
import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.ui.ChattingActivity

class MyRecyclerAdapter(val data: ArrayList<ChattingUsers>):RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout of the item
        val layoutInflater=LayoutInflater.from(parent.context)
        val view:UserItemBinding=DataBindingUtil.inflate(layoutInflater,R.layout.user_item,parent,false)
        return MyViewHolder(view)

    }


    override fun getItemCount(): Int =data.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentusername=data[position].name
      holder.bindingItem.textuser.text= currentusername

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent=Intent(holder.itemView.context,ChattingActivity::class.java)
            intent.putExtra("name",currentusername)
            holder.itemView.context.startActivity(intent)
        })

    }
    class MyViewHolder(val bindingItem: UserItemBinding) : RecyclerView.ViewHolder(bindingItem.root){



    }


}