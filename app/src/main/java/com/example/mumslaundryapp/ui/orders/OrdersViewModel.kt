package com.example.mumslaundryapp.ui.orders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mumslaundryapp.common.isNumeric
import com.example.mumslaundryapp.data.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(private val firestore: FirebaseFirestore,private val auth: FirebaseAuth): ViewModel() {
    private val _state = mutableStateOf(OrderStates())
    val state: State<OrderStates> = _state

    init {
        getOrders()
        auth.currentUser?.email?.let { checkAdmin(it) }
    }


    fun addOrder(order: Order)
    {

        _state.value=_state.value.copy(isLoading = true)

      if(!isNumeric( order.machine)) {
          _state.value = _state.value.copy(isLoading = false, error = "Invalid Machine Type")
          return
      }
        if(!isNumeric( order.load)){
            _state.value=_state.value.copy(isLoading = false, error="Inavlid Load")
        return
    }
        if(!isNumeric( order.discount)){
            _state.value=_state.value.copy(isLoading = false, error="Inavlid Discount")
            return
    }
        if(!isNumeric( order.extraCost)){
            _state.value=_state.value.copy(isLoading = false, error="Inavlid Extra Cost")
        return
    }
        if(!isNumeric( order.total.toString())){
            _state.value=_state.value.copy(isLoading = false, error="Inavlid Total Cost")
            return
        }


        firestore.collection("orders_workers").add(order).addOnSuccessListener {
            _state.value=_state.value.copy(isLoading = false, additionSuccess = true)
            getOrders()
        }.addOnFailureListener {
            _state.value= _state.value.copy(isLoading = false, additionSuccess = false, error = it.message.toString())
        }

    }
    fun getOrders()
    {
        _state.value=_state.value.copy(
            isLoading = true,
        )
        firestore.collection("orders_workers").orderBy("timestamp", Query.Direction.DESCENDING).get()
            .addOnSuccessListener { snapshot ->
                var orders=ArrayList<Order>()
            for (document in snapshot.documents) {
                document.toObject(Order::class.java)?.let {
                    orders.add(
                      it)

            }

                //             👆
            }
                _state.value= _state.value.copy(
                    isLoading = false,
                    data = orders, error = "Load Successfull"
                )
        }.addOnFailureListener {
                _state.value= _state.value.copy(
                    isLoading = false,
                    error = it.message.toString()
                )
            }

    }
    fun checkAdmin(userid:String)
    {
        _state.value=_state.value.copy(
            isLoading = true,
        )
        
             firestore.collection("admin").whereEqualTo("userid",userid).get().addOnSuccessListener {
                 if(it.documents.isEmpty())
                 {
                     _state.value=_state.value.copy(isLoading = false, isAdmin = false)
                 }
                 else{
                     _state.value=_state.value.copy(isLoading = false, isAdmin = true)
                 }
             }.addOnFailureListener {
                 _state.value=_state.value.copy(isLoading = false, isAdmin = false, error = it.message.toString())
             }
    }
    fun singout()
    {
        _state.value=_state.value.copy(isLoading = true)
        auth.signOut()
        _state.value=_state.value.copy(isLoading = false)

    }
}