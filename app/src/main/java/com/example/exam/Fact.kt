package com.example.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.task2.RetrofitClient2
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.squareup.picasso.Picasso

class Fact : AppCompatActivity() {
    var url = ""
    var exp = ""

    fun image()= runBlocking {
        GlobalScope.launch{
            url = RetrofitClient2().service.getImage("Wo32R1UzAbnZD694PAU5O4g46ZPRjPt2oXw3a7iG")?.body()?.url!!
        }
    }

    fun text()= runBlocking {
        GlobalScope.launch{
            exp = RetrofitClient2().service.getImage("Wo32R1UzAbnZD694PAU5O4g46ZPRjPt2oXw3a7iG")?.body()?.explanation!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fact)

        val image = findViewById<ImageView>(R.id.Image)
        val text = findViewById<TextView>(R.id.Text)

        image()
        text()
        while (true) {
            if ((exp != "") && (url != "")) break
        }
        Picasso.get().load(url).into(image)
        text.setText(exp)

    }

    override fun onStart() {
        super.onStart()

        val close = findViewById<TextView>(R.id.close)

        close.setOnClickListener {
            this.finish()
        }
    }
}