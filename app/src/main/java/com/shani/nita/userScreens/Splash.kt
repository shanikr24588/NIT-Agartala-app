package com.shani.nita.userScreens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shani.nita.R
import kotlinx.coroutines.delay

@Composable

fun Splash(navController: NavController){

    val scope = rememberCoroutineScope()

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2500
        )
    )


    Surface(color = colorResource(id = R.color.SplashColor)) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Image(painter = painterResource(id = R.drawable.nita_logo),
                    contentDescription ="logo",
                    modifier = Modifier
                        .size(180.dp)
                        .alpha(alphaAnim.value)
                )

                Text(text = "NATIONAL INSTITUTE OF ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp),
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.Default)
                )

                Text(text = "TECHNOLOGY AGARTALA",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp),
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.Default)
                )

                Text(text ="Developed By:-",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,

                    modifier = Modifier.alpha(alphaAnim.value)
                )
//                Text(text = "SHANI KUMAR",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 13.sp,
//
//                    modifier = Modifier.alpha(alphaAnim.value)
//                )
                Text(text = "22UEE135",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.alpha(alphaAnim.value).padding(end = 20.dp)
                )

            }

        }
    }


    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1200)

        navController.navigate("home") {
            navController.popBackStack(navController.graph.startDestinationId, true)
        }

//        scope.launch {
//            if (FirebaseAuth.getInstance().currentUser != null) {
//                navController.navigate("home") {
//                    navController.popBackStack(navController.graph.startDestinationId, true)
//                }
//            } else {
//                navController.navigate("login") {
//                    navController.popBackStack(navController.graph.startDestinationId, true)
//                }
//            }
//        }


    }
}