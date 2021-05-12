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
import com.atex.creditcalculator.databinding.PaymentInputFragmentBinding
import com.atex.creditcalculator.enums.CREDIT_TYPE

class PaymentInputFragment : Fragment(R.layout.payment_inform_layout) {


    private var _binding: PaymentInputFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PaymentInputFragmentBinding.inflate(inflater, container, false)

        binding.calculationBtn.setOnClickListener {

            val paymentString = binding.payment.text.toString()
            val monthsString = binding.months.text.toString()
            val percentString = binding.percent.text.toString()

            if (paymentString.length > 0 && monthsString.length > 0 && percentString.length > 0) {
                val payment = paymentString.toInt()
                val months= monthsString.toInt()
                val percent = percentString.toFloat()

                if (payment>=0 && months >= 1 && percent > 0.0f) {
                    val action =
                        PaymentInputFragmentDirections.actionPaymentInputFragmentToCreditCalendar(
                            payment * months,
                            0,
                            percent,
                            months,
                            CREDIT_TYPE.ANNUITY_CREDIT
                        )
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(context, "Проверьте данные", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, "Проверьте данные", Toast.LENGTH_SHORT).show()
            }
        }

        binding.openCreditInputBtn.setOnClickListener {
            val action = PaymentInputFragmentDirections.actionPaymentInputFragmentToCreditInputFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}