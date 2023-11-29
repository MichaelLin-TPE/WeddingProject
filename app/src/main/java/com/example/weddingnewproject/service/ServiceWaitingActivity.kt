package com.example.weddingnewproject.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weddingnewproject.R
import com.example.weddingnewproject.service.waiting_page.WaitingFragment

class ServiceWaitingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_waiting)
        val type = intent.extras?.getInt("type",0)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, WaitingFragment.newInstance(type!!))
        transaction.commit()
    }
}