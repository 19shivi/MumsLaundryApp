package com.example.mumslaundryapp.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mumslaundryapp.R
import com.example.mumslaundryapp.common.formatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFields(placeholder: String, default: String,isNumeric:Boolean=false,
        inputText:(String)->Unit)
{
    var text:String by remember {
        mutableStateOf(default)
    }
    Column(Modifier.padding(0.dp,8.dp,0.dp,0.dp)) {
        Text(placeholder)
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, Color.Black)

        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = if(isNumeric)
                    KeyboardType.Number
                else
                    KeyboardType.Text
                    ),
                placeholder = { Text(text = placeholder) },
                onValueChange = {
                    text = it
                    inputText(it)
                },

                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                singleLine = true


            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordFields(placeholder: String,inputText:(String)->Unit)
{
    var text:String by remember {
        mutableStateOf("")
    }
    Column(Modifier.padding(0.dp,8.dp,0.dp,0.dp)) {
        Text(placeholder)
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, Color.Black)

        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                placeholder = { Text(text = placeholder) },
                onValueChange = {
                    text = it
                    inputText(it)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),

                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                singleLine = true
                /* leadingIcon = {
                     Icon(
                         painter = painterResource(id = R.drawable.ic_arrow_tilt),
                         contentDescription = "",
                         modifier = Modifier.size(24.dp),
                         tint = Color.Transparent
                     )
                 }*/

            )
        }
    }


}
@Composable
fun serviceTypeWidget(drawable:Int,lable:String,modifier: Modifier,selected:(Boolean)->(Unit))
{
    var isSelected by remember {
        mutableStateOf(false)
    }
    var color by remember {
        mutableStateOf(Color(0xFFD8D8D8))
    }
    var textColor by remember {
        mutableStateOf(Color.Black)
    }
    var tintColor by remember {
        mutableStateOf(Color.Black)
    }
    Box(modifier)
    {
        Column(modifier= Modifier.fillMaxWidth()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.25f)
                .clip(
                    RoundedCornerShape(16.dp)
                )

                ,
                contentAlignment = Alignment.Center
               )
                {
                    Box(modifier = Modifier
                        .background(color)
                        .fillMaxSize()
                        .clickable {
                            isSelected = !isSelected
                            if (isSelected) {
                                textColor = Color(0xFF00ADBB)
                                color = Color(0xFF00ADBB)
                                tintColor = Color.White
                            } else {
                                textColor = Color.Black
                                tintColor = Color.Black
                                color = Color(0xFFD8D8D8)
                            }
                        })

                Icon(
                    painter = painterResource(id = drawable),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.Transparent)
                        ,
                    tint = tintColor
                )
            }
            Text(text = lable,
                modifier= Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 8.dp, 0.dp, 0.dp),
                style= TextStyle(
                    fontSize = 16.sp,
                    color = textColor
                )
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun selectWidget(textColor:Color,textColorSelected:Color,
                 bgColor:Color,bgColorSelected:Color,defaultValue:Boolean,onChange:(Boolean)->(Unit)){

    var isSelectedButton:Boolean by remember {
        mutableStateOf(defaultValue)
    }.apply {
        defaultValue
    }
    Row(Modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .weight(1f)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                isSelectedButton = true
                onChange(true)
            }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        if (isSelectedButton)
                            bgColorSelected
                        else
                            bgColor
                    ),

                contentAlignment = Alignment.Center

            )
            {
                Text(
                    "Yes",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 4.dp, 16.dp, 0.dp),
                    style = TextStyle(
                        color = if(isSelectedButton)
                            textColorSelected
                        else
                            textColor,
                        fontSize = 18.sp

                    ),
                    textAlign = TextAlign.Center

                )
                Image(
                    painter = painterResource(id = if(isSelectedButton)
                        R.drawable.ic_selected
                    else
                        R.drawable.ic_unselected),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(0.dp, 0.dp, 16.dp, 0.dp),
                    contentDescription = ""
                )

            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .weight(1f)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                isSelectedButton = false
                onChange(false)
            }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        if (isSelectedButton)
                            bgColor
                        else
                            bgColorSelected
                    ),
                contentAlignment = Alignment.Center

            )
            {
                Text(
                    "No",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 4.dp, 16.dp, 0.dp),
                    style = TextStyle(
                        color = if(isSelectedButton)
                            textColor
                        else
                            textColorSelected,
                        fontSize = 18.sp

                    ),
                    textAlign = TextAlign.Center

                )
                Image(
                    painter = painterResource(id = if(isSelectedButton)
                        R.drawable.ic_unselected
                    else
                        R.drawable.ic_selected),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(0.dp, 0.dp, 16.dp, 0.dp),
                    contentDescription = ""
                )

            }
        }
    }
}

