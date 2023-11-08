package com.example.mumslaundryapp.ui.Login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.mumslaundryapp.ui.theme.MumsLaundryAppTheme
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current

            MumsLaundryAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                    ,


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
                           )


                   )

                   {
                       Card(
                           colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                           shape=RoundedCornerShape(50,50,0,0),
                           modifier = Modifier
                               .fillMaxWidth()
                               .aspectRatio(1f)
                               .padding(32.dp)
                               .align(Alignment.Center)
                           ,
                           border = BorderStroke(2.dp,Color.White)
                       ) {


                       Image(  painterResource(id = R.drawable.store), contentDescription ="bg",
                          modifier = Modifier
                              .fillMaxSize()
                              .padding(12.dp, 16.dp, 12.dp, 12.dp)
                              .background(Color.Transparent)
                              .clip( RoundedCornerShape(50,50,0,0),
                              )
                       )


                   }
                       Column(
                           modifier = Modifier.padding(0.dp,32.dp,0.dp,0.dp)
                       ) {
                           Image(  painterResource(id = R.drawable.mums), contentDescription ="bg",
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .background(Color.Transparent)
                           )
                           Image(  painterResource(id = R.drawable.laundry), contentDescription ="bg",
                               modifier = Modifier
                                   .fillMaxWidth()

                                   .background(Color.Transparent)
                           )
                       }
                       Card(
                           colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(108.dp, 32.dp, 108.dp, 0.dp)
                               .align(Alignment.TopCenter)
                           ,
                       ) {


                           Image(  painterResource(id = R.drawable.ic_logo), contentDescription ="bg",
                               modifier = Modifier
                                   .aspectRatio(1f)
                                   .background(Color.Transparent)
                           )


                       }
                       Card(shape=RoundedCornerShape(32.dp,32.dp,0.dp,0.dp),
                           colors = CardDefaults.cardColors(containerColor = Color.White),
                           modifier = Modifier
                               .fillMaxWidth()
                               .align(Alignment.BottomCenter)
                           ,
                       ) {


                           Column(horizontalAlignment = Alignment.CenterHorizontally,
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(32.dp, 16.dp, 32.dp, 0.dp)) {


                          Text(text = "Get your laundry within \n" +
                                  "24 hours", style = TextStyle(
                              fontSize = 20.sp,
                              fontWeight = FontWeight.SemiBold,
                              textAlign = TextAlign.Center
                          ))

                               Text(text = "A convenient laundry solution that helps\n" +
                                       "protect the environment.", style = TextStyle(
                                   fontSize = 16.sp,
                                           textAlign = TextAlign.Center,
                                           color = Color.Black

                               ),
                                   modifier = Modifier.padding(0.dp,16.dp,0.dp,0.dp))

                               Box(modifier = Modifier.padding(0.dp, 16.dp)) {
                                   Box(
                                       Modifier
                                           .fillMaxWidth()
                                           .height(60.dp)
                                           .clip(shape = RoundedCornerShape(percent = 50))
                                           .clickable {
                                                   context.startActivity(Intent(context, SignUpActivity::class.java))
                                           }


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
                                               ),
                                           horizontalArrangement = Arrangement.Center,
                                           verticalAlignment = Alignment.CenterVertically

                                       ) {
                                           Text(
                                               text = "Let's Start ",

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

                               Row( Modifier
                                   .fillMaxWidth()
                                   .padding(0.dp,0.dp,0.dp,8.dp)
                                   ,
                                   horizontalArrangement = Arrangement.Center,
                                   verticalAlignment = Alignment.CenterVertically
                               ) {
                                   Text(
                                       text = "Already have account ?"
                                             , style = TextStyle(
                                           fontSize = 16.sp,
                                           textAlign = TextAlign.Center,
                                           color = Color.Black

                                       )
                                   )
                                   Text(
                                       text = "  Sign In"
                                       , style = TextStyle(
                                           fontSize = 16.sp,
                                           textAlign = TextAlign.Center,
                                           color = Color(0xFF00ADBB)

                                       )
                                       ,
                                       modifier = Modifier.clickable {
                                           context.startActivity(Intent(context, LoginActivity::class.java))
                                       }
                                   )
                               }
                           }
                       }
                   }


                }
            }
        }
    }
}
