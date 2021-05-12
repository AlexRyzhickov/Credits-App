package com.atex.creditcalculator.data

import com.atex.creditcalculator.data.CreditData

class DifferentiatedCredit(val sum: Double, val months: Int, val procent: Double) {
    val monthlyProcent: Double = procent / 12 / 100
    val principalDebtPayment: Double = round(sum / months)

    fun getData(): ArrayList<CreditData> {
        val list = ArrayList<CreditData>()
        var remain = sum
        for (i in 1..months) {
            val interestPayment = round(remain * monthlyProcent)
            val monthlyPayment = principalDebtPayment + interestPayment
            remain -= principalDebtPayment
            list.add(CreditData("",monthlyPayment,interestPayment,principalDebtPayment,remain))
        }
        return list
    }

    private fun round(number: Double): Double {
        return Math.round(number * 100.0) / 100.0
    }
}