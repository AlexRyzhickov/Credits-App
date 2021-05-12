package com.atex.creditcalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.atex.creditcalculator.R
import com.atex.creditcalculator.databinding.CreditInputFragmentBinding

class CreditInputFragment : Fragment(R.layout.credit_input_fragment) {


    private var _binding: CreditInputFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreditInputFragmentBinding.inflate(inflater, container, false)




        binding.calculationBtn.setOnClickListener {

            val loanAmountString = binding.loanAmount.text.toString()
            val firstInstallmentString = binding.firstInstallment.text.toString()
            val monthsString = binding.months.text.toString()
            val percentString = binding.percent.text.toString()

            if (loanAmountString.length > 0 && firstInstallmentString.length > 0 && monthsString.length > 0 && percentString.length > 0) {
                val loanAmount= loanAmountString.toInt()
                val firstInstallment = firstInstallmentString.toInt()
                val months= monthsString.toInt()
                val percent = percentString.toFloat()

                if (firstInstallment < loanAmount && months >= 1 && percent > 0.0f) {
                    val action =
                        CreditInputFragmentDirections.actionCreditInputFragmentToCreditCalendar(
                            loanAmount,
                            firstInstallment,
                            percent,
                            months
                        )
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(context, "Проверьте данные", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, "Проверьте данные", Toast.LENGTH_SHORT).show()
            }
        }

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