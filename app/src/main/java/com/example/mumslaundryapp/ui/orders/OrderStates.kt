package com.example.mumslaundryapp.ui.orders

import com.example.mumslaundryapp.data.Order

data class OrderStates(
val isLoading: Boolean = false,
val error: String = "",
    val additionSuccess:Boolean=false,
    val data:ArrayList<Order> = ArrayList(),
    val isAdmin:Boolean=false

)

