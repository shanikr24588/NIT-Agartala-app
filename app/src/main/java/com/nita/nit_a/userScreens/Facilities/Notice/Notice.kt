package com.nita.nit_a.userScreens.Facilities.Notice

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nita.nit_a.ui.theme.Purple40
import com.nita.nit_a.viewModel.NoticeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import com.nita.nit_a.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable


fun Notice(navController:NavController){

    val noticeViewModel: NoticeViewModel = viewModel()
    val noticeList by noticeViewModel.noticeList.observeAsState(emptyList())
    val loader by noticeViewModel.loading.observeAsState(false)
    val isRefreshing by noticeViewModel.loading.observeAsState(false)

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
    val scope = rememberCoroutineScope()





    LaunchedEffect(true) {
        noticeViewModel.getNotice()
    }

    Surface(color = colorResource(id = R.color.violetsurface)) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Notice",
                    fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp ) },
                    colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),
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
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 12.dp),
                ) {
                    SwipeRefresh(
                        state = swipeRefreshState,
                        onRefresh = { scope.launch { noticeViewModel.getNotice() }}
                    ){
                        if(loader){
                            Box(modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        } else {
                            LazyColumn(modifier = Modifier.padding(padding)) {
                                items(noticeList){
                                    NoticeItemView(noticeModel = it, navController)

                                }

                            }
                        }


                    }
                    FloatingActionButton(onClick = { scope.launch { noticeViewModel.getNotice() } },
                        shape = FloatingActionButtonDefaults.shape,
                        modifier = Modifier
                            .width(80.dp)
                            .height(40.dp)
                            .align(Alignment.BottomCenter)
                    ) {

                        Text(text = "Refresh")


                    }
                }

            }
        )
    }



}