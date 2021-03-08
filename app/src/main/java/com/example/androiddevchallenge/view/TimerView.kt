package com.example.androiddevchallenge.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.animateFloatAsState
import com.example.androiddevchallenge.util.getTimeParts
import kotlin.math.ceil

@Composable
fun TimerView(
    seconds: Int,
    endMillis: Long
) {
    val animatedSeconds by animateFloatAsState(key = endMillis, targetValue = seconds.toFloat())

    val currentSeconds = ceil(animatedSeconds).toInt()
    val nextSeconds = currentSeconds - 1
    val factor = currentSeconds.toFloat() - animatedSeconds
    val currentParts = getTimeParts(currentSeconds)
    val nextParts = getTimeParts(nextSeconds)

    Row {
        TimerItem(
            title = stringResource(R.string.hours),
            currentValue = currentParts.hours,
            nextValue = nextParts.hours,
            factor = if (currentParts.hours == nextParts.hours) 0F else factor,
        )

        Spacer(modifier = Modifier.width(16.dp))

        TimerItem(
            title = stringResource(R.string.minutes),
            currentValue = currentParts.minutes,
            nextValue = nextParts.minutes,
            factor = if (currentParts.minutes == nextParts.minutes) 0F else factor,
        )

        Spacer(modifier = Modifier.width(16.dp))

        TimerItem(
            title = stringResource(R.string.seconds),
            currentValue = currentParts.seconds,
            nextValue = nextParts.seconds,
            factor = if (currentParts.seconds == nextParts.seconds) 0F else factor,
        )
    }
}
