package com.atex.creditcalculator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atex.creditcalculator.R
import com.atex.creditcalculator.data.CreditData

class PayInformAdapter(
    private var creditsData: List<CreditData>,
) :
    RecyclerView.Adapter<PayInformAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.payment_inform_layout,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = creditsData[position]
        holder.date.text = currentItem.date
        holder.monthlyPayment.text = currentItem.monthlyPayment.toString()
        holder.interestPayment.text = currentItem.interestPayment.toString()
        holder.principalDebtPayment.text = currentItem.principalDebtPayment.toString()
        holder.remain.text = currentItem.remain.toString()
    }

    override fun getItemCount(): Int {
        return creditsData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var date: TextView
        var monthlyPayment: TextView
        var interestPayment: TextView
        val principalDebtPayment: TextView
        val remain: TextView

        init {
            date = itemView.findViewById(R.id.pay_date)
            monthlyPayment = itemView.findViewById(R.id.monthly_payment)
            interestPayment = itemView.findViewById(R.id.interest_payment)
            principalDebtPayment = itemView.findViewById(R.id.principal_debt_payment)
            remain = itemView.findViewById(R.id.remain_payment)
        }

    }

}