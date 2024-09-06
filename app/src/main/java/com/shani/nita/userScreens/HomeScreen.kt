package com.shani.nita.userScreens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.shani.nita.R
import com.shani.nita.viewModel.BannerViewModel
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import com.shani.nita.Models.NavDrawerModel
import com.shani.nita.Widgets.AnimatedLoading
import com.shani.nita.viewModel.CalenderViewModel
import com.shani.nita.viewModel.NoticeViewModel
import kotlinx.coroutines.launch






@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable

fun HomeScreen(navController: NavController) {

    val noticeViewModel:NoticeViewModel = viewModel()
    val noticeList by noticeViewModel.noticeList.observeAsState(emptyList())


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

        NavDrawerModel(
            title = "Admin",
            route = "admin_login",
            selectedIcon = R.drawable.admin1,
            unselectedIcon = Icons.Outlined.Face
        )
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
            .fillMaxWidth(0.8f),
//            drawerContainerColor = colorResource(id = R.color.NavDrawer)
        ) {
            NavDrawerHeader()
            Spacer(modifier = Modifier.height(8.dp))
            NavBarBody(items = items,
                currentRoute =  null,
                onClick ={navDrawerModel -> navController.navigate(navDrawerModel.route)  },
                navController
            )

        }

    }, drawerState = Drawer)  {

        val statusBarColor = colorResource(id = R.color.IconColor)

        val window = (LocalContext.current as Activity).window
        window.statusBarColor = statusBarColor.toArgb()

        Surface(color = Color.Transparent) {
            Scaffold(
                topBar = {
                    TopAppBar(title = { Text(text = "NIT - A",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        color = Color.White, fontSize = 25.sp,
                        modifier = Modifier.padding(start = 85.dp)) },
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            containerColor = colorResource(id = R.color.IconColor)
                        ),

                        navigationIcon = {
                            IconButton(onClick = { scope.launch { Drawer.open()}}){
                                Icon(imageVector = Icons.Default.Menu,
                                    contentDescription = null,
                                    tint = Color.White, modifier = Modifier.size(40.dp)
                                )
                            }

                        },

                        )
                },
                containerColor = Color.Transparent

            )
            {padding->

                LazyColumn(modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()) {
                    if (bannerList == null){
                        item { BannerLoadingPlaceholder() }


                    } else {
                        item {
                            com.google.accompanist.pager.HorizontalPager(count = imageSlider.size,
                                state = pagerState, modifier = Modifier) {pager->
                                Card(modifier = Modifier
                                    .height(220.dp)
                                    .padding(8.dp) ) {

                                    Image(
                                        painter = imageSlider[pager],
                                        contentDescription = "banner",
                                        modifier = Modifier
                                            .height(220.dp)
                                            .fillMaxWidth(),
                                        contentScale = ContentScale.FillBounds
                                    )
                                }

                            }

                        }
                        item {
                            Row(horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()){
                                HorizontalPagerIndicator(pagerState = pagerState,
                                    modifier = Modifier,
                                    activeColor = colorResource(id = R.color.IconColor),
                                    spacing = 4.dp
                                )

                            }

                        }
                    }
                    item{
                        Box(modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center ){
                            Column(modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 8.dp),
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
                                            .size(25.dp),
                                        tint = colorResource(id = R.color.IconColor)

                                    )
                                    Text(
                                        modifier = Modifier.padding(horizontal = 8.dp),
                                        text = "Facilities",
                                        color =  colorResource(id = R.color.IconColor),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.devider2),
                                        contentDescription = "divider2", modifier = Modifier
                                            .size(25.dp),
                                        tint = colorResource(id = R.color.IconColor)

                                    )
                                }


                                Row(modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly){

                                    Card(modifier = Modifier
                                        .height(60.dp)
                                        .width(80.dp)
                                        .clickable {
                                            navController.navigate("notice")
                                            noticeList?.forEach {
                                                noticeViewModel.markNoticeAsSeen(it)
                                                it.isNew = false
                                            }
                                        },
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White)){
                                        Box(modifier = Modifier.fillMaxSize()) {
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
                                            val newNoticeCount = noticeList.count { it.isNew }
                                            Log.d("NoticeCount", "New notice count: $newNoticeCount") // Check the count of new notices
                                            if (newNoticeCount > 0) {
                                                Box(
                                                    modifier = Modifier
                                                        .align(Alignment.TopEnd)
                                                        .padding(8.dp)
                                                        .background(
                                                            Color.Red,
                                                            shape = CircleShape
                                                        )
                                                        .size(24.dp),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text(
                                                        text = newNoticeCount.toString(),
                                                        color = Color.White,
                                                        fontSize = 12.sp
                                                    )
                                                }
                                            }

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
                                            .size(25.dp),
                                        tint = colorResource(id = R.color.IconColor)

                                    )
                                    Text(
                                        modifier = Modifier.padding(horizontal = 8.dp),
                                        text = "Departments",
                                        color = colorResource(id = R.color.IconColor),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    Icon(
                                        painter = painterResource(id = R.drawable.devider2),
                                        contentDescription = "divider2", modifier = Modifier
                                            .size(25.dp),
                                        tint = colorResource(id = R.color.IconColor)

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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("1st Year",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)
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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("CSE",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)

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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("ECE",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)

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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("EE",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)

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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("EIE",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)
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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("Mech",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)
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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("Civil",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)
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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("Chemical",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)

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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("PE",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)
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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("Bio",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)

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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("Physics",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)
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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("Chemistry", fontWeight = FontWeight.Bold,
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)

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
                                                    .size(35.dp),
                                                tint = colorResource(id = R.color.IconColor)
                                            )
                                            Text("Math",
                                                modifier =Modifier,
                                                color = colorResource(id = R.color.IconColor)
                                            )


                                        }

                                    }



                                }


                            }
                        }

                    }



                }

            }
        }


    }


}

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





     
    



     
    

