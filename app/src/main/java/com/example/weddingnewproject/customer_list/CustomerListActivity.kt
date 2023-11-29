package com.example.weddingnewproject.customer_list

import android.content.Intent
import android.content.pm.ResolveInfo.DisplayNameComparator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weddingnewproject.R
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.confirmation_customer.ConfirmationCustomerActivity
import com.example.weddingnewproject.databinding.ActivityCustomerListBinding
import java.util.ArrayList

class CustomerListActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCustomerListBinding
    private lateinit var viewModel: CustomerListViewModel
    private lateinit var adapter: CustomerListAdapter
    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_customer_list)
        viewModel = ViewModelProvider(this)[CustomerListViewModel::class.java]

        initView()
        initHandleLiveData()
        type = intent.extras?.getInt("type",0)!!
        val customerType = intent.extras?.getString("customerType","")
        viewModel.onCreate(type,customerType!!)


    }




    private fun initHandleLiveData() {
        viewModel.customerListLiveData.observe(this){
            showCustomerList(it)
        }
        viewModel.updateCustomerListLiveData.observe(this){
            updateCustomerList(it)
        }
        viewModel.showNameConfirmationDialogLiveData.observe(this){
            val dialog = NameConfirmationDialog.newInstance(it)
            dialog.show(supportFragmentManager,"dialog")
            dialog.setOnNameConfirmationCallback {
                viewModel.onNameConfirmationCallback(it)
            }
        }
        viewModel.goNextConfirmPageLiveData.observe(this){
            val intent = Intent(this@CustomerListActivity,ConfirmationCustomerActivity::class.java)
            intent.putExtra("data",it)
            intent.putExtra("type",type)
            startActivity(intent)
        }
    }

    private fun updateCustomerList(it: ArrayList<CustomerListData>) {
        adapter.update(it)
    }

    private fun showCustomerList(customerList: ArrayList<CustomerListData>) {
        adapter = CustomerListAdapter(customerList){ customerListData ->

            viewModel.onCustomerSelectedListener(customerListData)

        }
        binding.customerList.adapter = adapter
        binding.customerList.addItemDecoration(DividerItemDecoration(2))
    }

    private fun initView() {
        binding.back.setOnClickListener {
            finish()
        }
        binding.confirm.setOnClickListener {
            viewModel.onNameConfirmClickListener()
        }
    }
}