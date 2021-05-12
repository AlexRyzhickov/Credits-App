package com.atex.creditcalculator.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.atex.creditcalculator.R
import com.atex.creditcalculator.databinding.CreditInputFragmentBinding
import com.atex.creditcalculator.enums.CREDIT_TYPE
import com.atex.creditcalculator.enums.InputType

class CreditInputFragment : Fragment(R.layout.credit_input_fragment) {


    private var _binding: CreditInputFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreditInputFragmentBinding.inflate(inflater, container, false)

        val items = listOf("Аннуитетный", "Дифференцированный")
        val adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, items)
        (binding.creditType as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.calculationBtn.setOnClickListener {

            val loanAmountString = binding.loanAmount.text.toString()
            val firstInstallmentString = binding.firstInstallment.text.toString()
            val monthsString = binding.months.text.toString()
            val percentString = binding.percent.text.toString()
            val typeString = binding.creditType.text

            if (loanAmountString.length > 0 && firstInstallmentString.length > 0 && monthsString.length > 0 && percentString.length > 0 && typeString.length > 0 ){
                val loanAmount= loanAmountString.toInt()
                val firstInstallment = firstInstallmentString.toInt()
                val months= monthsString.toInt()
                val percent = percentString.toFloat()
                var type = CREDIT_TYPE.ANNUITY_CREDIT
                if (typeString.toString() == "Аннуитетный"){
                    type = CREDIT_TYPE.ANNUITY_CREDIT
                }else{
                    type = CREDIT_TYPE.DIFFERENTIATED_CREDIT
                }

                if (firstInstallment < loanAmount && months >= 1 && percent > 0.0f) {
                    val action =
                        CreditInputFragmentDirections.actionCreditInputFragmentToCreditCalendar(
                            loanAmount,
                            firstInstallment,
                            percent,
                            months,
                            type,
                            InputType.CREDIT_INPUT
                        )
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(context, "Проверьте данные", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, "Проверьте данные", Toast.LENGTH_SHORT).show()
            }
        }

        binding.openPaymentInputBtn.setOnClickListener {
            val action = CreditInputFragmentDirections.actionCreditInputFragmentToPaymentInputFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        val items = listOf("Аннуитетный", "Дифференцированный")
        val adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, items)
        (binding.creditType as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}