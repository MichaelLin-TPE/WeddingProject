package com.example.weddingnewproject.service.confirm_amount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weddingnewproject.firebase.FirebaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FinalConfirmViewModel : ViewModel() {

    private val firebaseRepository = FirebaseRepository()

    val backToWaitingPageLiveData = MutableLiveData<Boolean>()

    fun onViewCreated(type: Int?) {
        firebaseRepository.clearCustomerData(type)
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            firebaseRepository.onSendAction(type!!,"backToCustomerSelection")
            backToWaitingPageLiveData.postValue(true)
        }
    }

}