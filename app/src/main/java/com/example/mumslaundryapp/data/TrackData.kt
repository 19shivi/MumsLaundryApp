package com.example.mumslaundryapp.data

data class TrackData(
    val thisLoad:Long,
    val lastLoad:Long,
    val thisSale:Long,
    val lastSale:Long,
    val existingCustomer:Long,
    val newCustomer:Long,
    val newCustomerLoad:Long,
    val existingCustomerLoad:Long,
    val newCustomerDiscount:Long

)
