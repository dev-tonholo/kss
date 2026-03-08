package dev.tonholo.kss.demo.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
    darkColorScheme(
        primary = Color(0xFF569CD6),
        secondary = Color(0xFF9CDCFE),
        tertiary = Color(0xFFC586C0),
        background = Color(0xFF1E1E1E),
        surface = Color(0xFF252526),
        surfaceVariant = Color(0xFF2D2D2D),
        onBackground = Color(0xFFD4D4D4),
        onSurface = Color(0xFFD4D4D4),
        onSurfaceVariant = Color(0xFF858585),
        outline = Color(0xFF3E3E42),
        error = Color(0xFFF44747)
    )

/**
 * Applies the KSS Demo dark theme, using a VS Code-inspired dark color scheme.
 *
 * @param content The composable content to wrap with the theme.
 */
@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
