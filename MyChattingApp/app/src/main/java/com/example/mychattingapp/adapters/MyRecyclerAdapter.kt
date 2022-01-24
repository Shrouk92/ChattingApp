package com.example.mychattingapp.adapters

import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mychattingapp.R
import com.example.mychattingapp.databinding.UserItemBinding
import com.example.mychattingapp.model.ChattingUsers
import com.example.mychattingapp.model.Message
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
        val currentUser=data[position]
      holder.bindingItem.textUser.text= currentUser.name

        holder.itemView.setOnClickListener(View.OnClickListener {
            // open the chatting page when choice a person from list
            // send the data of the choisen person to chat with from the recycler view
            val intent=Intent(holder.itemView.context,ChattingActivity::class.java)
            intent.putExtra("RecipientName",currentUser.name)
            intent.putExtra("RecipientUid",currentUser.uid)
            holder.itemView.context.startActivity(intent)
        })

    }
    class MyViewHolder(val bindingItem: UserItemBinding) : RecyclerView.ViewHolder(bindingItem.root){



    }


}