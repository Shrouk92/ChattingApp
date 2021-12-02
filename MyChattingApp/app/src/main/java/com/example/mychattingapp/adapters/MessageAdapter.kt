package com.example.mychattingapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mychattingapp.databinding.RecievedMessageBinding
import com.example.mychattingapp.databinding.SendmessageLayoutBinding

class MessageAdapter(itemView: View) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    inner class RecieveHolder(binding: RecievedMessageBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    inner class SentHolder(binding: SendmessageLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}