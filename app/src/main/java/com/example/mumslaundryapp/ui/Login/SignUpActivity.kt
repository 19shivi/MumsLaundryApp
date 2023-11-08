package com.example.mumslaundryapp.ui.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mumslaundryapp.ui.home.HomeActivity
import com.example.mumslaundryapp.R
import com.example.mumslaundryapp.common.isValidEmail
import com.example.mumslaundryapp.common.matchesString
import com.example.mumslaundryapp.common.showToastMessage
import com.example.mumslaundryapp.ui.common.CustomPasswordFields
import com.example.mumslaundryapp.ui.common.CustomTextFields
import com.example.mumslaundryapp.ui.theme.MumsLaundryAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current

            val ( isLoading,
                isEmailVerified,
                isLoginSuccess,
                isSignupSuccess,
                error: String
            ) = viewModel.state.value
            var userName:String by remember {
                mutableStateOf("")
            }
            var userEmail:String by remember {
                mutableStateOf("")
            }
            var userPassword:String by remember {
                mutableStateOf("")
            }
            var userConfirmPassword:String by remember {
                mutableStateOf("")
            }
            if (error.isNotBlank()) {
                LaunchedEffect(error) { Toast.makeText(context, error, Toast.LENGTH_LONG).show() }
            }
            if(isSignupSuccess)
            {
                context.startActivity(Intent(context,LoginActivity::class.java))
                finish()
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
                            )


                    )

                    {
                        Column(
                            modifier = Modifier.padding(0.dp, 32.dp, 0.dp, 0.dp)
                        ) {
                            Image(
                                painterResource(id = R.drawable.mums), contentDescription = "bg",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent)
                            )
                            Image(
                                painterResource(id = R.drawable.laundry), contentDescription = "bg",
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
                                .align(Alignment.TopCenter),
                        ) {


                            Image(
                                painterResource(id = R.drawable.ic_logo), contentDescription = "bg",
                                modifier = Modifier
                                    .aspectRatio(1f)
                                    .background(Color.Transparent)
                            )


                        }
                        Card(
                            shape = RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter),
                        ) {


                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp, 16.dp, 32.dp, 0.dp)
                            ) {




                                Text(
                                    text = "Sign Up", style = TextStyle(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        textAlign = TextAlign.Center
                                    )
                                )

                                Text(
                                    text = "Enter your details to signup your account",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        textAlign = TextAlign.Center,
                                        color = Color.Black

                                    ),
                                )
                                //Spacer(Modifier.height(8.dp))
                                CustomTextFields(placeholder = "Full Name","")
                                {
                                    userName=it
                                }
                                CustomTextFields(placeholder = "Email Id","")
                                {
                                    userEmail=it
                                }
                                CustomPasswordFields(placeholder = "Password")
                                {
                                    userPassword=it
                                }
                                CustomPasswordFields(placeholder = "Confirm Password")
                                {
                                    userConfirmPassword=it
                                }

                                Box(modifier = Modifier.padding(0.dp, 16.dp)) {
                                    Box(
                                        Modifier
                                            .fillMaxWidth()
                                            .height(60.dp)
                                            .clip(shape = RoundedCornerShape(percent = 50))
                                            .clickable {
                                                context.startActivity(
                                                    Intent(
                                                        context,
                                                        HomeActivity::class.java
                                                    )
                                                )
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
                                                ).clickable{
                                                  viewModel.signUp (userEmail,userPassword,userConfirmPassword,userName)

                                                },
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically

                                        ) {
                                            Text(
                                                text = "Sign Up ",

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

                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp, 0.dp, 0.dp, 8.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Already have account ?", style = TextStyle(
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Center,
                                            color = Color.Black

                                        )
                                    )
                                    Text(
                                        text = "  Sign In", style = TextStyle(
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF00ADBB)

                                        ),
                                        modifier = Modifier.clickable {
                                            context.startActivity(
                                                Intent(
                                                    context,
                                                    LoginActivity::class.java
                                                )
                                            )
                                        }
                                    )
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

