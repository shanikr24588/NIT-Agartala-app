package com.nita.nit_a.adminScreens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nita.nit_a.R
import com.nita.nit_a.viewModel.AdminAuthViewModel

@Composable
fun AdminLoginScreen(navController: NavController) {

    val context = LocalContext.current
    val email = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }

    val adminAuthViewModel:AdminAuthViewModel = viewModel()

    val loading by adminAuthViewModel.isAdminLoading.observeAsState(false)
    val isAdminLoggedIn by adminAuthViewModel.isAdminLoggedIn.observeAsState(false)




    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        if (loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else

                LaunchedEffect(isAdminLoggedIn) {
                    if(isAdminLoggedIn){
                        navController.navigate("admin_dashboard"){
                            navController.popBackStack()
                        }
                    }

                }

        Image(
            painter = painterResource(R.drawable.nita_logo),
            contentDescription = "nita_logo", modifier = Modifier
                .padding(12.dp)
                .size(150.dp)
        )

        Text(text = "National Institute of Technology Agartala",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 40.dp),
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.Default)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Welcome Back!",
            textAlign = TextAlign.Center,
             fontStyle = FontStyle.Italic,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.Default)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Admin..",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Default,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold

        )

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)) {
            Column(modifier = Modifier,

                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    , border = BorderStroke(2.dp, Color.LightGray)){
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically){
                        Icon(imageVector = Icons.Default.Email,
                            contentDescription ="Email", modifier = Modifier.padding(20.dp),
                        )
                        TextField(value =email.value ,
                            onValueChange ={email.value = it},
                            label = {Text("Email")},
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email
                            ), singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                    }


                }
                Spacer(modifier = Modifier.padding(4.dp))

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    , border = BorderStroke(2.dp, Color.LightGray)){
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically){
                        Icon(imageVector = Icons.Default.Lock,
                            contentDescription ="password", modifier = Modifier.padding(20.dp),
                        )
                        TextField(value =password.value ,
                            onValueChange ={password.value = it},
                            label = {Text("Password") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                    }



                }

                Spacer(modifier = Modifier.padding(4.dp))

                Button(onClick = {  adminAuthViewModel.adminLogin(context,email.value, password.value) } ,
                    modifier = Modifier
                        .size(400.dp, 70.dp)
                        .padding(8.dp), ) {
                    Text(text = "Login", fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }


            }

        }



    }
}