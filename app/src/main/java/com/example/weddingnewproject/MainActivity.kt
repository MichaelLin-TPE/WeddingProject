package com.example.weddingnewproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.weddingnewproject.customer.CustomerActivity
import com.example.weddingnewproject.databinding.ActivityMainBinding
import com.example.weddingnewproject.management_area.InputPasswordActivity
import com.example.weddingnewproject.service.ServiceActivity
import com.example.weddingnewproject.sit.SitActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.customer.setOnClickListener {
            val intent = Intent(this@MainActivity, CustomerActivity::class.java)
            startActivity(intent)
        }
        binding.service.setOnClickListener {
            val intent = Intent(this@MainActivity, ServiceActivity::class.java)
            startActivity(intent)
        }
        binding.sit.setOnClickListener {
            val intent = Intent(this@MainActivity,SitActivity::class.java)
            startActivity(intent)
        }
        binding.management.setOnClickListener {
            val intent = Intent(this@MainActivity,InputPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}