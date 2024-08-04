package com.nita.nit_a.userScreens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Card

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nita.nit_a.R
import com.nita.nit_a.viewModel.AuthViewModel

@Composable
fun ForgotPassword(navController: NavController){
    val context = LocalContext.current
    val authViewModel:AuthViewModel = viewModel()
    val loading by authViewModel.isLoading.observeAsState(false)

     val email = remember {
         mutableStateOf("")
     }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.nita_logo),
            contentDescription = "nita_logo", modifier = Modifier
                .padding(12.dp)
                .size(200.dp)
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

        Text(text = "Forgot Password?",
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
             color = colorResource(id = R.color.Default)
        )

        Spacer(modifier = Modifier.height(20.dp))


        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 4.dp, vertical = 8.dp)){
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    , border = BorderStroke(2.dp, Color.LightGray)
                ){
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically){
                        Icon(imageVector = Icons.Default.Email,
                            contentDescription ="Email", modifier = Modifier.padding(20.dp),
                        )
                        TextField(value =email.value ,
                            onValueChange ={email.value = it},
                            label = {Text("Registered Email") },
                            singleLine = true
                        )

                    }



                }
                Spacer(modifier = Modifier.padding(12.dp))
                Button(onClick = {
                    if(email.value == "")
                        Toast.makeText(context, "Fill All Required Details", Toast.LENGTH_SHORT).show()
                    else
                        authViewModel.forgotPassword(context, email.value)
                    email.value = ""
                    navController.navigate("login")

                } , modifier = Modifier
                    .size(400.dp, 70.dp)
                    .padding(8.dp), ) {
                    Text(text = "Reset Password", fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center){
                    Text(text = "Don't have an account? ",

                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        fontSize = 18.sp)
                    Text(text = " Register",
                        modifier = Modifier
                            .clickable { navController.navigate( "registration"){
                                navController.popBackStack()
                            }

                                       },
                        color = Color.Blue,
                        fontSize = 17.sp)
                }


            }




        }

    }

}