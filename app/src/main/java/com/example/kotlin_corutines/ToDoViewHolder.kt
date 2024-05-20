package com.example.kotlin_corutines

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ToDoViewHolder(view: View):ViewHolder(view) {
    val cbTodo:CheckBox = view.findViewById(R.id.cbTodo)
    val ivDelete:ImageView = view.findViewById(R.id.ivDelete)

}