package com.atex.creditcalculator.data

data class CreditData (
    val date: String,
    val monthlyPayment: Double,
    val interestPayment: Double,
    val principalDebtPayment: Double,
    val remain: Double
)
