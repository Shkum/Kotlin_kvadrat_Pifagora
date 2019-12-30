package com.SAMsoft.kvadratpifagora

import java.text.ParseException
import java.text.SimpleDateFormat

class IsDateExist {

    //Проверка на правильность введенной даты (существует ли она в календаре)
    fun isValidDate(dateString: String): Boolean {
        val df = SimpleDateFormat("dd.MM.yyyy")
        return try {
            df.isLenient = false
            df.parse(dateString)
            true
        } catch (e: ParseException) {
            false
        }
    }

}