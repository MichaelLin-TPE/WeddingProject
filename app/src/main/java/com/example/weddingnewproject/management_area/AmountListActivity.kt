package com.example.weddingnewproject.management_area

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weddingnewproject.R
import com.example.weddingnewproject.databinding.ActivityAmountListBinding
import java.text.DecimalFormat

class AmountListActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityAmountListBinding
    private lateinit var viewModel: AmountListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_amount_list)
        viewModel = ViewModelProvider(this)[AmountListViewModel::class.java]

        viewModel.onCreate()
        handleLiveData()
    }

    private fun handleLiveData() {
        viewModel.showCategoryListLiveData.observe(this){
            val adapter = AmountCategoryAdapter(it)
            dataBinding.amountList.adapter = adapter
        }
        viewModel.showTotalAmountLiveData.observe(this){
            dataBinding.totalAmount.text = "$"+formatNumberWithThousandsSeparator(it)
        }
    }

    private fun formatNumberWithThousandsSeparator(number: Int): String {
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(number)
    }
}