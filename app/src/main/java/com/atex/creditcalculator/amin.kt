package com.atex.creditcalculator

import com.atex.creditcalculator.data.DifferentiatedCredit


fun main() {
   /* val credit = AnnuityCredit(300000.0, 12, 10.0)
    println(credit.monthlyPayment)

    println(credit.overPayment)

    for (item in credit.getData()){
//        val df = DecimalFormat("#.##")
//        df.roundingMode = RoundingMode.DOWN
        println(item.toString())
//        println(df.format(item.remain))
    }*/
//    credit.getData()

    val credit = DifferentiatedCredit(300000.0, 12, 10.0)
//    println(credit.monthlyPayment)

//    println(credit.overPayment)

    for (item in credit.getData()){
//        val df = DecimalFormat("#.##")
//        df.roundingMode = RoundingMode.DOWN
        println(item.toString())
//        println(df.format(item.remain))
    }
}