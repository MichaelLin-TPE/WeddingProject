package com.example.weddingnewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CustomerSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_selection)
        val type = intent.extras?.getInt("type",0)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,if (type == 0) CustomerSelectionMaleFragment.newInstance() else CustomerSelectionFemaleFragment.newInstance())
        transaction.commit()

    }
}