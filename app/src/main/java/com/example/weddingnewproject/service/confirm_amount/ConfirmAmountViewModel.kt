package com.example.weddingnewproject.service.confirm_amount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.firebase.FirebaseRepository

class ConfirmAmountViewModel : ViewModel() {

    val needCakeLiveData = MutableLiveData<Boolean>()
    val nameLiveData = MutableLiveData<String>()
    val backLiveData = MutableLiveData<Boolean>()
    private var infoData : CustomerListData? = null
    private val firebaseRepository = FirebaseRepository()

    fun onViewCreated(infoData: CustomerListData) {
        this.infoData = infoData
        needCakeLiveData.postValue(infoData.isNeedCake)
        nameLiveData.postValue(infoData.name)
    }

    fun onSendButtonClickListener(amount: String, cakeCount: String) {

    }

    fun onBackPressedListener() {
        infoData?.let {
            firebaseRepository.onDeleteData{
                backLiveData.postValue(true)
            }
            firebaseRepository.onSendAction("back")
        }
    }

}