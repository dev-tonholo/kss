package dev.tonholo.kss.demo

import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.tonholo.kss.demo.theme.AppTheme

fun main() =
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KSS \u2014 CSS AST Explorer"
        ) {
            AppTheme {
                CompositionLocalProvider(LocalRippleConfiguration provides RippleConfiguration()) {
                    App()
                }
            }
        }
    }
