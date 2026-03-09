package dev.tonholo.kss.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.tonholo.kss.demo.state.AppViewModel
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
    val viewModel = viewModel { AppViewModel() }
    val state by viewModel.uiState.collectAsState()

    Row(modifier = modifier.fillMaxSize()) {
        CssEditorPanel(
            state = state,
            onCssTextChange = viewModel::onCssTextChange,
            onCursorOffsetChange = viewModel::onCursorOffsetChange,
            onClearSelection = viewModel::clearSelection,
            modifier = Modifier.weight(PANEL_WEIGHT),
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
            onToggleCollapse = viewModel::toggleCollapse,
            onNodeClick = viewModel::onAstNodeClicked,
            modifier = Modifier.weight(PANEL_WEIGHT),
        )
    }
}
