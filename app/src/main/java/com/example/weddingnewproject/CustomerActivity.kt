package com.example.weddingnewproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.weddingnewproject.databinding.ActivityCustomerBinding

class CustomerActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_customer)
        binding.back.setOnClickListener {
            finish()
        }
        binding.buttonBg.setOnClickListener {
            val intent = Intent(this@CustomerActivity,CustomerSelectionActivity::class.java)
            intent.putExtra("type",0) //0男生
            startActivity(intent)
        }
        binding.buttonBg2.setOnClickListener {
            val intent = Intent(this@CustomerActivity,CustomerSelectionActivity::class.java)
            intent.putExtra("type",1) //1女生
            startActivity(intent)
        }
    }
}