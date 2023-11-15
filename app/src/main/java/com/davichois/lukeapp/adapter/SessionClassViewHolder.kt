package com.davichois.lukeapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.davichois.lukeapp.databinding.ItemSessionClassBinding
import com.davichois.lukeapp.dto.SessionClassDTO

class SessionClassViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSessionClassBinding.bind(view)

    fun render(item: SessionClassDTO, onAvatarSelect: (String) -> Unit) {
        binding.tvNameSession.text = item.nameSession

        itemView.setOnClickListener {
            onAvatarSelect(item.id)
        }
    }

}