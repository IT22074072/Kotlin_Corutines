package com.example.kotlin_corutines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter:RecyclerView.Adapter<ToDoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
        return ToDoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1

    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {

        holder.cbTodo.text = "Sample todo"
    }
}