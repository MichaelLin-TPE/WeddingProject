package com.example.weddingnewproject.firebase

import android.util.Log
import com.example.weddingnewproject.bean.CustomerData
import com.example.weddingnewproject.bean.CustomerListData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.security.auth.callback.Callback

class FirebaseRepository {

    private val db = Firebase.firestore
    private var type = 0

    fun getCustomList(type:Int,customerCallBack: customerCallBack){
        this.type = type
        db.collection(if (type == 0)"michael_customer" else "joyce_customer")
            .document("customer_list")
            .get()
            .addOnSuccessListener {
                if (it != null && it.exists()){
                    val json = it.getString("customer_list")
                    Log.i("Michael","json : $json")
                    val listType = object : TypeToken<ArrayList<CustomerData>>() {}.type
                    val customList : ArrayList<CustomerData> = Gson().fromJson(json,listType)
                    customerCallBack(customList)
                }else{
                    Log.i("Michael","獲取不到")
                }


            }
            .addOnFailureListener {
                Log.i("Michael","fail get customer data : $it")
            }

    }

    fun onSendActionToServiceSite(it: CustomerListData) {
        val json = Gson().toJson(it)
        val customerInfo = hashMapOf(
            "customer_info" to json
        )
        db.collection(if (type == 0) "michael_service_site" else "joyce_server_site").document("customer")
            .set(customerInfo)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
    }

}

typealias customerCallBack = (ArrayList<CustomerData>) -> Unit