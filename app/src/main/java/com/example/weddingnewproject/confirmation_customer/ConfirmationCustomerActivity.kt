package com.example.weddingnewproject.confirmation_customer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.weddingnewproject.R
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.databinding.ActivityConfirmationCustomerBinding

class ConfirmationCustomerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfirmationCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_confirmation_customer)
        val data = intent.extras?.getSerializable("data") as CustomerListData
        binding.name.text = data.name
        binding.sit.text = data.sit

    }
}