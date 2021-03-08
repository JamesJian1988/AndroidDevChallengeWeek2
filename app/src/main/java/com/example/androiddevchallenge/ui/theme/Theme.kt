/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
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
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
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
