package com.example.weddingnewproject.management_area

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weddingnewproject.bean.CategoryData
import com.example.weddingnewproject.bean.CategoryListData
import com.example.weddingnewproject.bean.CustomerData
import com.example.weddingnewproject.bean.CustomerListData
import com.example.weddingnewproject.firebase.FirebaseRepository

class AmountListViewModel : ViewModel() {

    private val db = FirebaseRepository()
    private val categoryList = ArrayList<CategoryData>()
    private var michaelTotalAmount = 0
    private var joyceTotalAmount = 0
    val showCategoryListLiveData = MutableLiveData<ArrayList<CategoryData>>()
    val showTotalAmountLiveData = MutableLiveData<Int>()

    fun onCreate() {

        db.getCustomList(0) { customerList ->
            categoryList.clear()

            for (data in customerList) {
                for (customer in data.customer_list) {
                    michaelTotalAmount += customer.amount
                }
            }

            val dataList = ArrayList<CategoryListData>()
            for (customer in customerList) {
                if (customer.type == "lineage") {
                    val data = CategoryListData("   -- 天堂", getTotalAmount(customer.customer_list))
                    dataList.add(data)
                    continue
                }
                if (customer.type == "college_friend") {
                    val data = CategoryListData("   -- 大學同學", getTotalAmount(customer.customer_list))
                    dataList.add(data)
                    continue
                }
                if (customer.type == "high_friend") {
                    val data = CategoryListData("   -- 國中同學", getTotalAmount(customer.customer_list))
                    dataList.add(data)
                    continue
                }
                if (customer.type == "hotel") {
                    val data = CategoryListData("   -- 儷夏", getTotalAmount(customer.customer_list))
                    dataList.add(data)
                    continue
                }
                if (customer.type == "company") {
                    val data = CategoryListData("   -- 工作同事", getTotalAmount(customer.customer_list))
                    dataList.add(data)
                    continue
                }
                if (customer.type == "family") {
                    val data = CategoryListData("   -- 親戚", getTotalAmount(customer.customer_list))
                    dataList.add(data)
                }

            }
            val categoryData = CategoryData("男方禮金", michaelTotalAmount, dataList)
            categoryList.add(categoryData)

            Log.i("Michael", "michaelAmount : $michaelTotalAmount")
            startToCatchJoyceCustomer()
        }

    }

    private fun getTotalAmount(customerList: java.util.ArrayList<CustomerListData>): Int {
        var amount = 0
        for (data in customerList) {
            amount += data.amount
        }
        return amount
    }

    private fun startToCatchJoyceCustomer() {
        db.getCustomList(1) { customerList ->

            for (data in customerList) {
                for (customer in data.customer_list) {
                    joyceTotalAmount += customer.amount
                }
            }
            val dataList = ArrayList<CategoryListData>()
            for (customer in customerList) {
                if (customer.type == "friend") {
                    val data = CategoryListData("   -- 朋友", getTotalAmount(customer.customer_list))
                    dataList.add(data)
                    continue
                }
                if (customer.type == "family") {
                    val data = CategoryListData("   -- 親戚", getTotalAmount(customer.customer_list))
                    dataList.add(data)
                    continue
                }
                if (customer.type == "brother_sister") {
                    val data = CategoryListData("   -- 家人", getTotalAmount(customer.customer_list))
                    dataList.add(data)
                }

            }
            val categoryData = CategoryData("女方禮金", joyceTotalAmount, dataList)
            categoryList.add(categoryData)
            showCategoryListLiveData.postValue(categoryList)
            showTotalAmountLiveData.postValue(michaelTotalAmount + joyceTotalAmount)
            Log.i("Michael", "joyceTotalAmount : $joyceTotalAmount")
        }
    }




}