package com.example.weddingnewproject.customer_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weddingnewproject.R
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.databinding.ActivityCustomerListBinding
import java.util.ArrayList

class CustomerListActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCustomerListBinding
    private lateinit var viewModel: CustomerListViewModel
    private lateinit var adapter: CustomerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_customer_list)
        viewModel = ViewModelProvider(this)[CustomerListViewModel::class.java]

        initView()
        initHandleLiveData()
        val type = intent.extras?.getInt("type",0)
        val customerType = intent.extras?.getString("customerType","")
        viewModel.onCreate(type!!,customerType!!)


    }

    private fun initHandleLiveData() {
        viewModel.customerListLiveData.observe(this){
            showCustomerList(it)
        }
        viewModel.updateCustomerListLiveData.observe(this){
            updateCustomerList(it)
        }
    }

    private fun updateCustomerList(it: ArrayList<CustomerListData>) {
        adapter.update(it)
    }

    private fun showCustomerList(customerList: ArrayList<CustomerListData>) {
        adapter = CustomerListAdapter(customerList){ customerListData ->

            viewModel.onCustomerSelectedListener(customerListData)
            for (data in customerList){
                data.isSelected = data.name == customerListData.name
            }
        }
        binding.customerList.adapter = adapter
        binding.customerList.addItemDecoration(DividerItemDecoration(2))
    }

    private fun initView() {
        binding.back.setOnClickListener {
            finish()
        }
        binding.confirm.setOnClickListener {

        }
    }
}