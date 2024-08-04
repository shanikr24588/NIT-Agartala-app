package com.nita.nit_a.userScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nita.nit_a.Models.NavDrawerModel
import com.nita.nit_a.R
import com.nita.nit_a.viewModel.AuthViewModel
import com.nita.nit_a.viewModel.UserProfileViewModel

@Composable
fun NavDrawerHeader(){

    val context = LocalContext.current

    val  userProfileViewModel: UserProfileViewModel = viewModel()
    val authViewModel:AuthViewModel = viewModel()


    val _enrollment by authViewModel.editenrollment.observeAsState("")




    var name by remember {
        mutableStateOf("")
    }


    LaunchedEffect(true) {
        authViewModel.getUserData()

    }

    LaunchedEffect(true) {
        userProfileViewModel.getData(context) { userData ->
            name = userData.name!!

        }

    }

     Column(modifier = Modifier
         .fillMaxWidth()
         .wrapContentWidth(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.Start){

         Image(painter = painterResource(id = R.drawable.person),
             contentDescription = null,
             modifier = Modifier
                 .padding(10.dp)
                 .size(100.dp)
                 .clip(CircleShape)
                 .align(Alignment.Start),
             contentScale = ContentScale.Fit
         )

         Text(text = name, fontWeight = FontWeight.Bold)
         Text(text = _enrollment!!, fontWeight = FontWeight.Bold)

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
        } else
            if (firebaseUser == null) {
                LaunchedEffect(Unit) {
                    navController.navigate("login") {
                        navController.popBackStack(navController.graph.startDestinationId, true)
                    }
                }
            }
        items.forEachIndexed { index, navDrawerModel ->
            NavigationDrawerItem(label = {
                Text(text = navDrawerModel.title, fontWeight = FontWeight.Bold)
            },
                selected = currentRoute == navDrawerModel.route,
                onClick = { onClick(navDrawerModel) },
                icon = {
                    androidx.compose.material3.Icon(
                        imageVector = (if (currentRoute == navDrawerModel.route) {
                            navDrawerModel.selectedIcon
                        } else {
                            navDrawerModel.unselectedIcon
                        }) as ImageVector, contentDescription = navDrawerModel.title
                    )
                })

        }

        Text(text = "SignOut",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    authViewModel.logOut()
                }
                .padding(start = 16.dp, top = 30.dp),
            fontSize = 18.sp,

        )
    }


}