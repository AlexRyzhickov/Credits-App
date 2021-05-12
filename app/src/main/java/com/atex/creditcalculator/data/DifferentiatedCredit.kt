package com.atex.creditcalculator.data

import com.atex.creditcalculator.data.CreditData
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DifferentiatedCredit(val sum: Double, val months: Int, val procent: Double) {
    val monthlyProcent: Double = procent / 12 / 100
    val principalDebtPayment: Double = round(sum / months)
    val overPayment: Double = 0.0


    fun getData(): ArrayList<CreditData> {

        val calendar: Calendar = GregorianCalendar()
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        val list = ArrayList<CreditData>()
        var remain = sum
        for (i in 1..months) {
            val interestPayment = round(remain * monthlyProcent)
            val monthlyPayment = principalDebtPayment + interestPayment
            remain -= principalDebtPayment
            list.add(CreditData(dateFormat.format(calendar.time),monthlyPayment,interestPayment,principalDebtPayment,remain))
            calendar.add(Calendar.MONTH, 1)
        }
        return list
    }

    private fun round(number: Double): Double {
        return Math.round(number * 100.0) / 100.0
    }
}