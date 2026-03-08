package dev.tonholo.kss.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.tonholo.kss.demo.state.AppState
import dev.tonholo.kss.demo.ui.AstTreePanel
import dev.tonholo.kss.demo.ui.CssEditorPanel

private const val PANEL_WEIGHT = 0.5f

/**
 * Root composable for the KSS Demo application.
 *
 * Renders a horizontal split-pane layout with a [CssEditorPanel] on the left
 * and an [AstTreePanel] on the right, separated by a thin vertical divider.
 */
@Composable
fun App(modifier: Modifier = Modifier) {
    val state = remember { AppState() }

    Row(modifier = modifier.fillMaxSize()) {
        CssEditorPanel(
            state = state,
            modifier = Modifier.weight(PANEL_WEIGHT)
        )

        Box(
            modifier =
                Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.outline)
        )

        AstTreePanel(
            state = state,
            modifier = Modifier.weight(PANEL_WEIGHT)
        )
    }
}
