package com.example.kvadratpifagora

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class ActivitySearch : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)



        }

    fun onClick(view: View) {
        val b = view as Button
        when (b.id) {
            R.id.btnSearchBack -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }

    }
}