@Composable
fun TimeSelector(default:String,onChange: (Int) -> Unit){
    var currentSelector by remember {
        mutableStateOf(0)
    }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        Card (
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(4.dp, 0.dp, 4.dp, 0.dp)
                .height(32.dp)
                .clickable {
                    currentSelector = 0
                    onChange(0)
                },
            shape= RoundedCornerShape(8.dp),
            colors=CardDefaults.cardColors(containerColor = Color.Transparent),
            border = BorderStroke(1.dp,Color.White)
        ){
            Box (

                Modifier
                    .fillMaxSize()
                    .background(
                        color =
                        if (currentSelector == 0)
                            Color.White
                        else
                            Color.Transparent
                    ),
                contentAlignment = Alignment.Center){
                Text(text = "Weekly", Modifier
                    .fillMaxWidth(), textAlign = TextAlign.Center,
                    style = TextStyle(
                         if(currentSelector==0)
                        Color(0xFF154359)
                        else
                        Color.White,
                        fontSize = 18.sp
                    ))
            }
        }
        Card (
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(4.dp, 0.dp, 4.dp, 0.dp)
                .height(32.dp)
                .clickable {
                    currentSelector = 1
                    onChange(1)
                },
            colors=CardDefaults.cardColors(containerColor = Color.Transparent),
            shape= RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp,Color.White)
        ){
            Box (

                Modifier
                    .fillMaxSize()
                    .background(
                        color =
                        if (currentSelector == 1)
                            Color.White
                        else
                            Color.Transparent
                    ),
                contentAlignment = Alignment.Center){
                Text(text = "Monthly", Modifier
                    .fillMaxWidth(), textAlign = TextAlign.Center,
                    style = TextStyle(
                        if(currentSelector==1)
                            Color(0xFF154359)
                        else
                            Color.White,
                        fontSize = 18.sp
                    ))
            }
        }
        Card (
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(4.dp, 0.dp, 4.dp, 0.dp)
                .height(32.dp)
                .clickable {
                    currentSelector = 2
                    onChange(2)
                },
            shape= RoundedCornerShape(8.dp),
            colors=CardDefaults.cardColors(containerColor = Color.Transparent),
            border = BorderStroke(1.dp,Color.White)
        ){
            Box (

                Modifier
                    .fillMaxSize()
                    .background(
                        color =
                        if (currentSelector == 2)
                            Color.White
                        else
                            Color.Transparent
                    ),
                contentAlignment = Alignment.Center){
                Text(text = "Yearly", Modifier
                    .fillMaxWidth(), textAlign = TextAlign.Center,
                    style = TextStyle(
                        if(currentSelector==2)
                            Color(0xFF154359)
                        else
                            Color.White,
                        fontSize = 18.sp
                    ))
            }
        }

    }
}
@Composable
fun LoadTrack(title:String,load:Long,sale:Long){

    Column(Modifier.fillMaxWidth()) {
        Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){

            Text(text =title,style =TextStyle(
                fontSize = 24.sp,  color = Color.White
            ) )
            Text(text ="Loads: "+load.toString() ,style =TextStyle(
                fontSize = 20.sp,  color = Color.White
            ))


        }
        Text(text = "₱ "+ formatter(sale), style =TextStyle(
            fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White
        ), modifier = Modifier.fillMaxWidth(),
           textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier
            .padding(0.dp, 16.dp, 0.dp, 0.dp)
            .height(1.dp)
            .fillMaxWidth()
            .background(
                Color.White
            ))
    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(placeholder: String, options: Array<String>, onChange: (String) -> Unit)
{
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        modifier = Modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        Column(Modifier.padding(0.dp,8.dp,0.dp,0.dp)) {
            Text(placeholder)
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Black)

            ) {
                TextField(
                    readOnly=true,
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    value = selectedOptionText,
                    placeholder = { Text(text = placeholder) },
                    onValueChange = {
                       onChange(it)
                    },

                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,

                            trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                )
            }
        }


        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text={Text(text = selectionOption)},
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        onChange(selectedOptionText)
                    }
                )
            }
        }
    }
}