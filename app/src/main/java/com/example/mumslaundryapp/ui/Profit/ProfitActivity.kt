package com.example.mumslaundryapp.ui.Profit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.mumslaundryapp.ui.Login.SplashActivity
import com.example.mumslaundryapp.ui.common.TimeSelector
import com.example.mumslaundryapp.ui.common.selectWidget
import com.example.mumslaundryapp.ui.common.serviceTypeWidget
import com.example.mumslaundryapp.ui.theme.MumsLaundryAppTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfitActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current


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

                        Text("Profit",
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

     }
                    }
                }
            }
        }
    }
    }
}
