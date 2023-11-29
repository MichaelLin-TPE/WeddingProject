package com.example.weddingnewproject.service.confirm_amount

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
import com.example.weddingnewproject.service.waiting_page.WaitingViewModel


class ConfirmAmountFragment : Fragment() {

    private var _binding:FragmentConfirmAmountBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ConfirmAmountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_confirm_amount,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ConfirmAmountViewModel::class.java]
        onHandleLiveData()
        initView()
        val infoData : CustomerListData = arguments?.getSerializable("data") as CustomerListData
        viewModel.onViewCreated(infoData)

    }

    private fun initView() {
        binding.send.setOnClickListener {
            val amount = binding.editAmount.text.toString()
            val cakeCount = binding.editCakeAmount.text.toString()
            viewModel.onSendButtonClickListener(amount,cakeCount)
        }
        binding.back.setOnClickListener {
            viewModel.onBackPressedListener()
        }
    }

    private fun onHandleLiveData() {
        viewModel.needCakeLiveData.observe(viewLifecycleOwner){
            binding.needCake.text = if(it) "要發喜餅" else "無提供喜餅"
        }
        viewModel.nameLiveData.observe(viewLifecycleOwner){
            binding.name.text = it
        }
        viewModel.backLiveData.observe(viewLifecycleOwner){
            parentFragmentManager.popBackStack()
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(data : CustomerListData) =
            ConfirmAmountFragment().apply {
                arguments = Bundle().apply {
                   putSerializable("data",data)
                }
            }
    }
}