package com.nicatnabiyev88.testdelete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress_bar.isVisible = false

        btn_background.setOnClickListener {
            doInBackground()
        }

        btn_ui.setOnClickListener {
            doInUI()
        }
    }

    private fun doInBackground(){
        progress_bar.isVisible = true
        btn_background.text = "Progress..."
        btn_background.isEnabled = false
        resetCount()
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0..100){
                withContext(Dispatchers.Main){
                    btn_background.text = "Proegress... $i%"
                    delay(500)
                }
            }
            doInvisibleProgressBar()
        }
    }

    private fun doInUI(){
        text_view.text = (++count).toString()
    }

    private suspend fun doInvisibleProgressBar(){
        withContext(Dispatchers.Main) {
            progress_bar.isVisible = false
            btn_background.text = "Done!"
            btn_background.isEnabled = true
        }
    }

    private fun resetCount(){
        count = 0
        text_view.text = count.toString()
    }
}