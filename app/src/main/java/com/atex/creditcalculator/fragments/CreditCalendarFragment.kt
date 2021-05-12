package com.atex.creditcalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.atex.creditcalculator.R
import com.atex.creditcalculator.adapters.PayInformAdapter
import com.atex.creditcalculator.data.AnnuityCredit
import com.atex.creditcalculator.data.DifferentiatedCredit
import com.atex.creditcalculator.databinding.CreditCalendarFragmentBinding
import com.atex.creditcalculator.enums.CREDIT_TYPE

class CreditCalendarFragment : Fragment(R.layout.credit_calendar_fragment) {


    private var _binding: CreditCalendarFragmentBinding?= null
    private val binding get() = _binding!!
    private val args: CreditCalendarFragmentArgs by navArgs()


    private lateinit var adapter: PayInformAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreditCalendarFragmentBinding.inflate(inflater, container, false)

        val sum = (args.loanAmount - args.firstInstallment).toDouble()
        val months = args.months
        val percent = args.percent.toDouble()
//        val credit = null

        if (args.creditType == CREDIT_TYPE.ANNUITY_CREDIT){
            val credit = AnnuityCredit(sum, months, percent)
            adapter = PayInformAdapter(credit.getData())
            binding.overPayment.text = credit.overPayment.toString()
        }

        if (args.creditType == CREDIT_TYPE.DIFFERENTIATED_CREDIT){
            val credit = DifferentiatedCredit(sum, months, percent)
            adapter = PayInformAdapter(credit.getData())
            binding.overPayment.text = credit.overPayment.toString()
        }

//        adapter = PayInformAdapter(credit.getData())
        binding.paysRecycler.adapter = adapter
        binding.paysRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.credit.text = sum.toString()
//        binding.overPayment.text = credit.overPayment.toString()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}