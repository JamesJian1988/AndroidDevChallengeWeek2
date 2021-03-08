package com.example.androiddevchallenge.ui

import android.os.SystemClock
import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.view.TimerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.math.ceil
import kotlin.math.max

@Composable
fun TimerPage(modifier: Modifier = Modifier) {
    var endTime by remember { mutableStateOf(SystemClock.uptimeMillis()) }
    var remainingSeconds by remember { mutableStateOf(0) }

    fun updateRemainingTime() {
        remainingSeconds =
            ceil(max(endTime - SystemClock.uptimeMillis(), 0L).toFloat() / 1000F).toInt()
    }

    fun addTime(millis: Long) {
        endTime = SystemClock.uptimeMillis() + millis
        updateRemainingTime()
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main) {
            while (true) {
                updateRemainingTime()
                delay(100L)
            }
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Tick Tock",
            fontSize = 36.sp,
            fontWeight = FontWeight.Medium,
            color = MyTheme.colors.timeBackground
        )

        Spacer(modifier = Modifier.height(48.dp))

        TimerView(
            seconds = remainingSeconds,
            endMillis = endTime,
        )

        Spacer(modifier = Modifier.height(200.dp))

        Row {
            Spacer(modifier = Modifier.width(48.dp))
            Box(
                modifier = Modifier
                    .background(color = MyTheme.colors.timeBackground)
                    .weight(1f)
                    .clickable { addTime(DateUtils.HOUR_IN_MILLIS) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "01:00:00",
                    color = MyTheme.colors.timeText,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(6.dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .background(color = MyTheme.colors.timeBackground)
                    .weight(1f)
                    .clickable { addTime(DateUtils.MINUTE_IN_MILLIS * 30) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "00:30:00",
                    color = MyTheme.colors.timeText,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(6.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .background(color = MyTheme.colors.timeBackground)
                    .weight(1f)
                    .clickable { addTime(DateUtils.MINUTE_IN_MILLIS * 5) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "00:05:00",
                    color = MyTheme.colors.timeText,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(6.dp)
                )
            }
            Spacer(modifier = Modifier.width(48.dp))

        }
    }
}