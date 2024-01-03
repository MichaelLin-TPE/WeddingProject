package com.example.weddingnewproject.management_area

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import com.example.weddingnewproject.R
import com.example.weddingnewproject.databinding.ActivityInputPasswordBinding

class InputPasswordActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityInputPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_input_password)

        dataBinding.passwordInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                if (password == "0624"){
                    goToAmountListPage()
                }
            }
        })

    }

    private fun goToAmountListPage() {
        val it = Intent(this,AmountListActivity::class.java)
        startActivity(it)
    }
}