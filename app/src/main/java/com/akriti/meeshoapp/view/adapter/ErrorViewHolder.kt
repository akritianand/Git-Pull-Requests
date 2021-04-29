package com.akriti.meeshoapp.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ErrorViewHolder (private val itemView: View): RecyclerView.ViewHolder(itemView) {

    fun setClickListener(action: () -> Unit) {
        itemView.setOnClickListener { action() }
    }
}