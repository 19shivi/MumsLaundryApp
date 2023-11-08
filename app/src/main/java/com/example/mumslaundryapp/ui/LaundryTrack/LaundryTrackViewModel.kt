package com.example.mumslaundryapp.ui.LaundryTrack

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mumslaundryapp.common.getLastWeekSunday
import com.example.mumslaundryapp.common.getThisWeekSunday
import com.example.mumslaundryapp.common.isNumeric
import com.example.mumslaundryapp.data.Order
import com.example.mumslaundryapp.data.TrackData
import com.example.mumslaundryapp.ui.orders.OrderStates
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class LaundryTrackViewModel @Inject constructor(private val firestore: FirebaseFirestore): ViewModel(){
    private val _state = mutableStateOf(LaundryTrackStates())
    val state: State<LaundryTrackStates> = _state
init {
    getWeekData(getThisWeekSunday(), getLastWeekSunday())
}
    fun getWeekData(thisTime:Long,lastTime:Long) {
        _state.value= LaundryTrackStates(isLoading = true)
        var thisWeekLoad=0L
        var lastWeekLoad=0L
        var thisSale=0L
        var lastWeekSale=0L
        var newOrder=0L
        var oldOrder=0L
        var newOrderCustomer=0L
        var oldOrderCustomer=0L
        var discount=0L
        firestore.collection("orders_workers")
            .whereGreaterThanOrEqualTo("timestamp", thisTime).get()
            .addOnSuccessListener {

                for (document in it.documents) {
                    document.toObject(Order::class.java)?.let {
                        if (isNumeric(it.load)) {
                            thisWeekLoad = it.load.toLong()
                        }
                        if (isNumeric(it.total.toString())) {
                            thisSale = it.total.toLong()
                        }
                        if (it.newCustomer) {
                            newOrderCustomer++
                            if (isNumeric(it.total.toString())) {
                                discount = (it.total*0.25).toLong()
                            }
                            if (isNumeric(it.load)) {
                                newOrder = it.load.toLong()
                            }
                        } else {
                            oldOrderCustomer++
                            if (isNumeric(it.load)) {
                                oldOrder = it.load.toLong()
                            }
                        }
                    }
                }

                firestore.collection("orders_workers")
                    .whereGreaterThanOrEqualTo("timestamp", lastTime ).whereLessThan("timestamp", thisTime).get()
                    .addOnSuccessListener {

                        for (document in it.documents) {
                            document.toObject(Order::class.java)?.let {
                                if (isNumeric(it.load)) {
                                    lastWeekLoad = it.load.toLong()
                                }
                                if (isNumeric(it.total.toString())) {
                                    lastWeekSale = it.load.toLong()
                                }
                            }
                        }
                        _state.value=LaundryTrackStates(
                            isLoading = false,
                           data= TrackData(
                               thisLoad = thisWeekLoad,
                               lastLoad =lastWeekLoad,
                               thisSale=thisSale,
                               lastSale=lastWeekSale,
                               existingCustomer = oldOrderCustomer,
                               newCustomer = newOrderCustomer,
                               newCustomerLoad = newOrder,
                               existingCustomerLoad = oldOrder,
                               newCustomerDiscount = discount
                           )

                        )
                    }.addOnFailureListener {
                        _state.value=LaundryTrackStates(isLoading = false, error= it.message.toString())
                    }
                            }.addOnFailureListener {
                _state.value=LaundryTrackStates(isLoading = false, error= it.message.toString())
            }


    }
}