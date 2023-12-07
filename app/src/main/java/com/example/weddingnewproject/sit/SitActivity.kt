package com.example.weddingnewproject.sit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.weddingnewproject.R
import com.example.weddingnewproject.databinding.ActivitySitBinding

class SitActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivitySitBinding
    private lateinit var viewModel: SitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_sit)
        viewModel = ViewModelProvider(this)[SitViewModel::class.java]
        viewModel.onCreate()
        initView()

    }

    private fun initView() {
        dataBinding.mainTable.setOnClickListener {

        }
    }
}