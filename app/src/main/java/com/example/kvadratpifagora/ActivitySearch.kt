package com.example.kvadratpifagora

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import java.text.SimpleDateFormat
import java.util.*

class ActivitySearch : AppCompatActivity() {
var searchFlag: Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


    }


    fun ProverkaSovpadeniy(string: String): Boolean {

        var c1 = false
        var c2 = false
        var c3 = false
        var c4 = false
        var c5 = false
        var c6 = false
        var c7 = false
        var c8 = false
        var c9 = false

        val pifagor = Pifagor()

        pifagor.pifCalc(string.replace(".", ""))

        if (check1.isChecked && editTxt1.text.length == pifagor.edinici.length) c1 = true
        if (check2.isChecked && editTxt1.text.length == pifagor.dvoyki.length) c2 = true
        if (check3.isChecked && editTxt1.text.length == pifagor.troyki.length) c3 = true
        if (check4.isChecked && editTxt1.text.length == pifagor.chetverki.length) c4 = true
        if (check5.isChecked && editTxt1.text.length == pifagor.pyaterki.length) c5 = true
        if (check6.isChecked && editTxt1.text.length == pifagor.shesterki.length) c6 = true
        if (check7.isChecked && editTxt1.text.length == pifagor.semerki.length) c7 = true
        if (check8.isChecked && editTxt1.text.length == pifagor.vosmerki.length) c8 = true
        if (check9.isChecked && editTxt1.text.length == pifagor.devyatki.length) c9 = true

        if (!check1.isChecked) c1 = true
        if (!check2.isChecked) c2 = true
        if (!check3.isChecked) c3 = true
        if (!check4.isChecked) c4 = true
        if (!check5.isChecked) c5 = true
        if (!check6.isChecked) c6 = true
        if (!check7.isChecked) c7 = true
        if (!check8.isChecked) c8 = true
        if (!check9.isChecked) c9 = true

        return c1 && c2 && c3 && c4 && c5 && c6 && c7 && c8 && c9

    }

    fun checkLenght(str: String): Boolean {
        return str.length == 8
    }

    fun txtClear() {
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

    fun onClick(view: View) {
        val b = view as Button
        when (b.id) {
            R.id.btnSearchBack -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            R.id.btnSerchStart -> {
                btnSerchStart.isEnabled=false

                val valDate = isDateExist()
                val lenght1 = checkLenght(editDateStart.text.toString())
                val lenght2 = checkLenght(editDateEnd.text.toString())
                val dte1: String
                val dte2: String
                if (lenght1 && lenght2) {

                    var dd: String = editDateStart.text.substring(0, 2)
                    var mm: String = editDateStart.text.substring(2, 4)
                    var yy: String = editDateStart.text.substring(4, 8)
                    dte1 = "$dd.$mm.$yy"
                    val valDte1 = valDate.isValidDate(dte1)
                    dd = editDateEnd.text.substring(0, 2)
                    mm = editDateEnd.text.substring(2, 4)
                    yy = editDateEnd.text.substring(4, 8)
                    dte2 = "$dd.$mm.$yy"
                    val valDte2 = valDate.isValidDate(dte2)
                    if (valDte1 && valDte2) {
                        StartSearch()
                    } else wrongDate()
                } else wrongDate()
                btnSerchStart.isEnabled=true

            }

            R.id.btnSearchEnd -> {7
                searchFlag=false
                btnSerchStart.isEnabled=true
            }

        }
    }

    fun StartSearch() {

        val validDate1 = isDateExist()
        val validDate2 = isDateExist()
        val dDate1: String
        val dDate2: String
        var dDate: String
        var cValue = editDateStart.text.toString()
        var dd: String = cValue.substring(0, 2)
        var mm: String = cValue.substring(2, 4)
        var yy: String = cValue.substring(4, 8)
        dDate1 = "$dd.$mm.$yy"

        cValue = editDateEnd.text.toString()

        dd = cValue.substring(0, 2)
        mm = cValue.substring(2, 4)
        yy = cValue.substring(4, 8)
        dDate2 = "$dd.$mm.$yy"

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)

        if (editDateStart.length() == 8 && editDateEnd.length() == 8 && validDate1.isValidDate(dDate1) && validDate2.isValidDate(dDate2)) {

            val strDates: ArrayList<String> = ArrayList()
            val adapter: ArrayAdapter<String>
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strDates)
            lstDate.setAdapter(adapter)


            dDate = dDate1
            searchFlag=true
            while (dteCompare(dDate, dDate2) && searchFlag) {

                if (ProverkaSovpadeniy(dDate)) {
                    strDates.add(0, dDate)
                    adapter.notifyDataSetChanged()
                }
                dDate = dteIncrement(dDate)
            }

        }
        searchFlag=false
    }

    fun dteIncrement(str: String): String {
        val dte: Calendar = Calendar.getInstance()
        val sdf: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        dte.setTime(sdf.parse(str))
        dte.add(Calendar.DATE, 1)
        return sdf.format(dte.getTime()).toString()
    }

    private fun dteCompare(date1: String, date2: String): Boolean {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val dte1: Date = sdf.parse(date1)
        val dte2: Date = sdf.parse(date2)

        return dte1.time < dte2.time
    }

    fun wrongDate() {
        val msg: Toast = Toast.makeText(applicationContext, "НЕПРАВИЛЬНАЯ ДАТА", Toast.LENGTH_LONG)
        msg.show()
    }

}