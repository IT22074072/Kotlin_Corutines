package com.example.kotlin_corutines

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_corutines.database.Todo
import com.example.kotlin_corutines.database.TodoDatabase
import com.example.kotlin_corutines.database.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TodoAdapter
    private lateinit var viewModel:MainActivityData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView:RecyclerView = findViewById(R.id.rvTodoList)
        val repository = TodoRepository(TodoDatabase.getInstance(this))
        viewModel = ViewModelProvider(this)[MainActivityData::class.java]

        viewModel.data.observe(this){
            adapter = TodoAdapter(it, repository, viewModel)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)

        }

        CoroutineScope(Dispatchers.IO).launch{
            val data = repository.getAllTodoItems()
            runOnUiThread{
                viewModel.setData(data)
            }
        }


        val btnAddItem:Button = findViewById(R.id.btnAddItem)

        btnAddItem.setOnClickListener{
            displayDialog(repository)
        }

    }

    private fun displayDialog(repository:TodoRepository){
        val builder = AlertDialog.Builder(this)

        //set the alert dialog title and message
        builder.setTitle("Enter new todo item:")
        builder.setMessage("Enter the todo item below:")

        //create an editText input field
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Set the positive button action
        builder.setPositiveButton("OK") { dialog, which ->
            // Get the input text and display a Toast message
            val item = input.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(Todo(item))
                val data = repository.getAllTodoItems()
                runOnUiThread {
                    viewModel.setData(data)
                }
            }
        }

        // Set the negative button action
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        // Create and show the alert dialog
        val alertDialog = builder.create()
        alertDialog.show()


    }

}