package com.shani.nita.userScreens.MessMenu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shani.nita.viewModel.MessMenuViewModel
import com.shani.nita.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessMenu(navController: NavController){

    val messmenuViewModel:MessMenuViewModel = viewModel()

    val loading by messmenuViewModel.loading.observeAsState(false)
    val hostelList by messmenuViewModel.hostelList.observeAsState(emptyList())


    LaunchedEffect(true) {
        messmenuViewModel.gethostel()
    }

    Surface(color = colorResource(id = R.color.violetsurface)) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Mess Menu",
                    fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp ) },
                    colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = colorResource(
                        id = R.color.scaffoldColor
                    )),
                    navigationIcon = {
                        IconButton(onClick = {navController.navigateUp()}){
                            Icon(imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                )
            },
            containerColor = Color.Transparent,
            content = {padding->
                if (loading){
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color.Red)
                    }
                } else{
                    LazyColumn(modifier = Modifier.padding(padding)){
                        items(hostelList){
                            MessNameItemView(hostel = it, navController)


                        }

                    }
                }
            }
        )

    }

}