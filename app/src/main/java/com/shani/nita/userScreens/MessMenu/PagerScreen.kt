package com.shani.nita.userScreens.MessMenu

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
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
import com.shani.nita.R
import com.shani.nita.viewModel.MessMenuViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(navController: NavController, hostel: String) {
    val pagerPage = remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val messmenuViewModel: MessMenuViewModel = viewModel()
    val loading by messmenuViewModel.loading.observeAsState(false)
    val messmenuMap by messmenuViewModel.messmenuMap.observeAsState(mutableMapOf())
    val dayList by messmenuViewModel.dayList.observeAsState(emptyList())

    // Fetch days when hostel changes
    LaunchedEffect(hostel) {
        if (hostel.isNotBlank()) {
            messmenuViewModel.getday(hostel)
        }
    }

    // Fetch menus for each day after dayList is populated
    LaunchedEffect(dayList) {
        if (dayList.isNotEmpty()) {
            // Ensure data is fetched for all days
            val daysToFetch = dayList.filterNot { messmenuMap.containsKey(it) }
            if (daysToFetch.isNotEmpty()) {
                val jobs = daysToFetch.map { day ->
                    async {
                        messmenuViewModel.getMessMenu(hostel, day)
                        Log.d("MessMenu", "Fetching mess menu for day $day")
                    }
                }
                jobs.awaitAll()
            }
        }
    }

    // Set up PagerState
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { dayList.size }
    )

    LaunchedEffect(pagerState.currentPage) {
        pagerPage.value = pagerState.currentPage
    }

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Top) {
        if (dayList.isNotEmpty()) {
            ScrollableTabRow(
                contentColor = colorResource(id = R.color.white),
                edgePadding = 0.dp,
                selectedTabIndex = pagerState.currentPage,
                containerColor = colorResource(id = R.color.scaffoldColor),
                tabs = {
                    dayList.forEachIndexed { index, tabName ->
                        Tab(
                            selected = index == pagerState.currentPage,
                            onClick = {
                                scope.launch { pagerState.animateScrollToPage(index) }
                            },
                            text = { Text(text = tabName) }
                        )
                    }
                }
            )
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top,
            beyondBoundsPageCount = dayList.count(),
            userScrollEnabled = true
        ) { tabIndex ->
            val day = dayList.getOrNull(tabIndex) ?: return@HorizontalPager
            val messmenu = messmenuMap[day] ?: emptyList()
            Log.d("MessMenu", "Mess menu for day $day: $messmenu")
            if (loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn {
                    items(messmenu) {
                        MessMenuItemView(messmenuModel = it)
                    }
                }
            }
        }
    }
}








