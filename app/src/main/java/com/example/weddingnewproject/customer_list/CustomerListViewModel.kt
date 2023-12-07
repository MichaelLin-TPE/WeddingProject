package com.example.weddingnewproject.customer_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weddingnewproject.bean.CustomerData
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.firebase.FirebaseRepository

class CustomerListViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    private val customerList = ArrayList<CustomerListData>()
    val customerListLiveData = MutableLiveData<ArrayList<CustomerListData>>()
    val updateCustomerListLiveData = MutableLiveData<ArrayList<CustomerListData>>()
    val showNameConfirmationDialogLiveData = MutableLiveData<CustomerListData>()
    val goNextConfirmPageLiveData = MutableLiveData<CustomerListData>()
    private var nameConfirmationData : CustomerListData? = null
    private var type = 0
    fun onCreate(type: Int, customerType: String) {
        this.type = type
        repository.getCustomList(type) {
            customerList.clear()
            for (data in it){
                if (data.type == customerType){
                    customerList.addAll(data.customer_list)
                    break
                }
            }
            customerListLiveData.postValue(customerList)
        }
    }

    fun onCustomerSelectedListener(customerListData: CustomerListData) {
        for (data in customerList){
            data.isSelected = data.name == customerListData.name
        }
        updateCustomerListLiveData.postValue(customerList)
        nameConfirmationData = customerListData


    }

    fun onNameConfirmClickListener() {
        nameConfirmationData?.let {
            showNameConfirmationDialogLiveData.postValue(it)
        }
    }

    fun onNameConfirmationCallback(it: CustomerListData) {
        repository.onSendActionToServiceSite(type,it)
        goNextConfirmPageLiveData.postValue(it)
    }

    fun onResume() {
        repository.clearAction(type)
    }
}