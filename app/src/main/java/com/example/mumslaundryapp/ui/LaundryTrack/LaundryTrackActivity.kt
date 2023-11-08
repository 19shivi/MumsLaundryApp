package com.example.mumslaundryapp.ui.LaundryTrack

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mumslaundryapp.R
import com.example.mumslaundryapp.common.formatter
import com.example.mumslaundryapp.common.getLastMonth
import com.example.mumslaundryapp.common.getLastWeekSunday
import com.example.mumslaundryapp.common.getLastYear
import com.example.mumslaundryapp.common.getThisMonth
import com.example.mumslaundryapp.common.getThisWeekSunday
import com.example.mumslaundryapp.common.getThisYear
import com.example.mumslaundryapp.ui.Login.LoginViewModel
import com.example.mumslaundryapp.ui.Login.SplashActivity
import com.example.mumslaundryapp.ui.common.LoadTrack
import com.example.mumslaundryapp.ui.common.TimeSelector
import com.example.mumslaundryapp.ui.common.selectWidget
import com.example.mumslaundryapp.ui.common.serviceTypeWidget
import com.example.mumslaundryapp.ui.theme.MumsLaundryAppTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaundryTrackActivity : ComponentActivity() {
    private val viewModel: LaundryTrackViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val ( isLoading,
                error: String,data
            ) = viewModel.state.value
var dataCategory:Int by remember {
    mutableStateOf(0)
}
            if (error.isNotBlank()) {
                LaunchedEffect(error) { Toast.makeText(context, error, Toast.LENGTH_LONG).show() }
            }
            MumsLaundryAppTheme {



            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),


                ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color(0xFF00ADBB), Color(0xFF192F47)
                                )
                            )
                        )) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp, 0.dp)
                            .fillMaxSize()
                    ) {
                        Box {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 32.dp, 0.dp, 0.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            )
                            {
                                Image(
                                    modifier = Modifier.size(72.dp),
                                    painter = painterResource(id = R.drawable.ic_logo),
                                    contentDescription = ""
                                )
                            }

                        Text("Laundry Track",
                            style= TextStyle(
                                fontSize = 24.sp,
                                color= Color.White,
                                fontWeight = FontWeight.Bold
                            ),

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 56.dp, 0.dp, 0.dp),
                            textAlign = TextAlign.Center

                        )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

     TimeSelector(default = "week"){
dataCategory=it
         if(it==0)
             viewModel.getWeekData(getThisWeekSunday(), getLastWeekSunday())
         else if(it==1)
             viewModel.getWeekData(getThisMonth(), getLastMonth())
         else
             viewModel.getWeekData(getThisYear(), getLastYear())
     }
                        Spacer(modifier = Modifier.height(16.dp))
                        LoadTrack(title = if(dataCategory==0)"This Week"
                            else if(dataCategory==1)
                            "This Month"
                                else
                                    "This Year", load =data.thisLoad , sale =data.thisSale )
                        Spacer(modifier = Modifier.height(16.dp))
                        LoadTrack(title = if(dataCategory==0)"Last Week"
                        else if(dataCategory==1)
                            "Last Month"
                        else
                            "Last Year", load =data.lastLoad , sale =data.lastSale )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(Modifier.fillMaxWidth())
                        {
                            Card(
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .height(84.dp)
                                    .padding(4.dp, 0.dp, 4.dp, 0.dp), colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF2CD1E2)
                                    ), shape = RoundedCornerShape(16.dp)
                            )
                            {
                                Column (modifier = Modifier.fillMaxWidth()){
                                    Text("Existing Customer", style = TextStyle(
                                        color =  Color(0xFF154359), fontSize = 16.sp
                                    ), textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth()
                                    )
                                    Text(data.existingCustomer.toString(),style = TextStyle(
                                        color =  Color(0xFF154359), fontSize = 28.sp
                                    ), textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth())
                                    Text("Loads: "+data.existingCustomerLoad.toString(),style = TextStyle(
                                        color =  Color(0xFF154359), fontSize = 16.sp
                                    ), textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth())
                                }
                            }
                            Card(
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .height(84.dp)
                                    .padding(4.dp, 0.dp, 4.dp, 0.dp), colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFF2CD1E2)
                                ), shape = RoundedCornerShape(16.dp)
                            )
                            {
                                Column (modifier = Modifier.fillMaxWidth()){
                                    Text("New Customer", style = TextStyle(
                                        color =  Color(0xFF154359), fontSize = 16.sp
                                    ), textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth()
                                    )
                                    Text(data.newCustomer.toString(),style = TextStyle(
                                        color =  Color(0xFF154359), fontSize = 28.sp
                                    ), textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth())
                                    Text("Loads: "+data.newCustomerLoad,style = TextStyle(
                                        color =  Color(0xFF154359), fontSize = 16.sp
                                    ), textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth())
                                }
                            }

                        }
                        Spacer(modifier = Modifier
                            .padding(0.dp, 16.dp, 0.dp, 0.dp)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(
                                Color.White
                            ))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("New Customer Discount", style= TextStyle(
                            color = Color.White, fontSize = 18.sp
                        ))
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                            colors=CardDefaults.cardColors(
                                containerColor = Color(0xFF2CD1E2)
                            )){
                            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("Value", modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 4.dp), textAlign = TextAlign.Center,style= TextStyle(
                                    color = Color(0xFF154359),
                                            fontSize = 18.sp
                                )
                                )
                                Text("₱ "+ formatter( data.newCustomerDiscount),modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 4.dp), textAlign = TextAlign.Center,style= TextStyle(
                                    color = Color.White,
                                    fontSize = 32.sp
                                ))
                                Text("Discount 25% OFF",modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 4.dp), textAlign = TextAlign.Center,style= TextStyle(
                                    color = Color(0xFF154359), fontSize = 18.sp
                                ))

                            }
                        }
                    }
                    if(isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
    }
}
