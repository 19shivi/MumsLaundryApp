package com.example.mumslaundryapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mumslaundryapp.R
import com.example.mumslaundryapp.ui.LaundryTrack.LaundryTrackActivity
import com.example.mumslaundryapp.ui.Login.SplashActivity
import com.example.mumslaundryapp.ui.Profit.ProfitActivity
import com.example.mumslaundryapp.ui.common.selectWidget
import com.example.mumslaundryapp.ui.common.serviceTypeWidget
import com.example.mumslaundryapp.ui.orders.OrdersActivity
import com.example.mumslaundryapp.ui.theme.MumsLaundryAppTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current

            val auth=Firebase.auth

            if(auth.currentUser==null)
            {
               startActivity(
                   Intent(
                       context,
                        SplashActivity::class.java

                   )
               )
            }
            var isYesSelected:Boolean by remember {
                mutableStateOf(false)
            }
            var isNoSelected:Boolean by remember {
                mutableStateOf(false)
            }

            MumsLaundryAppTheme {

            }

            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),


                ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp, 0.dp)
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 32.dp, 0.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Image(
                            modifier = Modifier.size(72.dp).clickable {
                                                                      startActivity(Intent(
                                                                          context,ProfitActivity::class.java
                                                                      ))
                            },
                            painter = painterResource(id = R.drawable.ic_logo),
                            contentDescription = ""
                        )
                        Card(
                            modifier = Modifier
                                .size(60.dp)
                                .clickable {
                                    Firebase.auth.signOut()
                                    startActivity(
                                        Intent(
                                            context,
                                            SplashActivity::class.java
                                        )
                                    )
                                },
                            shape = RoundedCornerShape(50),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF00ADBB)
                            )

                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp)
                                    .background(color = Color.Transparent),
                                painter = painterResource(id = R.drawable.ic_notification),
                                alignment = Alignment.Center,
                                contentDescription = ""
                            )
                        }
                    }
                    Row {
                        Text("Hey, ",style = TextStyle(

                            fontSize = 28.sp,
                            color = Color.Black
                        ))
                        Text(
                            auth.currentUser?.displayName.toString(), style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            color = Color.Black
                        )
                        )
                    }
                    Text("Get smart experience" ,style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.Black
                    ))
                    Text("in washing",style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.Black
                    ))

                    Image(painter = painterResource(id = R.drawable.ic_first_order_offer), contentDescription ="",

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 16.dp, 0.dp, 0.dp)
                            )


                    Text("Select Service", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    )

                    Row(modifier = Modifier.fillMaxWidth()){
                        var modifier= Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(12.dp)
                        serviceTypeWidget(
                            R.drawable.ic_dry_service,
                            "Dry",
                           modifier
                        ){

                        }
                        serviceTypeWidget(
                            R.drawable.ic_wash_service,
                            "Wash",
                            modifier
                        ){

                        }
                        serviceTypeWidget(
                            R.drawable.ic_fold_service,
                            "Fold",
                           modifier
                        ){

                        }
                    }
                    Text("Select Pickup Service", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    )

                        Row(Modifier.fillMaxWidth()) {
                            selectWidget(
                                textColor = Color.Black,
                                textColorSelected = Color.White,
                                bgColor = Color(0xFFD8D8D8),
                                bgColorSelected = Color(0xFF00ADBB),
                                isYesSelected

                            ){
                                isYesSelected=it
                            }

                    }
                    Text("Extra Soap", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 16.dp, 8.dp, 0.dp),
                        shape = RoundedCornerShape(16.dp),

                    ) {
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = "20",
                            textStyle = TextStyle(
                                fontSize = 18.sp
                            ),
                            onValueChange = {
                            },
                            enabled=false,

                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(0xFFD8D8D8),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                            ),
                            singleLine = true


                        )
                    }

                }
            }
        }
    }
}
