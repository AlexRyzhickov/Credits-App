package com.atex.creditcalculator.data

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

class AnnuityCredit(private val sum: Double, private val months: Int, private val procent: Double) {

    val monthlyProcent: Double = procent / 12 / 100
    var monthlyPayment: Double = round(
        sum * monthlyProcent * (1 + monthlyProcent).pow(months) / ((1 + monthlyProcent).pow(
            months
        ) - 1)
    )
    val overPayment: Double = round(monthlyPayment * months - sum)

    fun getData(): ArrayList<CreditData> {

        val calendar: Calendar = GregorianCalendar()
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        val list = ArrayList<CreditData>()
        var remain = sum
        for (i in 1..months) {
            val interestPayment = round(remain * monthlyProcent)
            val principalDebtPayment = monthlyPayment - interestPayment
            remain -= principalDebtPayment
            if (i != months) {
                list.add(CreditData(dateFormat.format(calendar.time), monthlyPayment, interestPayment, principalDebtPayment, round(remain)))
            } else {
                list.add(CreditData(dateFormat.format(calendar.time), monthlyPayment, interestPayment, list[months - 2].remain, 0.0))
            }
            calendar.add(Calendar.MONTH, 1)
        }
        return list
    }

    private fun round(number: Double): Double {
        return Math.round(number * 100.0) / 100.0
    }

}