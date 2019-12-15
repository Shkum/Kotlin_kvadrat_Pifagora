package com.example.kvadratpifagora

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*


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

    fun ProverkaSovpadeniy(string: String){

    }

    fun CheckLengt(): Boolean {
        return true
    }

    fun txtClear(){
        editTxt1.setText("")
        editTxt2.setText("")
        editTxt3.setText("")
        editTxt4.setText("")
        editTxt5.setText("")
        editTxt6.setText("")
        editTxt7.setText("")
        editTxt8.setText("")
        editTxt9.setText("")
    }

}
