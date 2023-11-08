package com.example.mumslaundryapp.data

import java.sql.Timestamp

data class Order (
    val machine:String="",
    val load:String="",
    val discount:String="",
    val extraCost:String="",
    val timestamp: Long=0,
    val total: Long=0,
    val date:String="",
    val newCustomer:Boolean=false,
)