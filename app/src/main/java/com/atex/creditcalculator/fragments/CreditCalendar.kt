package com.atex.creditcalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atex.creditcalculator.R
import com.atex.creditcalculator.adapters.PayInformAdapter
import com.atex.creditcalculator.data.AnnuityCredit
import com.atex.creditcalculator.data.DifferentiatedCredit
import com.atex.creditcalculator.databinding.CreditCalendarBinding

//import com.atex.databinding.

class CreditCalendar : Fragment(R.layout.credit_calendar) {


    private var _binding: CreditCalendarBinding?= null
    private val binding get() = _binding!!

    private lateinit var adapter: PayInformAdapter
//    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreditCalendarBinding.inflate(inflater, container, false)

        val credit = AnnuityCredit(300000.0, 12, 10.0)

        adapter = PayInformAdapter(credit.getData())
        binding.paysRecycler.adapter = adapter
        binding.paysRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


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