package com.shani.nita.Widgets

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.shani.nita.R

@Composable

fun AnimatedLoading(
    modifier: Modifier = Modifier,
    circleSize:Dp = 20.dp,
    circleColor:Color =   colorResource(id = R.color.violet),
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp
   ){
    val circle = listOf(
        remember{ androidx.compose.animation.core.Animatable(0f) },
        remember{ androidx.compose.animation.core.Animatable(0f) },
        remember {
            androidx.compose.animation.core.Animatable(0f)
        }
    )

    circle.forEachIndexed{index, animatable ->
        LaunchedEffect(animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )

        }

    }
    
    val circleValue = circle.map { it.value }
    val distance = with(LocalDensity.current){travelDistance.toPx()}
    val lastCircle = circleValue.size - 1
    
    Row(modifier = Modifier) {
        circleValue.forEachIndexed {index, value->
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .graphicsLayer {
                        translationY = -value * distance
                    }
                    .background(
                        color = circleColor,
                        shape = CircleShape
                    )
            )
            
            if (index != lastCircle){
                Spacer(modifier = Modifier.width(spaceBetween))
            }
            
            
        }
        
    }
                
     
}