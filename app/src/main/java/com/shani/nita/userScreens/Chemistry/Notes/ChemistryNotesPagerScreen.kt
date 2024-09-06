package com.shani.nita.userScreens.Chemistry.Notes

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
import com.shani.nita.viewModel.BranchViewModel
import kotlinx.coroutines.launch
import com.shani.nita.UserPanel_ItemsView.EePdfItemView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChemistryNotesPagerScreen(navController: NavController, branch: String) {
    val pagerPage = remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val branchViewModel: BranchViewModel = viewModel()
    val loading by branchViewModel.loading.observeAsState(false)

    val notesMap by branchViewModel.notesMap.observeAsState(mutableMapOf())
    val semesterList by branchViewModel.semesterList.observeAsState(emptyList())

    // Fetch semester list and initial notes when branch changes
    LaunchedEffect(branch) {
        if (branch.isNotBlank()) {
            branchViewModel.getSemester(branch)
        }
    }

    // Fetch notes when the semester list is updated
    LaunchedEffect(semesterList) {
        if (semesterList.isNotEmpty()) {
            val initialSemester = semesterList[0] // Default to the first semester
            branchViewModel.getNotes(branch, initialSemester)
        }
    }

    val pagerState = rememberPagerState(
        initialPage = pagerPage.value,
        initialPageOffsetFraction = 0f,
        pageCount = { semesterList.size }
    )

    // Update the pager page value when the current page changes
    LaunchedEffect(pagerState.currentPage) {
        pagerPage.value = pagerState.currentPage
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        if (semesterList.isNotEmpty()) {
            ScrollableTabRow(
                contentColor = colorResource(id = R.color.white),
                edgePadding = 5.dp,
                selectedTabIndex = pagerState.currentPage,
                containerColor = colorResource(id = R.color.scaffoldColor),
                tabs = {
                    semesterList.forEachIndexed { index, tabName ->
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
            beyondBoundsPageCount = semesterList.count(),
            userScrollEnabled = true
        ) { tabIndex ->
            val semester = semesterList.getOrNull(tabIndex) ?: return@HorizontalPager
            val notes = notesMap[semester] ?: emptyList()

            // Fetch notes when the page changes
            LaunchedEffect(tabIndex) {
                branchViewModel.getNotes(branch, semester)
            }

            if (loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn {
                    items(notes) { branchModel ->
                        EePdfItemView(branchModel = branchModel, navController)
                    }
                }
            }
        }
    }
}







