package com.example.mychattingapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mychattingapp.R
import com.example.mychattingapp.databinding.RecievedMessageBinding
import com.example.mychattingapp.databinding.SendmessageLayoutBinding
import com.example.mychattingapp.databinding.UserItemBinding
import com.example.mychattingapp.firebaseutils.FirebaseUtils.firebaseAuth
import com.example.mychattingapp.model.Message

class MessageAdapter(val messages:ArrayList<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    val Sender=1
    val Reciever=2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        if(viewType==1)
        {
            val view: RecievedMessageBinding =
                DataBindingUtil.inflate(inflater, R.layout.recieved_message,parent,false)
            return RecieveHolder(view)
        }
        else{
            val view: SendmessageLayoutBinding =
                DataBindingUtil.inflate(inflater, R.layout.sendmessage_layout,parent,false)
            return SentHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.javaClass==RecieveHolder :: class.java)
        {
            val Holder=holder as RecieveHolder
            Holder.binding.recMsg.setText(messages[position].message)
        }
        else
        {
            val Holder=holder as SentHolder
            Holder.binding.sentMsg.setText(messages[position].message)
        }


    }

    override fun getItemViewType(position: Int): Int {
        val currentuser=messages[position]
        if(firebaseAuth.currentUser?.uid.equals(currentuser.senderId))
        {
            return Sender
        }
        else return Reciever
    }

    override fun getItemCount(): Int=messages.size





     class RecieveHolder( val binding: RecievedMessageBinding) : RecyclerView.ViewHolder(binding.root) {

    }
     class SentHolder( val binding: SendmessageLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {

    }
}