package com.example.weddingnewproject.bean

import java.io.Serializable

data class CustomerListData(
    val name:String,
    val sit:String,
    val amount:Int,
    val isNeedCake:Boolean,
    val isAlreadyShow:Boolean,
    var isSelected:Boolean
    ): Serializable
