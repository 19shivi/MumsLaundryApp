package com.example.mumslaundryapp.ui.orders

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mumslaundryapp.R
import com.example.mumslaundryapp.common.formatter
import com.example.mumslaundryapp.common.getDate
import com.example.mumslaundryapp.common.getLastMonth
import com.example.mumslaundryapp.common.getLastWeekSunday
import com.example.mumslaundryapp.common.getLastYear
import com.example.mumslaundryapp.common.getThisMonth
import com.example.mumslaundryapp.common.getThisWeekSunday
import com.example.mumslaundryapp.common.getThisYear
import com.example.mumslaundryapp.data.Order
import com.example.mumslaundryapp.ui.LaundryTrack.LaundryTrackActivity
import com.example.mumslaundryapp.ui.Login.SplashActivity
import com.example.mumslaundryapp.ui.common.CustomTextFields
import com.example.mumslaundryapp.ui.common.DropDown
import com.example.mumslaundryapp.ui.theme.MumsLaundryAppTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersActivity : ComponentActivity() {
    private val viewModel: OrdersViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val auth= Firebase.auth

            if(auth.currentUser==null)
            {
                startActivity(
                    Intent(
                        context,
                        SplashActivity::class.java

                    )
                )
                finish()
            }
            val (isLoading,
                error, additionSuccess, data,isAdmin
            ) = viewModel.state.value


            if (error.isNotBlank()) {
                LaunchedEffect(error) { Toast.makeText(context, error, Toast.LENGTH_LONG).show() }
            }
            if (additionSuccess) {
                LaunchedEffect(error) {
                    Toast.makeText(
                        context,
                        "Order Added Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            val showDialog = remember { mutableStateOf(false) }
            if (showDialog.value)
                DialogBox(viewModel, setShowDialog = {
                    showDialog.value = it
                })


            MumsLaundryAppTheme {


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()



                    ) {
                    Box(Modifier.fillMaxSize()) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        listOf(
                                            Color(0xFF00ADBB), Color(0xFF192F47)
                                        )
                                    )
                                )
                        ) {

                            Column(
                                Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),) {
                                Text("Orders", fontSize = 24.sp, fontWeight = FontWeight.Bold,color=Color.White)
                                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                                    items(data) { item ->
                                        Card(
                                            Modifier
                                                .fillMaxWidth()
                                                .padding(8.dp),
                                            border = BorderStroke(1.dp, color = Color.White),
                                            colors = CardDefaults.cardColors(
                                                containerColor = Color.Transparent
                                            )

                                        ) {
                                            Box(
                                                Modifier
                                                    .fillMaxSize()
                                            ) {

                                            }
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Row(
                                                Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp, 0.dp,),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                Text("Machine Type : " + item.machine, color = Color.White)
                                                Text("Load : " + formatter( item.load.toLong())+"Kg", color = Color.White)
                                            }
                                            Row(
                                                Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp, 0.dp,),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {

                                                Text("Discount  : " + item.discount+"%", color = Color.White)
                                                Text("Extra Cost  : ₱ " + formatter(item.extraCost.toLong()), color = Color.White)
                                            }
                                            Row(
                                                Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp, 0.dp,),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {

                                                Text("Total  : ₱ " + formatter( item.total.toLong()), color = Color.White)
                                                Text("Date  : " + getDate( item.timestamp), color = Color.White)
                                            }

                                        }
                                    }
                                }


                            }
                            Column(
                                Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.End
                            ) {
                                Card(
                                    Modifier
                                        .size(84.dp)
                                        .padding(16.dp)
                                        .clickable {
                                            showDialog.value = true
                                        },
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White

                                    )
                                ) {
                                    Box(modifier =Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Rounded.Add,
                                            contentDescription = ""
                                        )
                                    }
                                }

                            }
                            if(isAdmin){
                                Column(
                                    Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Bottom,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Card(
                                        Modifier
                                            .width(108.dp)
                                            .height(84.dp)
                                            .padding(16.dp)
                                            .clickable {
                                               context.startActivity(
                                                   Intent(
                                                   context,LaundryTrackActivity::class.java
                                               )
                                               )
                                            },
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.White

                                        )
                                    ) {
                                        Box(modifier =Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                           Text(text = "Track")
                                        }
                                    }

                                }

                            }
                            Column(
                                Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.End
                            ) {
                                Card(
                                    Modifier
                                        .size(72.dp)
                                        .padding(16.dp)
                                        .clickable {
                                            viewModel.singout()
                                        },
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White

                                    )
                                ) {
                                    Box(modifier =Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                        Icon(
                                           painterResource(id = R.drawable.ic_logout),
                                            contentDescription = ""
                                        )
                                    }
                                }

                            }

                            if (isLoading) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }

                        }


                    }
                }
            }
        }

    }
}

@Composable
fun DialogBox(viewModel: OrdersViewModel, setShowDialog: (Boolean) -> Unit) {
    val machineTypes = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    val discount = arrayOf("0", "10", "25")
    var discountOrder by remember {
        mutableStateOf("0")
    }
    var machineTypeOrder by remember {
        mutableStateOf("1")
    }
    var loadOrder by remember {
        mutableStateOf("0")
    }
    var extraCostOrder by remember {
        mutableStateOf("0")

    }
    var totalCostOrder by remember {
        mutableStateOf("0")

    }
    var newCustomer by remember { mutableStateOf(false) }
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Text(text = "New Customer")

                        Switch(
                            checked = newCustomer,
                            onCheckedChange = {
                                newCustomer = it
                            }
                        )

                    }
                    DropDown(placeholder = "Machine Type", options = machineTypes) {
                        machineTypeOrder = it
                    }
                    CustomTextFields(placeholder = "Load Kg", "0", isNumeric = true) {
                        loadOrder = it
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    DropDown(placeholder = "Discount %", options = discount) {
                        discountOrder = it
                    }
                    CustomTextFields(placeholder = "Extra Cost ₱ ", "0", isNumeric = true) {
                        extraCostOrder = it
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    CustomTextFields("Total Value ₱ ", "0", isNumeric = true) {
                        totalCostOrder = it
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.padding(0.dp, 16.dp)) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .clip(shape = RoundedCornerShape(percent = 50))

                        ) {

                            Row(
                                Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            listOf(
                                                Color(0xFF00ADBB), Color(0xFF192F47)
                                            )
                                        )
                                    )
                                    .clickable {

                                        viewModel.addOrder(
                                            Order(
                                                machine = machineTypeOrder,
                                                load = loadOrder,
                                                discount = discountOrder,
                                                extraCost = extraCostOrder,
                                                timestamp = System.currentTimeMillis(),
                                                total = totalCostOrder.toLong(),
                                                getDate(System.currentTimeMillis()),
                                                newCustomer
                                            )


                                        )

                                        setShowDialog(false)
                                    },
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                Text(
                                    text = "Place Order",

                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_arrow_tilt),
                                    contentDescription = "",
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.White
                                )
                            }

                        }
                    }


                }
            }
        }
    }
}
