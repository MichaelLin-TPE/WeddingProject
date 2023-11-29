package com.example.weddingnewproject.firebase

import android.util.Log
import com.example.weddingnewproject.bean.CustomerData
import com.example.weddingnewproject.bean.CustomerListData
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

    fun onDetectCustomer(type: Int,customerInfoCallBack: customerInfoCallBack) {
        this.type = type
        val docRef = db.collection(if (type == 0) "michael_service_site" else "joyce_service_site").document("customer")
        docRef.addSnapshotListener { value, error ->

            if (error != null){
                return@addSnapshotListener
            }
            if (value != null && value.exists()){
                val json = value.getString("customer_info")
                if (json.isNullOrEmpty()){
                    customerInfoCallBack(null)
                    return@addSnapshotListener
                }
                val data : CustomerListData = Gson().fromJson(json,CustomerListData::class.java)
                Log.i("Michael","監聽到資料 : ${Gson().toJson(data)}")
                customerInfoCallBack(data)
            }

        }
    }

    fun onDeleteData(callback: ()->Unit) {
        db.collection(if (type == 0) "michael_service_site" else "joyce_service_site")
            .document("customer")
            .update("customer_info",FieldValue.delete())
            .addOnSuccessListener {
                callback()
            }
            .addOnFailureListener {

            }

    }

    fun onSendAction(action: String) {
        val hashMap = hashMapOf(
            "action" to action
        )
        db.collection("action")
            .document(if (type == 0) "michael_customer" else "joyce_customer")
            .set(hashMap)
    }

    fun onDetectAction(type: Int, detectAction: detectAction) {
        val docRef = db.collection("action").document(if (type == 0) "michael_customer" else "joyce_customer")
        docRef.addSnapshotListener { value, error ->

            if (error != null){
                return@addSnapshotListener
            }
            if (value != null && value.exists()){
                val action = value.getString("action")
                if (action.isNullOrEmpty()){
                    detectAction("")
                    return@addSnapshotListener
                }
                detectAction(action)

            }

        }
    }

}
typealias detectAction = (String) ->Unit
typealias customerInfoCallBack = (CustomerListData?) ->Unit
typealias customerCallBack = (ArrayList<CustomerData>) -> Unit