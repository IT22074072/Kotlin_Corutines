package com.example.kotlin_corutines

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView:TextView = findViewById(R.id.textView)
        val button:Button = findViewById(R.id.button)




        CoroutineScope(Dispatchers.Main).launch {
            counter(textView)
        }


        button.setOnClickListener(){
            Toast.makeText(this, "This is a button click", Toast.LENGTH_LONG).show()
            runBlocking {
                delay(3000)
            }
        }
    }


    //updating a ui component(with the suspend function)

    //if you want to run something parallel to the normal process
    private suspend fun counter(view:TextView){
        var x = 0
        while (true){
            view.text = x.toString()
            delay(1000)
            x++
        }
    }
}