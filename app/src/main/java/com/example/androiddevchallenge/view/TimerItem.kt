package com.example.androiddevchallenge.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme


@Composable
fun TimerItem(
    currentValue: Int,
    nextValue: Int,
    factor: Float,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(text = title, fontSize = 20.sp,color = Color(0xff7ba9a9))

        Spacer(modifier = Modifier.height(8.dp))

        TimerItemAnimation(
            currentText = currentValue.toString().padStart(2, '0'),
            nextText = nextValue.toString().padStart(2, '0'),
            factor = factor
        )

        Spacer(modifier = Modifier.height(48.dp))
    }
}


private val FLAP_ELEVATION = 8.dp

@Composable
fun TimerItemAnimation(currentText: String, nextText: String, factor: Float) {
    Box(contentAlignment = Alignment.Center) {
        if (factor < 0.5F) {
            val f = factor * 2F
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .graphicsLayer(
                        rotationX = -0F * f,
                        transformOrigin = TransformOrigin(
                            pivotFractionX = 0.5F,
                            pivotFractionY = 0.5F
                        )
                    )
            ) {

                TimerItemView(
                    text = currentText,
                    elevation = FLAP_ELEVATION
                )
            }

        }

        if (factor >= 0.5F) {
            val f = (1F - factor) * 2F
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .graphicsLayer(
                        rotationX = -180F * f,
                        transformOrigin = TransformOrigin(
                            pivotFractionX = 0.5F,
                            pivotFractionY = 0.5F
                        )
                    )
            ) {

                TimerItemView(
                    text = nextText,
                    elevation = FLAP_ELEVATION
                )


            }
        }
    }
}

private val FLAP_WIDTH = 96.dp
private val FLAP_HEIGHT = 72.dp

@Composable
fun TimerItemView(
    text: String,
    elevation: Dp,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.requiredSize(width = FLAP_WIDTH, height = FLAP_HEIGHT),
        elevation = elevation
    ) {
        Box(
            modifier = Modifier.background(MyTheme.colors.timeBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = MyTheme.colors.timeText,
                fontFamily = FontFamily.SansSerif,
                fontSize = 64.sp,
                modifier = Modifier
                    .requiredWidth(IntrinsicSize.Min)
                    .requiredHeight(IntrinsicSize.Min)

                    .offset(y = (-2).dp)
            )
        }
    }
}