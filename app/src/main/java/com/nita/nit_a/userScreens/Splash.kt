package com.nita.nit_a.userScreens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nita.nit_a.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable

fun Splash(navController: NavController){

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2500
        )
    )


        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp),
            contentAlignment = Alignment.Center) {

            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Image(painter = painterResource(id = R.drawable.nita_logo),
                    contentDescription ="logo",
                    modifier = Modifier
                        .size(180.dp)
                        .alpha(alphaAnim.value)
                )

                Text(text = "NATIONAL INSTITUTE OF TECHNOLOGY",
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .wrapContentWidth().alpha(alphaAnim.value),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    color = colorResource(id = R.color.Default)
                )

                Text(text = "AGARTALA",
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .alpha(alphaAnim.value),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                    color = colorResource(id = R.color.Default)
                )

                Text(text ="Developed By:-",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,

                    modifier = Modifier.alpha(alphaAnim.value)
                )
                Text(text = "SHANI KUMAR",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,

                    modifier = Modifier.alpha(alphaAnim.value)
                )
                Text(text = "22UEE135",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,

                    modifier = Modifier.alpha(alphaAnim.value)
                )

            }

        }

    


     

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2500)

        if(FirebaseAuth.getInstance().currentUser != null){
            navController.navigate("home"){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        } else {
            navController.navigate("login") {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }


    }
}