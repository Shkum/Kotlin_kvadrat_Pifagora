package com.example.kvadratpifagora

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_full_descr.*


class ActivityFullDescr : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_descr)

        //Добавляем скрол для textView
        textFullText.movementMethod = ScrollingMovementMethod()
    }

    fun onClick(view: View) {
        val b = view as Button
        when (b.id) {

            R.id.buttonBack -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }


            R.id.btnN1 -> {
                textFullText.text = getText(R.string.long1).toString().replace("@@@@@","\n")
            }

            R.id.btnN2 -> {
                textFullText.text = getText(R.string.long2).toString().replace("@@@@@","\n")
            }


            R.id.btnN3 -> {
                textFullText.text = getText(R.string.long3).toString().replace("@@@@@","\n")
            }

            R.id.btnN4 -> {
                textFullText.text = getText(R.string.long4).toString().replace("@@@@@","\n")
            }


            R.id.btnN5 -> {
                textFullText.text = getText(R.string.long5).toString().replace("@@@@@","\n")
            }


            R.id.btnN6 -> {
                textFullText.text = getText(R.string.long6).toString().replace("@@@@@","\n")
            }


            R.id.btnN7 -> {
                textFullText.text = getText(R.string.long7).toString().replace("@@@@@","\n")
            }


            R.id.btnN8 -> {
                textFullText.text = getText(R.string.long8).toString().replace("@@@@@","\n")
            }

            R.id.btnN9 -> {
                textFullText.text = getText(R.string.long9).toString().replace("@@@@@","\n")
            }

        }
    }
}



