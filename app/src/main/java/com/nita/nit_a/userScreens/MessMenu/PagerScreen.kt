package com.nita.nit_a.userScreens.MessMenu

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nita.nit_a.R
import com.nita.nit_a.viewModel.MessMenuViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable

fun PagerScreen(navController: NavController, hostel:String){

    val pagerPage = remember{
        mutableStateOf(0)
    }

    val scope = rememberCoroutineScope()
    val messmenuViewModel: MessMenuViewModel = viewModel()

    LaunchedEffect(hostel) {
        if (hostel.isNotBlank()) {
            messmenuViewModel.getday(hostel)
        }
    }



    val dayList by messmenuViewModel.dayList.observeAsState(emptyList())

    val collectionTabs = dayList


    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { collectionTabs.size }
    )

    val messmenuLists = collectionTabs.map { day ->
        messmenuViewModel.getMessMenu(hostel, day)
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        pagerPage.value = pagerState.currentPage

    }



    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement =  Arrangement.Top) {

        ScrollableTabRow(
            contentColor = colorResource(id = R.color.purple_500),
            edgePadding = 0.dp,
            selectedTabIndex = pagerState.currentPage,
            tabs = {
                collectionTabs.forEachIndexed { index, tabName ->
                    Tab(selected = index == pagerState.currentPage, onClick = {
                        scope.launch { pagerState.animateScrollToPage(index) }
                    },
                        text = { Text(text = tabName) })
                }
            }
        )


        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top,
            beyondBoundsPageCount = collectionTabs.count(),
            userScrollEnabled = true
        ) { tabIndex ->
            val messmenuListLiveData = messmenuLists[tabIndex]
            val messmenuList by messmenuViewModel.messmenuList.observeAsState(emptyList())
            LazyColumn() {
                items(messmenuList) {
                    MessMenuItemView(messmenuModel = it)
                }
            }
        }

    }

}






