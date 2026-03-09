package dev.tonholo.kss.demo

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import dev.tonholo.kss.demo.theme.AppTheme
import kotlinx.browser.document

private const val COMPOSE_ROOT_ID = "compose-root"

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val body = document.body ?: return

    // Remove any existing compose root so HMR re-initialization
    // doesn't fail with "Shadow root cannot be created on a host
    // which already hosts a shadow tree."
    document.getElementById(COMPOSE_ROOT_ID)?.remove()

    val root = document.createElement("div").apply {
        id = COMPOSE_ROOT_ID
        setAttribute("style", "width: 100%; height: 100%;")
    }
    body.appendChild(root)

    ComposeViewport(root) {
        AppTheme {
            App()
        }
    }
}
