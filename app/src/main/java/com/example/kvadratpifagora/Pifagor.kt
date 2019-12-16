package com.example.kvadratpifagora

import kotlin.math.abs

class Pifagor {

    private var cDate: String = ""
    private var cPolnoeChislo: String = ""
    private var cName: String = ""
    var chislo1: Int = 0
    var chislo2: Int = 0
    var chislo3: Int = 0
    var chislo4: Int = 0
    var nomerVoplosheniya: Int = 0

    var edinici: String = ""
    var dvoyki: String = ""
    var troyki: String = ""
    var chetverki: String = ""
    var pyaterki: String = ""
    var shesterki: String = ""
    var semerki: String = ""
    var vosmerki: String = ""
    var devyatki: String = ""


    var stroka1: Int = 0
    var stroka2: Int = 0
    var stroka3: Int = 0

    var stolbec1: Int = 0
    var stolbec2: Int = 0
    var stolbec3: Int = 0

    var plotskayaDiagonal: Int = 0
    var duhovnayaDiagonal: Int = 0


    fun pifCalc(dateString: String) {
        cDate = ""
        cPolnoeChislo = ""
        cName = ""
        chislo1 = 0
        chislo2 = 0
        chislo3 = 0
        chislo4 = 0
        nomerVoplosheniya = 0

        cDate = dateString.replace("0".toRegex(), "")
        for (i in cDate.indices) {
            chislo1 += Integer.parseInt(cDate.substring(i, i + 1))
        }
        for (i in chislo1.toString().indices) {
            chislo2 += Integer.parseInt(chislo1.toString().substring(i, i + 1))
        }
        chislo3 = abs(chislo1 - Integer.parseInt(cDate.substring(0, 1)) * 2)
        for (i in chislo3.toString().indices) {
            chislo4 += Integer.parseInt(chislo3.toString().substring(i, i + 1))
        }


        cPolnoeChislo =
            cDate + chislo1.toString() + chislo2.toString() + chislo3.toString() + chislo4.toString()


        nomerVoplosheniya = cPolnoeChislo.length

        //Заполняем кнопки цифрами
        for (i in 0 until cPolnoeChislo.length - cPolnoeChislo.replace("1".toRegex(), "").length) {
            cName += "1"
        }
        edinici = cName

        cName = ""
        for (i in 0 until cPolnoeChislo.length - cPolnoeChislo.replace("2".toRegex(), "").length) {
            cName += "2"
        }
        dvoyki = cName

        cName = ""
        for (i in 0 until cPolnoeChislo.length - cPolnoeChislo.replace("3".toRegex(), "").length) {
            cName += "3"
        }
        troyki = cName

        cName = ""
        for (i in 0 until cPolnoeChislo.length - cPolnoeChislo.replace("4".toRegex(), "").length) {
            cName += "4"
        }
        chetverki = cName

        cName = ""
        for (i in 0 until cPolnoeChislo.length - cPolnoeChislo.replace("5".toRegex(), "").length) {
            cName += "5"
        }
        pyaterki = cName

        cName = ""
        for (i in 0 until cPolnoeChislo.length - cPolnoeChislo.replace("6".toRegex(), "").length) {
            cName += "6"
        }
        shesterki = cName

        cName = ""
        for (i in 0 until cPolnoeChislo.length - cPolnoeChislo.replace("7".toRegex(), "").length) {
            cName += "7"
        }
        semerki = cName

        cName = ""
        for (i in 0 until cPolnoeChislo.length - cPolnoeChislo.replace("8".toRegex(), "").length) {
            cName += "8"
        }
        vosmerki = cName

        cName = ""
        for (i in 0 until cPolnoeChislo.length - cPolnoeChislo.replace("9".toRegex(), "").length) {
            cName += "9"
        }
        devyatki = cName

        //Расчет строк, столбцов и диагоналей
        stolbec1 = (edinici.length + dvoyki.length + troyki.length)
        stolbec2 = (chetverki.length + pyaterki.length + shesterki.length)
        stolbec3 = (semerki.length + vosmerki.length + devyatki.length)
        stroka1 = (edinici.length + chetverki.length + semerki.length)
        stroka2 = (dvoyki.length + pyaterki.length + vosmerki.length)
        stroka3 = (troyki.length + shesterki.length + devyatki.length)
        duhovnayaDiagonal = (edinici.length + pyaterki.length + devyatki.length)
        plotskayaDiagonal = (troyki.length + pyaterki.length + semerki.length)


    }

}