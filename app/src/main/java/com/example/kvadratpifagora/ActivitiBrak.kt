package com.example.kvadratpifagora

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activiti_brak.*
import kotlin.math.round

class ActivitiBrak : AppCompatActivity() {


    internal var isDate: Boolean = false
    internal var cValue: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiti_brak)


        //Отлеживаем введенный тект для мужа (Дату рождения)
        txtMuz.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val validDate = isDateExist()
                val pifagor = Pifagor()
                cValue = txtMuz.text.toString()
                isDate = false
                if (cValue.length == 8) {
                    val dDate: String
                    val dd: String = cValue.substring(0, 2)
                    val mm: String = cValue.substring(2, 4)
                    val yy: String = cValue.substring(4, 8)
                    dDate = "$dd.$mm.$yy"
                    if (validDate.isValidDate(dDate)) {
                        if (Integer.parseInt(yy) > 1999) {
                            val msg: Toast = Toast.makeText(
                                applicationContext,
                                getText(R.string.god2000),
                                Toast.LENGTH_LONG
                            )
                            msg.show()
                        }
                        isDate = true
                        pifagor.pifCalc(dd + mm + yy)

                        txt11.text = pifagor.edinici
                        txt12.text = pifagor.dvoyki
                        txt13.text = pifagor.troyki
                        txt14.text = pifagor.cheverki
                        txt15.text = pifagor.pyaterki
                        txt16.text = pifagor.shesterki
                        txt17.text = pifagor.semerki
                        txt18.text = pifagor.vosmerki
                        txt19.text = pifagor.devyatki

                        stabilnostMuza()

                        if (txtZenaDuh.text != "-") {
                            Stabilnost()
                            //убираем клавиатуру
                            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)
                        }
                    }
                    //Меняем подписи текста и кнопок на стандартные, если дата неправильная
                } else {
                    txt11.text = "1"
                    txt12.text = "2"
                    txt13.text = "3"
                    txt14.text = "4"
                    txt15.text = "5"
                    txt16.text = "6"
                    txt17.text = "7"
                    txt18.text = "8"
                    txt19.text = "9"
                    txtMuzDuh.text = "-"
                    txtMuzBit.text = "-"
                    txtDuh.text = "-"
                    txtBit.text = "-"
                    txtObshaya.text = "-"
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })


        //Отлеживаем введенный тект для жены (Дату рождения)
        txtZena.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val validDate = isDateExist()
                val pifagor = Pifagor()
                cValue = txtZena.text.toString()
                isDate = false
                if (cValue.length == 8) {
                    val dDate: String
                    val dd: String = cValue.substring(0, 2)
                    val mm: String = cValue.substring(2, 4)
                    val yy: String = cValue.substring(4, 8)
                    dDate = "$dd.$mm.$yy"
                    if (validDate.isValidDate(dDate)) {
                        if (Integer.parseInt(yy) > 1999) {
                            val msg: Toast = Toast.makeText(
                                applicationContext,
                                getText(R.string.god2000),
                                Toast.LENGTH_LONG
                            )
                            msg.show()
                        }
                        isDate = true
                        pifagor.pifCalc(dd + mm + yy)

                        txt21.text = pifagor.edinici
                        txt22.text = pifagor.dvoyki
                        txt23.text = pifagor.troyki
                        txt24.text = pifagor.cheverki
                        txt25.text = pifagor.pyaterki
                        txt26.text = pifagor.shesterki
                        txt27.text = pifagor.semerki
                        txt28.text = pifagor.vosmerki
                        txt29.text = pifagor.devyatki

                        stabilnostZeny()
                        if (txtMuzDuh.text != "-") {
                            Stabilnost()
                            //убираем клавиатуру
                            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)
                        }


                    }
                    //Меняем подписи текста и кнопок на стандартные, если дата неправильная
                } else {
                    txt21.text = "1"
                    txt22.text = "2"
                    txt23.text = "3"
                    txt24.text = "4"
                    txt25.text = "5"
                    txt26.text = "6"
                    txt27.text = "7"
                    txt28.text = "8"
                    txt29.text = "9"
                    txtZenaDuh.text = "-"
                    txtZenaBit.text = "-"
                    txtDuh.text = "-"
                    txtBit.text = "-"
                    txtObshaya.text = "-"

                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })


    }

    fun onClick(view: View) {
        val b = view as Button
        when (b.id) {
            R.id.btnBack -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            R.id.btnAbout -> {
                val msg = AlertDialog.Builder(this)
                msg.setTitle("О расчёте").setMessage(R.string.BrakAbout).setCancelable(false)
                    .setNegativeButton("ОК") { dialog, _ -> dialog.cancel() }
                val alert = msg.create()
                alert.show()
            }

            R.id.btnAbout2 -> {
                val msg = AlertDialog.Builder(this)
                msg.setTitle("О расчёте от автора книги").setMessage(R.string.BrakAbout2).setCancelable(false)
                    .setNegativeButton("ОК") { dialog, _ -> dialog.cancel() }
                val alert = msg.create()
                alert.show()
            }

        }
    }


    fun stabilnostMuza() {

        val pdm: Int = txt13.length() + txt15.length() + txt17.length()
        val ssm: Int = txt12.length() + txt15.length() + txt18.length()
        val psm: Int = txt13.length() + txt16.length() + txt19.length()
        val BSM: Int = pdm * ssm * psm
        val ddm: Int = txt11.length() + txt15.length() + txt19.length()
        val sm: Int = txt11.length() + txt12.length() + txt13.length()
        val crm: Int = txt11.length() + txt14.length() + txt17.length()
        val DSM: Int = ddm * sm * crm

        txtMuzDuh.text = "$DSM"

        txtMuzBit.text = "$BSM"

    }


    fun stabilnostZeny() {

        val pdm: Int = txt23.length() + txt25.length() + txt27.length()
        val ssm: Int = txt22.length() + txt25.length() + txt28.length()
        val psm: Int = txt23.length() + txt26.length() + txt29.length()
        val BSM: Int = pdm * ssm * psm
        val ddm: Int = txt21.length() + txt25.length() + txt29.length()
        val sm: Int = txt21.length() + txt22.length() + txt23.length()
        val crm: Int = txt21.length() + txt24.length() + txt27.length()
        val DSM: Int = ddm * sm * crm

        txtZenaDuh.text = "$DSM"

        txtZenaBit.text = "$BSM"

    }


    fun Stabilnost() {
        var s: Double
        s = (Integer.parseInt(txtMuzDuh.text.toString()) * Integer.parseInt(txtZenaDuh.text.toString())).toDouble() / 365
        s = round(s * 10.0) / 10.0
        txtDuh.text = "$s"
        s = (Integer.parseInt(txtMuzBit.text.toString()) * Integer.parseInt(txtZenaBit.text.toString())).toDouble() / 365
        s = round(s * 10.0) / 10.0
        txtBit.text = "$s"
        s = txtDuh.text.toString().toDouble() + (txtBit.text.toString().toDouble())
        s = round(s * 10.0) / 10.0
        txtObshaya.text = "$s"
    }


}










