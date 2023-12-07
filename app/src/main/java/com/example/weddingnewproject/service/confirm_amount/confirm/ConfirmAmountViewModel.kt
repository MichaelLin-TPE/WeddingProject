package com.example.weddingnewproject.service.confirm_amount.confirm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weddingnewproject.Tool
import com.example.weddingnewproject.bean.CustomerData
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.firebase.FirebaseRepository

class ConfirmAmountViewModel : ViewModel() {

    private val firebaseRepository = FirebaseRepository()

    private val customerList = mutableListOf<CustomerData>()
    val updateDataLiveData = MutableLiveData<Int>()

    fun onSendDataClickListener(data: CustomerListData) {

        for (customer in customerList){
            for (listData in customer.customer_list){
                if (data.name == listData.name){
                    listData.amount = data.amount
                    listData.isNeedCake = data.isNeedCake
                    listData.type = data.type
                    listData.cakeCount = data.cakeCount
                    listData.isAlreadyShow = true
                }
            }
        }
        val json = Tool.toJson(customerList)
        Log.i("Michael","更新完後的Json $json")
        firebaseRepository.updateCustomerList(json,data.type){
            updateDataLiveData.postValue(data.type)
        }
    }

    fun onViewCreated(data: CustomerListData) {
        firebaseRepository.getCustomList(data.type){

            customerList.addAll(it)


        }
    }


}