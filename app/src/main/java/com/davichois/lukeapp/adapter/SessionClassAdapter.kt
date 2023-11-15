package com.davichois.lukeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davichois.lukeapp.R
import com.davichois.lukeapp.dto.SessionClassDTO

class SessionClassAdapter(
    private val sessionList: Array<SessionClassDTO>,
    private val onAvatarSelect: (String) -> Unit
): RecyclerView.Adapter<SessionClassViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionClassViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SessionClassViewHolder(layoutInflater.inflate(R.layout.item_session_class, parent, false))
    }

    override fun getItemCount(): Int = sessionList.size

    override fun onBindViewHolder(holder: SessionClassViewHolder, position: Int) {
        val item = sessionList[position]
        holder.render(item, onAvatarSelect)
    }

}