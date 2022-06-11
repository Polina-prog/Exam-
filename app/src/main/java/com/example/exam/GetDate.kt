package com.example.exam

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GetDate : AppCompatActivity() {
    var pref : SharedPreferences? = null
    var daySave = ""
    var monthSave = ""
    var yearSave = ""
    var first = true

    fun SignByDate (m: Int, d: Int): String {
        var sign = ""
        if ( m == 3 && d >= 21 || m == 4 && d <= 20 ) sign = "aries"
        else if ( m == 4 && d >= 21 || m == 5 && d <= 20 ) sign = "taurus"
        else if ( (m == 5 && d >= 21) || (m == 6 && d <= 21) ) sign = "gemini"
        else if ( m == 6 && d >= 22 || m == 7 && d <= 22 ) sign = "cancer"
        else if ( m == 7 && d >= 23 || m == 8 && d <= 23 ) sign = "leo"
        else if ( m == 8 && d >= 24 || m == 9 && d <= 23 ) sign = "virgo"
        else if ( m == 9 && d >= 24 || m == 10 && d <= 22 ) sign = "libra"
        else if ( m == 10 && d >= 23 || m == 11 && d <= 22 ) sign = "scorpio"
        else if ( m == 11 && d >= 23 || m == 12 && d <= 21 ) sign = "sagittarius"
        else if ( m == 12 && d >= 22 || m == 1 && d <= 20 ) sign = "capricorn"
        else if ( m == 1 && d >= 21 || m == 2 && d <= 19 ) sign = "aquarius"
        else sign = "pisces"
        return sign
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_date)
        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
    }

    fun saveData (name: String, data: String) {
        var editor = pref?.edit()
        editor?.putString(name, data)
        editor?.apply()
    }

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onStart() {
        super.onStart()

        val btn = findViewById<Button>(R.id.buttonGet)
        val day = findViewById<EditText>(R.id.Days)
        val month = findViewById<Spinner>(R.id.Monthes)
        val year = findViewById<EditText>(R.id.Years)
        val error = findViewById<TextView>(R.id.Error)

        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val dateYear = date.substringBefore('-').toInt()
        val dateMonth = date.substringAfter('-').substringBefore('-').toInt()
        val dateDay = date.substringAfter('-').substringAfter('-').toInt()

        val not = arrayOf(230, 231, 431, 631, 931, 1131)


        if ((pref?.getString("d", "") != "") && (first == true)) {
            first = false
            day.setText(pref?.getString("d", ""))
            pref?.getString("m", "")?.toInt()?.minus(1)?.let { month.setSelection(it) }
            year.setText(pref?.getString("y", ""))

            var d = day.getText().toString().toIntOrNull()
            var m = month.getSelectedItemPosition()+1
            val intent = Intent(this, Main::class.java)
            var sign = d?.let { SignByDate (m, it) }

            intent.putExtra(Main.LINK, sign)
            startActivity(intent)
        }

        btn.setOnClickListener {
            var d = day.getText().toString().toIntOrNull()
            var m = month.getSelectedItemPosition()+1
            var y = year.getText().toString().toIntOrNull()
            val intent = Intent(this, Main::class.java)

            daySave = d.toString()
            monthSave = m.toString()
            yearSave = y.toString()

            if ((d != null && y != null ) && ((y==dateYear && ((m==dateMonth && d<=dateDay) || m<dateMonth)) || y<dateYear) && !not.contains((m.toString()+d.toString()).toInt()) && ((m != 2) || (d <=28) || (y % 4 == 0))) {
                var sign = SignByDate (m, d)
                error.setText("")
                saveData("d", daySave)
                saveData("m", monthSave)
                saveData("y", yearSave)
                intent.putExtra(Main.LINK, sign)
                startActivity(intent)
            }
            else error.setText("Check that the data entered is correct!")
        }
    }
}