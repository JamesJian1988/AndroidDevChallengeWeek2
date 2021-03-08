package com.example.androiddevchallenge.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Stable
class MyColors(
    mainBackground: Color,
    timeBackground: Color,
    timeText: Color,
) {
    var mainBackground: Color by mutableStateOf(mainBackground)
        private set
    var timeBackground: Color by mutableStateOf(timeBackground)
        private set
    var timeText: Color by mutableStateOf(timeText)
        private set
}

private val LocalMyColors = compositionLocalOf {
    LightColorPalette
}

@Stable
object MyTheme {
    val colors: MyColors
        @Composable
        get() = LocalMyColors.current
}

private val DarkColorPalette = MyColors(
    mainBackground = main_dark,
    timeBackground = BACKGROUND_COLOR_DARK,
    timeText = TEXT_COLOR_DARK
)

private val LightColorPalette = MyColors(
    mainBackground = main_dark,
    timeBackground = BACKGROUND_COLOR_DARK,
    timeText = TEXT_COLOR_DARK

)

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val targetColors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val mainBackground = animateColorAsState(targetColors.mainBackground, TweenSpec(600))
    val timeBackground = animateColorAsState(targetColors.timeBackground, TweenSpec(600))
    val timeText = animateColorAsState(targetColors.timeText, TweenSpec(600))

    val colors = MyColors(
        mainBackground = mainBackground.value,
        timeBackground = timeBackground.value,
        timeText = timeText.value,
    )

    CompositionLocalProvider(LocalMyColors provides colors) {
        MaterialTheme(
            shapes = shapes
        ) {
            ProvideWindowInsets(content = content)
        }
    }
}
