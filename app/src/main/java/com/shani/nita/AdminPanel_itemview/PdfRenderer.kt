package com.shani.nita.AdminPanel_itemview

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shani.nita.ui.theme.Purple40

import com.rajat.pdfviewer.PdfRendererView
import com.rajat.pdfviewer.compose.PdfRendererViewCompose

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdfRenderer(url: String, navController:NavController) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var isLoading by remember{ mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Pdf",modifier = Modifier.padding(start = 90.dp),
                fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp ) },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}){
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,modifier = Modifier
                                .padding(start = 10.dp)
                                .size(35.dp)
                        )
                    }
                }
            )
        },
    )   {padding->
        Column(modifier = Modifier.padding(padding)) {
            PdfRendererViewCompose(
                modifier = Modifier,
                url = url,
                lifecycleOwner = lifecycleOwner,
                statusCallBack = object : PdfRendererView.StatusCallBack {
                    override fun onPdfLoadStart() {
                        Log.i("statusCallBack", "onPdfLoadStart")
                        isLoading = false
                    }


                    override fun onPdfLoadProgress(
                        progress: Int,
                        downloadedBytes: Long,
                        totalBytes: Long?
                    ) {
                         isLoading = true
                    }

                    override fun onPdfLoadSuccess(absolutePath: String) {
                        Log.i("statusCallBack", "onPdfLoadSuccess")
                        isLoading = false
                    }

                    override fun onError(error: Throwable) {
                        Log.i("statusCallBack", "onError")
                        isLoading = false
                    }

                    override fun onPageChanged(currentPage: Int, totalPage: Int) {
                        //Page change. Not require
                    }
                }

            )
            if(isLoading)
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.8f)),
                    contentAlignment = Alignment.Center) {

                    CircularProgressIndicator(modifier = Modifier.size(48.dp)
                        ,color = Color.Red,
                        strokeWidth = 4.dp)

                }
        }



        }


}
