package com.example.kvadratpifagora

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round


class ActivitySearch : AppCompatActivity() {


    var strArr: List<String> = ArrayList()

    private var searchFlag: Boolean = false
    private var value: String = ""

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

    private fun checkLength(str: String): Boolean {
        return str.length == 8
    }

    private fun checkTotalLength(): Boolean {
        var c1 = 0
        var c2 = 0
        var c3 = 0
        var c4 = 0
        var c5 = 0
        var c6 = 0
        var c7 = 0
        var c8 = 0
        var c9 = 0

        if (check1.isChecked) c1 = editTxt1.text.length
        if (check2.isChecked) c2 = editTxt2.text.length
        if (check3.isChecked) c3 = editTxt3.text.length
        if (check4.isChecked) c4 = editTxt4.text.length
        if (check5.isChecked) c5 = editTxt5.text.length
        if (check6.isChecked) c6 = editTxt6.text.length
        if (check7.isChecked) c7 = editTxt7.text.length
        if (check8.isChecked) c8 = editTxt8.text.length
        if (check9.isChecked) c9 = editTxt9.text.length

        val cTot: Int = c1 + c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9

        return (cTot <= 15)

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

                val valDate = isDateExist()
                val length1 = checkLength(editDateStart.text.toString())
                val length2 = checkLength(editDateEnd.text.toString())
                val dte1: String
                val dte2: String
                if (checkTotalLength()) {
                    if (length1 && length2) {
                        enableAll(false)
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

                            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                            GlobalScope.launch(Dispatchers.Main) {
                                updateUi()
                            }
                        } else wrongDate()
                    } else wrongDate()
                } else wrongLength()
            }

            R.id.btnSearchEnd -> {
                searchFlag = false
                enableAll(true)
            }

        }
    }


    private suspend fun updateUi() {
        strArr = emptyList()
        var adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, strArr)
        lstDate.adapter = adapter

        withContext(Dispatchers.Default) {
            value = withContext(Dispatchers.Default) {
                startSearch()
            }
        }
        strArr = value.split(";")
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, strArr)
        lstDate.adapter = adapter
        enableAll(true)
    }


    private suspend fun startSearch(): String {
        val dateStrings: ArrayList<String> = ArrayList()
        val adapter: ArrayAdapter<String>
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dateStrings)
        lstDate.adapter = adapter

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

        var strDates = ""

        if (editDateStart.length() == 8 && editDateEnd.length() == 8 && validDate1.isValidDate(dDate1) && validDate2.isValidDate(dDate2)) {

            dDate = dDate1
            searchFlag = true

            //////////////////////////////////////////////////////////////////////////////////////////////////////
            var s: String
            while (dteCompare(dDate, dDate2) && searchFlag) {

                if (proverkaSovpadeniy(dDate)) {
                    s = if (strDates.isNotEmpty()) ";" else ""
                    strDates = "$strDates$s$dDate"
                    dateStrings.add(dateStrings.lastIndex + 1, dDate)
                    delay(200)
                    GlobalScope.launch(Dispatchers.Main) {
                        adapter.notifyDataSetChanged()
                    }
                }
                dDate = dteIncrement(dDate)
                GlobalScope.launch(Dispatchers.Main) {
                    textView7.text = calcStatus(dDate1, dDate2, dDate)
                }
            }

            searchFlag = false

        }
        return strDates
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

    private fun wrongLength() {
        val msg: Toast = Toast.makeText(applicationContext, "Общее колличество цифр в выбранных характеристиках не может быть больше 15", Toast.LENGTH_LONG)
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

        result = round(result * 100)

        return (result).toString() + " %"
    }

    private fun enableAll(flag: Boolean) {
        check1.isEnabled = flag
        check2.isEnabled = flag
        check3.isEnabled = flag
        check4.isEnabled = flag
        check5.isEnabled = flag
        check6.isEnabled = flag
        check7.isEnabled = flag
        check8.isEnabled = flag
        check9.isEnabled = flag
        editTxt1.isEnabled = flag
        editTxt2.isEnabled = flag
        editTxt3.isEnabled = flag
        editTxt4.isEnabled = flag
        editTxt5.isEnabled = flag
        editTxt6.isEnabled = flag
        editTxt7.isEnabled = flag
        editTxt8.isEnabled = flag
        editTxt9.isEnabled = flag
        editDateStart.isEnabled = flag
        editDateEnd.isEnabled = flag
        btnSearchStart.isEnabled = flag
        btnSearchEnd.isEnabled = !flag
        lstDate.isEnabled = flag

    }


}