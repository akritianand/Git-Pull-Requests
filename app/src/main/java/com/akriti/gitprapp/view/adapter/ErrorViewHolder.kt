package com.akriti.gitprapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.akriti.gitprapp.databinding.ItemRecyclerViewErrorBinding

class ErrorViewHolder (private val dataBinding: ItemRecyclerViewErrorBinding): RecyclerView.ViewHolder(dataBinding.root) {

    fun setClickListener(action: () -> Unit) {
        dataBinding.footerErrorText.setOnClickListener { action() }
    }
}