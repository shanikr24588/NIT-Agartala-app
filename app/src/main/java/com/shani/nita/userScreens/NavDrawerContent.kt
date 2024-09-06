package com.shani.nita.userScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shani.nita.Models.NavDrawerModel
import com.shani.nita.R
import com.shani.nita.viewModel.AuthViewModel
import com.shani.nita.viewModel.UserProfileViewModel

@Composable
fun NavDrawerHeader(){

    val context = LocalContext.current

    val  userProfileViewModel: UserProfileViewModel = viewModel()


    var name by remember {
        mutableStateOf("")
    }
    var enrollment by remember {
        mutableStateOf("")
    }




    LaunchedEffect(true) {
        userProfileViewModel.getData(context) { userData ->
            name = userData.name!!
            enrollment = userData.enrollment!!

        }

    }
    Surface(color =colorResource(id = R.color.Profile )) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth().padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start){

            Image(painter = painterResource(id = R.drawable.profileimage),
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.Start),
                contentScale = ContentScale.Fit
            )

            Text(text = name,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 18.sp
            )
            Text(text = enrollment,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 18.sp
            )



        }
    }




}

@Composable

fun NavBarBody(
    items:List<NavDrawerModel>,
    currentRoute:String?,
    onClick :(NavDrawerModel) -> Unit,
    navController: NavController
){
    val context = LocalContext.current

    val authViewModel:AuthViewModel = viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState()
    val loading by authViewModel.isLoading.observeAsState(false)








    Column(modifier = Modifier.fillMaxSize()) {
        if (loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
//        else
//            if (firebaseUser == null) {
//                LaunchedEffect(Unit) {
//                    navController.navigate("login") {
//                        navController.popBackStack(navController.graph.startDestinationId, true)
//                    }
//                }
//            }
        items.forEachIndexed { index, navDrawerModel ->
            NavigationDrawerItem(
                label = {
                    Text(text = navDrawerModel.title,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black)
                },
                selected = currentRoute == navDrawerModel.route,
                onClick = { onClick(navDrawerModel) },
                icon = {
                    androidx.compose.material3.Icon(
                        imageVector = (if (currentRoute == navDrawerModel.route) {
                            navDrawerModel.selectedIcon
                        } else {
                            navDrawerModel.unselectedIcon
                        }) as ImageVector , contentDescription = navDrawerModel.title,
                        tint = Color.Black
                    )
                },
                colors =  NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color.LightGray, // Make it semi-transparent
                    unselectedContainerColor = Color.Transparent
                )
            )

        }

//        Text(text = "SignOut",
//            fontWeight = FontWeight.Bold,
//            color = Color.White,
//            modifier = Modifier
//                .clickable {
//                    authViewModel.logOut()
//                }
//                .padding(start = 16.dp, top = 30.dp),
//            fontSize = 18.sp,
//
//        )
    }


}