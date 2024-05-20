package com.example.kotlin_corutines

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter:RecyclerView.Adapter<ToDoViewHolder>(){

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)


        context = parent.context

        return ToDoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 100

    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {

        holder.cbTodo.text = "Sample todo"
        holder.ivDelete.setOnClickListener(){
            Toast.makeText(context,"Hello from Button $position", Toast.LENGTH_LONG).show()
        }
    }
}