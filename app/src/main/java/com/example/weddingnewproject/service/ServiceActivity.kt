package com.example.weddingnewproject.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.weddingnewproject.R
import com.example.weddingnewproject.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_service)

        binding.back.setOnClickListener {
            finish()
        }

        binding.buttonBg.setOnClickListener {

        }

        binding.buttonBg2.setOnClickListener {

        }


    }
}