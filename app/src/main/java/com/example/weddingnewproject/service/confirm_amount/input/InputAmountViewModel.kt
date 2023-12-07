package com.example.weddingnewproject.service.confirm_amount.input

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.firebase.FirebaseRepository

class InputAmountViewModel : ViewModel() {

    val needCakeLiveData = MutableLiveData<Boolean>()
    val nameLiveData = MutableLiveData<String>()
    val backLiveData = MutableLiveData<Boolean>()
    val goConfirmPageLiveData = MutableLiveData<CustomerListData>()
    private var infoData : CustomerListData? = null
    private val firebaseRepository = FirebaseRepository()

    fun onViewCreated(infoData: CustomerListData) {
        this.infoData = infoData
        needCakeLiveData.postValue(infoData.isNeedCake)
        nameLiveData.postValue(infoData.name)
    }

    fun onSendButtonClickListener(amount: String, cakeCount: String) {
        infoData?.let {
            it.amount = amount.toInt()
            it.isNeedCake = cakeCount.toInt() > 0
            it.cakeCount = cakeCount.toInt()
            goConfirmPageLiveData.postValue(it)
        }

    }

    fun onBackPressedListener() {
        infoData?.let {
            firebaseRepository.onDeleteData(it.type){
                backLiveData.postValue(true)
            }
            firebaseRepository.onSendAction(it.type,"back")
        }
    }

}