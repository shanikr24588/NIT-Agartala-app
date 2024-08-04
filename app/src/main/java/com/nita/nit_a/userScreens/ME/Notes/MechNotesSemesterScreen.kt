package com.nita.nit_a.userScreens.ME.Notes

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nita.nit_a.ui.theme.Purple40
import com.nita.nit_a.viewModel.BranchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MechNotesSemesterScreen(navController: NavController ) {


    val branch = "ME"

    val branchViewModel: BranchViewModel = viewModel()

    val loading by branchViewModel.loading.observeAsState(false)

    val semesterList by branchViewModel.semesterList.observeAsState(emptyList())

    LaunchedEffect(branch) {
        if (branch.isNotBlank()) {
            branchViewModel.getSemester(branch)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = branch,
                fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp ) },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()
                    }){
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        },
    ){padding->
        if(loading){
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Red)
            }
        }
        else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(semesterList){
                    MechNotesSemesterItemView(navController,branch = branch, semester = it)
                }

            }
        }


    }


}