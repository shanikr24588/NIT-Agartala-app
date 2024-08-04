package com.nita.nit_a

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.nita.nit_a.R
import com.nita.nit_a.Widgets.AnimatedLoading


import com.nita.nit_a.navigation.NavGraph
import com.nita.nit_a.ui.theme.NITATheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_NITA)
        super.onCreate(savedInstanceState)
        setContent {
            NITATheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color =  MaterialTheme.colorScheme.background
                ) {
                         NavGraph()
                }
            }
        }
    }
}






//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//
//}