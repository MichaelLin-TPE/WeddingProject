package com.example.weddingnewproject.confirmation_customer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weddingnewproject.firebase.FirebaseRepository

class ConfirmationCustomerViewModel : ViewModel() {

    val backLiveData = MutableLiveData<Boolean>()
    private val firebaseRepository = FirebaseRepository()
    val backToCustomerSelectionPageLiveData = MutableLiveData<Boolean>()

    fun onCreate(type: Int) {
        firebaseRepository.onDetectAction(type) {action->
            when(action){
                "back"->{
                    backLiveData.postValue(true)
                }
                "backToCustomerSelection"->{
                    backToCustomerSelectionPageLiveData.postValue(true)
                }
            }
        }
    }

}