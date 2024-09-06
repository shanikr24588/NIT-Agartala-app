package com.shani.nita.adminScreens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shani.nita.AdminPanel_itemview.NoticeItem
import com.shani.nita.viewModel.NoticeViewModel

@Composable

fun Noticelist(navController: NavController) {
    val context = LocalContext.current
    val noticeViewModel: NoticeViewModel = viewModel()
    val noticeList by noticeViewModel.noticeList.observeAsState(emptyList())
    val isDeleted by noticeViewModel.isDeleted.observeAsState(false)
    val loader by noticeViewModel.loading.observeAsState(false)


    LaunchedEffect(true) {
        noticeViewModel.getNotice()
    }

    LaunchedEffect(isDeleted) {
        if(isDeleted)
            Toast.makeText(context,"Notice deleted", Toast.LENGTH_SHORT).show()


    }

    if(loader){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 8.dp,
                top = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
        ) {
            items(noticeList) {
                NoticeItem(noticeModel = it, navController = navController,
                    delete = {
                            docId -> noticeViewModel.deleteNotice(docId)
                    })
            }

        }
    }




}

