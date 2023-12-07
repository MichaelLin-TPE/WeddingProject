package com.example.weddingnewproject.confirmation_customer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weddingnewproject.R
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.customer.CustomerSelectionActivity
import com.example.weddingnewproject.databinding.ActivityConfirmationCustomerBinding

class ConfirmationCustomerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfirmationCustomerBinding
    private lateinit var viewModel: ConfirmationCustomerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_confirmation_customer)
        val data = intent.extras?.getSerializable("data") as CustomerListData
        binding.name.text = data.name
        binding.sit.text = data.sit

        viewModel = ViewModelProvider(this)[ConfirmationCustomerViewModel::class.java]

        onHandleLiveData()
        val type = intent.extras?.getInt("type",0)
        viewModel.onCreate(type!!)


    }

    private fun onHandleLiveData() {
        viewModel.backLiveData.observe(this){
            finish()
        }
        viewModel.backToCustomerSelectionPageLiveData.observe(this){
            val intent = Intent(this@ConfirmationCustomerActivity,CustomerSelectionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }

    override fun onBackPressed() {

    }
}