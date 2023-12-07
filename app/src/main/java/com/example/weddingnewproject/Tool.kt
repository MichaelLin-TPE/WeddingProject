package com.example.weddingnewproject

import com.google.gson.Gson

object Tool {

    fun <T>toJson(data:T) :String{
        return Gson().toJson(data)
    }

}