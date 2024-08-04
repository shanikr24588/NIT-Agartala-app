package com.nita.nit_a.userScreens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.nita.nit_a.R

import com.nita.nit_a.ui.theme.Purple40
import com.nita.nit_a.viewModel.BannerViewModel
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer

import com.nita.nit_a.Models.NavDrawerModel
import com.nita.nit_a.Widgets.AnimatedLoading
import com.nita.nit_a.viewModel.CalenderViewModel
import kotlinx.coroutines.launch


@Composable
fun BannerLoadingPlaceholder() {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(220.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedLoading()
        }

    }
}



@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable

fun HomeScreen(navController: NavController) {


    val scope = rememberCoroutineScope()

    val items = listOf(
        NavDrawerModel(
            title = "Profile",
            route = "profile",
            selectedIcon = R.drawable.person,
            unselectedIcon = Icons.Outlined.Person
        ),

        NavDrawerModel(
            title = "Administrators",
            route = "administrators",
            selectedIcon = R.drawable.administrative,
            unselectedIcon = Icons.Outlined.Star
        ),

        NavDrawerModel(
            title = "Contact Us",
            route = "contactus",
            selectedIcon = R.drawable.mail,
            unselectedIcon = Icons.Outlined.Email
        ),
    )




    val context = LocalContext.current
    val uri = Uri.parse("https://mis.nita.ac.in/iitmsv4eGq0RuNHb0G5WbhLmTKLmTO7YBcJ4RHuXxCNPvuIw=?enc=EGbCGWnlHNJ/WdgJnKH8DA==")

    val bannerViewModel:BannerViewModel = viewModel ()
    val bannerList by bannerViewModel.bannerList.observeAsState(null)



    LaunchedEffect(true) {
        bannerViewModel.getBanner()
    }
    val pagerState = com.google.accompanist.pager.rememberPagerState(0)
    val imageSlider = ArrayList<AsyncImagePainter>()
    if (bannerList != null)
    bannerList!!.forEach {
        imageSlider.add(rememberAsyncImagePainter(model = it.url))

    }
    LaunchedEffect(Unit) {
        try {
            while(true) {
                yield()
                delay(2600)
                pagerState.animateScrollToPage(page = (pagerState.currentPage+1 ) % pagerState.pageCount)
            }
        } catch (e: Exception) {

        }

    }

    val  calenderViewModel: CalenderViewModel = viewModel()
    val  calender by calenderViewModel.calender.observeAsState()


    LaunchedEffect(key1 = true) {
        calenderViewModel.getCalender()

    }




    val Drawer = rememberDrawerState(initialValue = DrawerValue.Closed)
    BackHandler(enabled = Drawer.isOpen) {
        scope.launch { Drawer.close() }
    }
    ModalNavigationDrawer(drawerContent = {
                                          ModalDrawerSheet(modifier = Modifier
                                              .fillMaxWidth(0.8f)
                                              ) {
                                              NavDrawerHeader()
                                              Spacer(modifier = Modifier.height(8.dp))
                                              NavBarBody(items = items,
                                                  currentRoute =  null,
                                                  onClick ={navDrawerModel -> navController.navigate(navDrawerModel.route)  },
                                                  navController)

                                          }
    }, drawerState = Drawer)  {

        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Dashboard",
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    color = Color.White, fontSize = 30.sp,
                    modifier = Modifier.padding(start = 60.dp)) },
                    colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),

                    navigationIcon = {
                        IconButton(onClick = { scope.launch { Drawer.open()}}){
                            Icon(imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = Color.White, modifier = Modifier.size(40.dp)
                            )
                        }

                    },
                    actions = {

                        Column {
                            IconButton(
                                onClick = { navController.navigate("admin_login") },
                                modifier = Modifier
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = Color.White, modifier = Modifier.size(45.dp),

                                    )

                            }
                            Text(
                                text = "Admin",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(bottom = 13.dp),
                                fontSize = 15.sp
                            )
                        }



                    }
                )
            },
            content = {padding->


                Column(modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()


                ){

                    LazyColumn {
                        if (bannerList == null){
                            item { BannerLoadingPlaceholder() }


                        } else {
                            item {
                                com.google.accompanist.pager.HorizontalPager(count = imageSlider.size,
                                    state = pagerState, modifier = Modifier) {pager->
                                    Card(modifier = Modifier.height(220.dp)) {

                                        Image(
                                            painter = imageSlider[pager],
                                            contentDescription = "banner",
                                            modifier = Modifier
                                                .height(220.dp)
                                                .fillMaxWidth(),
                                            contentScale = ContentScale.Crop
                                        )





                                    }

                                }

                            }
                            item {
                                Row(horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth()){
                                    HorizontalPagerIndicator(pagerState = pagerState,
                                        modifier = Modifier
                                    )

                                }

                            }
                        }
                        item{
                            Column(modifier = Modifier.fillMaxSize().padding(bottom = 8.dp),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally){
                                Row(
                                    modifier = Modifier
                                        .height(IntrinsicSize.Min)
                                        .padding(vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.divider1),
                                        contentDescription = "devider", modifier = Modifier
                                            .size(25.dp)

                                    )
                                    Text(
                                        modifier = Modifier.padding(horizontal = 8.dp),
                                        text = "Facilities",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.devider2),
                                        contentDescription = "divider2", modifier = Modifier
                                            .size(25.dp)

                                    )
                                }


                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly){

                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable { navController.navigate("notice") },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Image(
                                                painter = painterResource(id = R.drawable.notifications),
                                                contentDescription = "notice", modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("Notice", fontWeight = FontWeight.Bold,
                                                modifier =Modifier
                                            )


                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable {
                                            context.startActivity(
                                                Intent(
                                                    Intent.ACTION_VIEW,
                                                    uri
                                                )
                                            )
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Image(
                                                painter = painterResource(id = R.drawable.network),
                                                contentDescription = "MIS", modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(top = 2.dp)
                                            )
                                            Text("MIS", fontWeight = FontWeight.Bold,
                                                modifier =Modifier
                                            )




                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable { navController.navigate("mess_menu") },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Image(
                                                painter = painterResource(id = R.drawable.cutlery),
                                                contentDescription = "messMenu", modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(top = 2.dp)
                                            )
                                            Text("Mess Menu", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,
                                                fontSize = 12.sp
                                            )


                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable {
                                            if (calender != null)
                                                navController.navigate(
                                                    "pdfScreen/${
                                                        Uri.encode(
                                                            calender!!.url
                                                        )
                                                    }"
                                                )
                                            else
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Calender Not Found",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()


                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){

                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Image(
                                                painter = painterResource(id = R.drawable.calendar),
                                                contentDescription = "Calender", modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(top = 2.dp)
                                            )
                                            Text("Calendar", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,
                                                fontSize = 12.sp
                                            )


                                        }

                                    }

                                }
                                Spacer(modifier = Modifier.padding(4.dp))

                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly){

                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable { navController.navigate("chat") },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Image(
                                                painter = painterResource(id = R.drawable.studentschat),
                                                contentDescription = "Chat", modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(top = 2.dp)
                                            )
                                            Text("Chat", fontWeight = FontWeight.Bold,
                                                modifier =Modifier
                                            )


                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable {
                                            navController.navigate("scholarship")
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Image(
                                                painter = painterResource(id = R.drawable.scholarshipcollge),
                                                contentDescription = "Scholarship", modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(top = 2.dp)
                                            )
                                            Text("Scholarship", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,
                                                fontSize = 12.sp
                                            )




                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable { navController.navigate("clubs") },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Image(
                                                painter = painterResource(id = R.drawable.teamwork),
                                                contentDescription = "messMenu", modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(top = 2.dp)
                                            )
                                            Text("Clubs", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,
                                                fontSize = 12.sp
                                            )


                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable {
                                            navController.navigate("events")
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){

                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Image(
                                                painter = painterResource(id = R.drawable.event),
                                                contentDescription = "Events", modifier = Modifier
                                                    .size(35.dp)
                                                    .padding(top = 2.dp)
                                            )
                                            Text("Events", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,
                                                fontSize = 12.sp
                                            )


                                        }

                                    }


                                }

                                Row(
                                    modifier = Modifier
                                        .height(IntrinsicSize.Min)
                                        .padding(vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.divider1),
                                        contentDescription = "devider", modifier = Modifier
                                            .size(25.dp)

                                    )
                                    Text(
                                        modifier = Modifier.padding(horizontal = 8.dp),
                                        text = "Departments",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.devider2),
                                        contentDescription = "divider2", modifier = Modifier
                                            .size(25.dp)

                                    )
                                }

                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly){

                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable { navController.navigate("first_year") },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.firstyear),
                                                contentDescription = "1st Year", modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("1st Year", fontWeight = FontWeight.Bold,
                                                modifier =Modifier
                                            )


                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable {
                                            navController.navigate("cse")
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.cse),
                                                contentDescription = "CSE", modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("CSE", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,

                                            )




                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable { navController.navigate("ece") },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.ece),
                                                contentDescription =  null, modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("ECE", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,

                                            )


                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable {
                                            navController.navigate("ee")
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){

                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.ee),
                                                contentDescription =  null, modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("EE", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,

                                            )


                                        }

                                    }


                                }
                                Spacer(modifier = Modifier.padding(4.dp))

                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly){

                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable { navController.navigate("eie") },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.eie),
                                                contentDescription = null, modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("EIE", fontWeight = FontWeight.Bold,
                                                modifier =Modifier
                                            )


                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable {
                                            navController.navigate("me")
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.mechanical),
                                                contentDescription =  null, modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("Mech", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,

                                                )




                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable { navController.navigate("ce") },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.civil),
                                                contentDescription =  null, modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("Civil", fontWeight = FontWeight.Bold,
                                                modifier =Modifier

                                            )


                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable {
                                            navController.navigate("che")
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){

                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.chemical),
                                                contentDescription =  null, modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("Chemical", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,

                                                )


                                        }

                                    }


                                }

                                Spacer(modifier = Modifier.padding(4.dp))

                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly){

                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable { navController.navigate("pe") },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.production),
                                                contentDescription = null, modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("PE", fontWeight = FontWeight.Bold,
                                                modifier =Modifier
                                            )


                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable {
                                            navController.navigate("be")
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.bio),
                                                contentDescription =  null, modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("Bio", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,

                                                )




                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable { navController.navigate("phy") },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.physics),
                                                contentDescription =  null, modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("Physics", fontWeight = FontWeight.Bold,
                                                modifier =Modifier

                                            )


                                        }

                                    }
                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable {
                                            navController.navigate("chem")
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){

                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.chemistry),
                                                contentDescription =  null, modifier = Modifier
                                                    .size(35.dp)
                                            )
                                            Text("Chemistry", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,

                                                )


                                        }

                                    }


                                }

                                Spacer(modifier = Modifier.padding(4.dp))

                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start){

                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .padding(start = 8.dp)
                                        .clickable { navController.navigate("math") },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Column(verticalArrangement = Arrangement.SpaceEvenly,
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxSize()){
                                            Icon(
                                                painter = painterResource(id = R.drawable.math),
                                                contentDescription = null, modifier = Modifier
                                                    .padding(top = 4.dp)
                                                    .size(35.dp)
                                            )
                                            Text("Math", fontWeight = FontWeight.Bold,
                                                modifier =Modifier
                                            )


                                        }

                                    }



                                }


                            }
                        }





                    }





















                }

            }
        )


    }


}


     
    



     
    

