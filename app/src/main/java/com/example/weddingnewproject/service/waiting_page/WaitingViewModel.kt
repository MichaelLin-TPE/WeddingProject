package com.example.weddingnewproject.service.waiting_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.firebase.FirebaseRepository

class WaitingViewModel : ViewModel() {

    val customerInfoLiveData = MutableLiveData<CustomerListData?>()

    fun onViewCreated(type: Int) {

        firebaseRepository.onDetectCustomer(type){ infoData->
            customerInfoLiveData.postValue(infoData)
        }
    }

    private val firebaseRepository = FirebaseRepository()



}