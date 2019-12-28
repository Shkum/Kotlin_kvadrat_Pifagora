package com.example.kvadratpifagora

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {


    private val fileName = "list.txt"
    var listOfDates: String = ""
    internal var isDate: Boolean = false
    internal var cValue: String = ""
    private var msgText: String = ""

    //Обработка меню под тремя точками
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnu_About -> {
                msgAbout()
                true
            }
            R.id.mnu_Exit -> {
                finishAffinity()
                true
            }
            R.id.mnu_Brak -> {
                val intent = Intent(this, ActivityBrak::class.java)
                startActivity(intent)
                true
            }
            R.id.mnu_PolnOpis -> {
                val intent = Intent(this, ActivityFullDescr::class.java)
                startActivity(intent)
                true
            }

            R.id.mnu_Search -> {
                val intent = Intent(this, ActivitySearch::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    //Обработка кликов по любой из цифровых кнопок

    fun onClick(view: View) {
        val b = view as Button


        //убираем клавиатуру если поле ввода потеряло фокус
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (txtBirthDay.hasFocus()) imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)

        // Снимаем фокус с поля ввода и проматываем TextView на начало
        txtBirthDay.clearFocus()
        txtText.scrollTo(0, 0)

        val bTxt = b.text.toString()
        val strArr: Array<String>
        var dataStr: String

        if (b.id == R.id.btnSave) {
            val msgAbout: Toast = Toast.makeText(applicationContext, getText(R.string.About), Toast.LENGTH_LONG)
            msgAbout.show()
        }

        //Подбор текста в зависимости от нажатой цифровой кнопки, подробное описание
        if (isDate) {
            when (b.id) {
                R.id.btn1 -> {
                    dataStr = getText(R.string.long1) as String
                    strArr = dataStr.split("@@@@@".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    dataStr = strArr[if (bTxt.length < 6) bTxt.length else 6]
                    txtText.text = dataStr
                }
                R.id.btn2 -> {
                    dataStr = getText(R.string.long2) as String
                    strArr = dataStr.split("@@@@@".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    dataStr = strArr[if (bTxt.length < 5) bTxt.length + 1 else 5]
                    txtText.text = dataStr
                }
                R.id.btn3 -> {
                    dataStr = getText(R.string.long3) as String
                    strArr = dataStr.split("@@@@@".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    dataStr = strArr[if (bTxt.length < 5) bTxt.length + 1 else 5]
                    txtText.text = dataStr
                }
                R.id.btn4 -> {
                    dataStr = getText(R.string.long4) as String
                    strArr = dataStr.split("@@@@@".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    dataStr = strArr[if (bTxt.length < 3) bTxt.length + 1 else 3]
                    txtText.text = dataStr
                }
                R.id.btn5 -> {
                    dataStr = getText(R.string.long5) as String
                    strArr = dataStr.split("@@@@@".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    dataStr = strArr[if (bTxt.length < 4) bTxt.length + 1 else 4]
                    txtText.text = dataStr
                }
                R.id.btn6 -> {
                    dataStr = getText(R.string.long6) as String
                    strArr = dataStr.split("@@@@@".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    dataStr = strArr[if (bTxt.length < 4) bTxt.length + 1 else 4]
                    txtText.text = dataStr
                }
                R.id.btn7 -> {
                    dataStr = getText(R.string.long7) as String
                    strArr = dataStr.split("@@@@@".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    dataStr = strArr[if (bTxt.length < 4) bTxt.length + 1 else 4]
                    txtText.text = dataStr
                }
                R.id.btn8 -> {
                    dataStr = getText(R.string.long8) as String
                    strArr = dataStr.split("@@@@@".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    dataStr = strArr[if (bTxt.length < 3) bTxt.length + 1 else 3]
                    txtText.text = dataStr
                }
                R.id.btn9 -> {
                    dataStr = getText(R.string.long9) as String
                    strArr = dataStr.split("@@@@@".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    dataStr = strArr[if (bTxt.length < 4) bTxt.length + 1 else 4]
                    txtText.text = dataStr
                }
            }
        }

        //Подбор текста в зависимости от нажатой цифровой кнопкиб КРАТКОЕ описание
        if (!isDate) {
            when (b.id) {
                R.id.btn1 -> txtText.text = getText(R.string.short1)
                R.id.btn2 -> txtText.text = getText(R.string.short2)
                R.id.btn3 -> txtText.text = getText(R.string.short3)
                R.id.btn4 -> txtText.text = getText(R.string.short4)
                R.id.btn5 -> txtText.text = getText(R.string.short5)
                R.id.btn6 -> txtText.text = getText(R.string.short6)
                R.id.btn7 -> txtText.text = getText(R.string.short7)
                R.id.btn8 -> txtText.text = getText(R.string.short8)
                R.id.btn9 -> txtText.text = getText(R.string.short9)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.setBackgroundColor(ContextCompat.getColor(this, R.color.main_activity_background_color))


        //Добавляем возможность прокрутки в TextView
        txtText.movementMethod = ScrollingMovementMethod()


        //Отлеживаем введенный тект (Дату рождения)
        txtBirthDay.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val validDate = isDateExist()
                val pifagor = Pifagor()
                cValue = txtBirthDay.text.toString()
                isDate = false
                if (cValue.length == 8) {
                    val dDate: String
                    val dd: String = cValue.substring(0, 2)
                    val mm: String = cValue.substring(2, 4)
                    val yy: String = cValue.substring(4, 8)
                    dDate = "$dd.$mm.$yy"
                    if (validDate.isValidDate(dDate)) {
                        if (Integer.parseInt(yy) > 1999) {
                            val msg: Toast = Toast.makeText(applicationContext, getText(R.string.god2000), Toast.LENGTH_LONG)
                            msg.show()
                        }
                        isDate = true
                        pifagor.pifCalc(dd + mm + yy)

                        btn1.text = pifagor.edinici
                        btn2.text = pifagor.dvoyki
                        btn3.text = pifagor.troyki
                        btn4.text = pifagor.chetverki
                        btn5.text = pifagor.pyaterki
                        btn6.text = pifagor.shesterki
                        btn7.text = pifagor.semerki
                        btn8.text = pifagor.vosmerki
                        btn9.text = pifagor.devyatki

                        txtPlotskayaDiagonal.text = pifagor.plotskayaDiagonal.toString()
                        txtDuhovnayaDiagonal.text = pifagor.duhovnayaDiagonal.toString()

                        txtStolb1.text = pifagor.stolbec1.toString()
                        txtStolb2.text = pifagor.stolbec2.toString()
                        txtStolb3.text = pifagor.stolbec3.toString()

                        txtStroka1.text = pifagor.stroka1.toString()
                        txtStroka2.text = pifagor.stroka2.toString()
                        txtStroka3.text = pifagor.stroka3.toString()

                        txtVoploshenie.text = pifagor.nomerVoplosheniya.toString()
                        txtChislo1.text = pifagor.chislo1.toString()
                        txtChislo2.text = pifagor.chislo2.toString()
                        txtChislo3.text = pifagor.chislo3.toString()
                        txtChislo4.text = pifagor.chislo4.toString()


                        btnSave.isEnabled = true
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        if (txtBirthDay.hasFocus()) imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)

                    }
                    //Меняем подписи текста и кнопок на стандартные, если дата неправильная
                } else {
                    btn1.text = "1"
                    btn2.text = "2"
                    btn3.text = "3"
                    btn4.text = "4"
                    btn5.text = "5"
                    btn6.text = "6"
                    btn7.text = "7"
                    btn8.text = "8"
                    btn9.text = "9"
                    btnSave.isEnabled = false
                    txtText.text = ""
                    txtStolb1.text = "-"
                    txtStolb2.text = "-"
                    txtStolb3.text = "-"
                    txtStroka1.text = "-"
                    txtStroka2.text = "-"
                    txtStroka3.text = "-"
                    txtDuhovnayaDiagonal.text = "-"
                    txtPlotskayaDiagonal.text = "-"
                    txtVoploshenie.text = "-"
                    txtChislo1.text = "-"
                    txtChislo2.text = "-"
                    txtChislo3.text = "-"
                    txtChislo4.text = "-"

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


    //Обрабатывает клики по TextView
    fun txtClick(view: View) {
        val txtView = view as TextView
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        //Убираем клавиатуру если полее воода потеряло фокус (при нажатии на любую кнопку)
        if (txtBirthDay.hasFocus()) imm.hideSoftInputFromWindow(window.currentFocus!!.windowToken, 0)

        txtBirthDay.clearFocus()
        txtText.scrollTo(0, 0)

        //Вывод описания в зависимости от кликнутого текстового поля
        when (txtView.id) {
            R.id.txtStolb1 -> txtText.text = getText(R.string.stolb1)
            R.id.txtStolb2 -> txtText.text = getText(R.string.stolb2)
            R.id.txtStolb3 -> txtText.text = getText(R.string.stolb3)
            R.id.txtStroka1 -> txtText.text = getText(R.string.str1)
            R.id.txtStroka2 -> txtText.text = getText(R.string.str2)
            R.id.txtStroka3 -> txtText.text = getText(R.string.str3)
            R.id.txtDuhovnayaDiagonal -> txtText.text = getText(R.string.diag1)
            R.id.txtPlotskayaDiagonal -> txtText.text = getText(R.string.diag2)
            R.id.textView11 -> txtText.text = getText(R.string.vopl)
            R.id.textView12 -> txtText.text = getText(R.string.chislo1)
            R.id.textView13 -> txtText.text = getText(R.string.chislo2)
            R.id.textView14 -> txtText.text = getText(R.string.chislo34)
            R.id.textView15 -> txtText.text =
                "Описание третьего и четвертого рабочих чисел \n" + getText(R.string.chislo34)
            R.id.txtVoploshenie -> txtText.text = getText(R.string.vopl)
            R.id.txtChislo1 -> txtText.text = getText(R.string.chislo1)
            R.id.txtChislo2 -> txtText.text = getText(R.string.chislo2)
            R.id.txtChislo3 -> txtText.text = getText(R.string.chislo34)
            R.id.txtChislo4 -> txtText.text =
                "Описание третьего и четвертого рабочих чисел \n" + getText(R.string.chislo34)
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Обрабатываем сохранение имени и даты рождения
    fun btnSaveClick(view: View) {
        msgEnterText("Сохранить", "Введите имя для: " + txtBirthDay.text.toString())

    }

    fun btnInterestingDates(view: View) {
        msgLoadList(arrayOf("a -> 26061979", "b -> 26121982", "c -> 20091984", "d -> 25092013", "e -> 05021957"))
    }

    fun btnLoadClick(view: View) {
        var strArr: List<String> = ArrayList()
        listOfDates = openFile()
        strArr = listOfDates.split("\n")
        msgLoadList(strArr.toTypedArray())

    }


    // Метод для открытия файла
    private fun openFile(): String {
        try {
            val file = File(this.filesDir, fileName)
            return (file.readText()) // Read file

        } catch (t: Throwable) {
            Toast.makeText(applicationContext, "Exception: $t", Toast.LENGTH_LONG).show()
        }
        return ""
    }


    // Метод для сохранения файла
    private fun saveFile(string: String) {

        try {
            this.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(string.toByteArray())
            }
        } catch (t: Throwable) {
            Toast.makeText(applicationContext, "Exception: $t", Toast.LENGTH_LONG).show()
        }
    }


    // показать сообщение о программе
    private fun msgAbout() {
        val msg = AlertDialog.Builder(this)
        msg.setTitle("О программе").setMessage(R.string.About).setCancelable(false).setNegativeButton("ОК") { dialog, _ -> dialog.cancel() }
        msg.create().show()
    }


    private fun msgLoadList(list: Array<String>) {
        val positiveButtonClick = { _: DialogInterface, _: Int -> Toast.makeText(applicationContext, android.R.string.no, Toast.LENGTH_SHORT).show() }
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Выбирите имя").setItems(list) { _, which ->
            val lstItem = list[which].split(" -> ")
            txtBirthDay.setText(lstItem[1])
            //   Toast.makeText(applicationContext, lstItem[1], Toast.LENGTH_SHORT).show()
        }

        builder.setPositiveButton("Отмена", positiveButtonClick)
        builder.show()
    }


    private fun msgEnterText(txtTitle: String, txtHint: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(txtTitle)
        // Поле ввода в сообщении
        val input = EditText(this)
        // Параметры сообщения
        input.inputType = InputType.TYPE_CLASS_TEXT
        input.hint = txtHint
        builder.setView(input)
        // Дейсвия в зависимости от нажатой кнопки в сообщении
        builder.setPositiveButton("Сохранить") { _, _ ->
            msgText = input.text.toString().replace(" -> ", "")

            saveFile(openFile() + "\n" + msgText + " -> " + txtBirthDay.text.toString())
            Toast.makeText(this, "Дата сохранена", Toast.LENGTH_SHORT).show()

            Toast.makeText(applicationContext, msgText + " -> " + txtBirthDay.text.toString(), Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Отмена") { _: DialogInterface, _: Int -> Toast.makeText(applicationContext, android.R.string.no, Toast.LENGTH_SHORT).show() }
        builder.show()
    }

    fun editSavedDates(view: View) {



    }


}
