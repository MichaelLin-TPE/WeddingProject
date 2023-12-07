package com.example.weddingnewproject.service.confirm_amount.confirm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weddingnewproject.R
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.databinding.FragmentConfirmAmountBinding
import com.example.weddingnewproject.service.confirm_amount.FinalConfirmFragment


class ConfirmAmountFragment : Fragment() {


    private var _binding : FragmentConfirmAmountBinding? = null;
    private val binding get() = _binding!!

    private lateinit var viewModel: ConfirmAmountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_confirm_amount,container,false)

        // Inflate the layout for this fragment
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ConfirmAmountViewModel::class.java]
        val data = arguments?.getSerializable("data") as CustomerListData
        viewModel.onViewCreated(data)
        initView(data)
        initHandle()
    }

    private fun initHandle() {
        viewModel.updateDataLiveData.observe(viewLifecycleOwner){
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, FinalConfirmFragment.newInstance(it))
            transaction?.commit()
        }
    }

    private fun initView(data: CustomerListData) {

        binding.amount.text = "禮金:$${data.amount}"
        binding.name.text = data.name
        binding.cakeCount.text = "喜餅已給數量:${data.cakeCount}盒"
        binding.send.setOnClickListener {
            viewModel.onSendDataClickListener(data)
        }
        binding.back.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(customerData : CustomerListData) =
            ConfirmAmountFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("data",customerData)
                }
            }
    }
}