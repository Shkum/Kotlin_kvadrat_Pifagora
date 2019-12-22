package com.example.kvadratpifagora

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_search.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round
import kotlinx.coroutines.*


class ActivitySearch : AppCompatActivity() {
    private var searchFlag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.main_activity_background_color))

        lstDate.onItemClickListener = OnItemClickListener { _, itemClicked, _, _ ->
            var getDte: String = (itemClicked as TextView).text.toString()
            textView8.text = getDte
            getDte = getDte.replace(".", "")
            val pifagor = Pifagor()
            pifagor.pifCalc(getDte)
            editTxt1.setText(pifagor.edinici)
            editTxt2.setText(pifagor.dvoyki)
            editTxt3.setText(pifagor.troyki)
            editTxt4.setText(pifagor.chetverki)
            editTxt5.setText(pifagor.pyaterki)
            editTxt6.setText(pifagor.shesterki)
            editTxt7.setText(pifagor.semerki)
            editTxt8.setText(pifagor.vosmerki)
            editTxt9.setText(pifagor.devyatki)
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (imm.isActive) imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)

        }

        editDateStart.requestFocus()
    }


    private fun proverkaSovpadeniy(string: String): Boolean {

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
        if (check2.isChecked && editTxt2.text.length == pifagor.dvoyki.length) c2 = true
        if (check3.isChecked && editTxt3.text.length == pifagor.troyki.length) c3 = true
        if (check4.isChecked && editTxt4.text.length == pifagor.chetverki.length) c4 = true
        if (check5.isChecked && editTxt5.text.length == pifagor.pyaterki.length) c5 = true
        if (check6.isChecked && editTxt6.text.length == pifagor.shesterki.length) c6 = true
        if (check7.isChecked && editTxt7.text.length == pifagor.semerki.length) c7 = true
        if (check8.isChecked && editTxt8.text.length == pifagor.vosmerki.length) c8 = true
        if (check9.isChecked && editTxt9.text.length == pifagor.devyatki.length) c9 = true

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

    private fun checkLenght(str: String): Boolean {
        return str.length == 8
    }


    fun onClick(view: View) {
        val b = view as Button
        textView8.text = ""
        when (b.id) {
            R.id.btnSearchBack -> {
                searchFlag = false
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            R.id.btnSearchStart -> {
                btnSearchStart.isEnabled = false

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

                        startSearch()

                    } else wrongDate()
                } else wrongDate()


            }

            R.id.btnSearchEnd -> {

                searchFlag = false
                btnSearchStart.isEnabled = true
            }

        }
    }

    private fun startSearch() {

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
        if (imm.isActive) imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)

        if (editDateStart.length() == 8 && editDateEnd.length() == 8 && validDate1.isValidDate(dDate1) && validDate2.isValidDate(dDate2)) {

            val strDates: ArrayList<String> = ArrayList()
            val adapter: ArrayAdapter<String>
            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strDates)
            lstDate.adapter = adapter


            dDate = dDate1
            searchFlag = true

            //////////////////////////////////////////////////////////////////////////////////////////////////////

            while (dteCompare(dDate, dDate2) && searchFlag) {

                if (proverkaSovpadeniy(dDate)) {
                    strDates.add(0, dDate)
                    adapter.notifyDataSetChanged()
                }
                dDate = dteIncrement(dDate)
                textView7.text = calcStatus(dDate1, dDate2, dDate)
            }

            btnSearchStart.isEnabled = true
            searchFlag = false

        }

    }

    private fun dteIncrement(str: String): String {
        val dte: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        dte.time = sdf.parse(str)
        dte.add(Calendar.DATE, 1)
        return sdf.format(dte.time).toString()
    }

    private fun dteCompare(date1: String, date2: String): Boolean {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val dte1: Date = sdf.parse(date1)
        val dte2: Date = sdf.parse(date2)
        return dte1.time < dte2.time
    }

    private fun wrongDate() {
        val msg: Toast = Toast.makeText(applicationContext, "НЕПРАВИЛЬНАЯ ДАТА", Toast.LENGTH_LONG)
        msg.show()
    }

    private fun calcStatus(startdate: String, enddate: String, currentdate: String): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val dte1: Date = sdf.parse(startdate)
        val dte2: Date = sdf.parse(enddate)
        val dte3: Date = sdf.parse(currentdate)
        val dateDiff: Long = ((dte2.time - dte1.time) / 60 / 60 / 24 / 1000)
        val dateDiffCurrent: Long = ((dte2.time - dte3.time) / 60 / 60 / 24 / 1000)
        var result: Double = (dateDiff.toDouble() - dateDiffCurrent.toDouble()) / dateDiff.toDouble()

        result = round(result * 10.0) / 10.0

        return (result * 100).toString() + " %"
    }


}