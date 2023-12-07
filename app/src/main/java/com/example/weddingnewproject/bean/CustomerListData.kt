package com.example.weddingnewproject.bean

import java.io.Serializable

data class CustomerListData(
    var name:String,
    var sit:String,
    var amount:Int,
    var isNeedCake:Boolean,
    var isAlreadyShow:Boolean,
    var isSelected:Boolean,
    var cakeCount:Int,
    var type : Int
    ): Serializable
