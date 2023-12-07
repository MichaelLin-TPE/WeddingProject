package com.example.weddingnewproject.service.waiting_page

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weddingnewproject.R
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.service.confirm_amount.input.InputAmountFragment


class WaitingFragment : Fragment() {

    private lateinit var viewModel: WaitingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Michael","onCreate")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Michael","onStop")
        viewModel.onStop()
    }

    override fun onResume() {
        super.onResume()
        Log.i("Michael","onResume")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_waiting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[WaitingViewModel::class.java]
        onHandleLiveData()
        val type = arguments?.let {
            it.getInt("type",0)
        }
        viewModel.onViewCreated(type!!)

    }



    private fun onHandleLiveData() {
        viewModel.customerInfoLiveData.observe(viewLifecycleOwner){data->
            Log.i("Michael","goAmountConfirmPage")
            data?.let {
                goAmountConfirmPage(it)
            }
        }
    }

    private fun goAmountConfirmPage(data: CustomerListData) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, InputAmountFragment.newInstance(data))
        transaction?.addToBackStack(null)
        transaction?.commit()
    }


    companion object {
        @JvmStatic
        fun newInstance(type:Int) =
            WaitingFragment().apply {
                arguments = Bundle().apply {
                    putInt("type",type)
                }
            }
    }
}