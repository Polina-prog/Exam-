package com.example.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.exam.API_Horoscope.RetrofitClient1
import kotlinx.coroutines.*

class Horoscope : AppCompatActivity() {
    companion object {
        const val LINK = "sign"
    }
    var mes = ""

    fun GetHoroscope(sign: String)= runBlocking {
        GlobalScope.launch{
            mes = RetrofitClient1().service.getHoroscope(sign)?.body()?.horoscope!!
            Log.d("MyLog",mes)
        }
    }

    fun ColorBySign (sign: String) {
        val title = findViewById<TextView>(R.id.textTitle2)
        title.setText(sign.replaceFirstChar{it.titlecase()})
        if (sign == "aries") title.setTextColor(resources.getColor(R.color.aries))
        if (sign == "taurus") title.setTextColor(resources.getColor(R.color.taurus))
        if (sign == "gemini") title.setTextColor(resources.getColor(R.color.gemini))
        if (sign == "cancer") title.setTextColor(resources.getColor(R.color.cancer))
        if (sign == "leo") title.setTextColor(resources.getColor(R.color.leo))
        if (sign == "virgo") title.setTextColor(resources.getColor(R.color.virgo))
        if (sign == "libra") title.setTextColor(resources.getColor(R.color.libra))
        if (sign == "scorpio") title.setTextColor(resources.getColor(R.color.scorpio))
        if (sign == "sagittarius") title.setTextColor(resources.getColor(R.color.sagittarius))
        if (sign == "capricorn") title.setTextColor(resources.getColor(R.color.capricorn))
        if (sign == "aquarius") title.setTextColor(resources.getColor(R.color.aquarius))
        if (sign == "pisces") title.setTextColor(resources.getColor(R.color.pisces))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoscope)
    }

    override fun onStart() {
        super.onStart()

        val sign = intent.getStringExtra(Main.LINK).toString()
        val close = findViewById<TextView>(R.id.Close)
        val title = findViewById<TextView>(R.id.textTitle2)
        val horoscope = findViewById<TextView>(R.id.textHoroscope)

        GetHoroscope(sign)
        while (mes == "") {
        }
        ColorBySign(sign)
        horoscope.setText(mes)

        close.setOnClickListener {
            this.finish()
        }
    }
}