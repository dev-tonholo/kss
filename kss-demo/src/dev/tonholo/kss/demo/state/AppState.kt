package dev.tonholo.kss.demo.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.tonholo.kss.demo.model.AstDisplayNode
import dev.tonholo.kss.demo.model.AstFlattener
import dev.tonholo.kss.demo.model.ParseErrorInfo
import dev.tonholo.kss.lexer.Token
import dev.tonholo.kss.lexer.css.CssTokenKind
import dev.tonholo.kss.lexer.css.CssTokenizer
import dev.tonholo.kss.parser.ast.css.CssParser
import dev.tonholo.kss.parser.ast.css.consumer.CssConsumers
import dev.tonholo.kss.parser.ast.css.syntax.node.StyleSheet

/**
 * Central state holder for the KSS Demo application.
 *
 * Manages the CSS editor text, tokenization, parsing, AST flattening, and bidirectional
 * synchronization between the editor cursor and the AST tree viewer. All derived values
 * (tokens, AST, visible nodes, error info) recompute automatically via [derivedStateOf]
 * when the [cssText] changes.
 *
 * @param initialCss The initial CSS source to display in the editor.
 */
@Stable
class AppState(
    initialCss: String = DEFAULT_CSS,
) {
    var cssText by mutableStateOf(initialCss)
        private set

    var cursorOffset by mutableIntStateOf(0)
        private set

    var selectedCssRange: IntRange? by mutableStateOf(null)
        private set

    var collapsedNodeIds: Set<Int> by mutableStateOf(emptySet())
        private set

    private val tokenizeResult: Pair<List<Token<out CssTokenKind>>, ParseErrorInfo?> by derivedStateOf {
        try {
            CssTokenizer().tokenize(cssText) to null
        } catch (expected: IllegalStateException) {
            emptyList<Token<out CssTokenKind>>() to ParseErrorInfo.from(expected)
        }
    }

    val tokens: List<Token<out CssTokenKind>> by derivedStateOf { tokenizeResult.first }
    private val tokenizeError: ParseErrorInfo? by derivedStateOf { tokenizeResult.second }

    private val parseResult: Pair<StyleSheet?, ParseErrorInfo?> by derivedStateOf {
        if (tokens.isEmpty()) return@derivedStateOf null to tokenizeError
        try {
            val consumers = CssConsumers(cssText)
            val styleSheet = CssParser(consumers).parse(tokens)
            styleSheet to null
        } catch (expected: IllegalStateException) {
            null to ParseErrorInfo.from(expected)
        }
    }

    val styleSheet: StyleSheet? by derivedStateOf { parseResult.first }
    val errorInfo: ParseErrorInfo? by derivedStateOf { parseResult.second }
    val parseError: String? by derivedStateOf { errorInfo?.message }

    val flattenedNodes: List<AstDisplayNode> by derivedStateOf {
        AstFlattener.flatten(styleSheet)
    }

    val visibleNodes: List<AstDisplayNode> by derivedStateOf {
        computeVisibleNodes(flattenedNodes, collapsedNodeIds)
    }

    val highlightedNodeIndex: Int? by derivedStateOf {
        findDeepestNodeAtOffset(visibleNodes, cursorOffset)
    }

    fun onCssTextChange(text: String) {
        cssText = text
    }

    fun onCursorOffsetChange(offset: Int) {
        cursorOffset = offset
    }

    fun onAstNodeClicked(node: AstDisplayNode) {
        selectedCssRange = node.cssRange
    }

    fun toggleCollapse(nodeId: Int) {
        collapsedNodeIds =
            if (nodeId in collapsedNodeIds) {
                collapsedNodeIds - nodeId
            } else {
                collapsedNodeIds + nodeId
            }
    }

    fun clearSelection() {
        selectedCssRange = null
    }
}

private fun computeVisibleNodes(
    flattenedNodes: List<AstDisplayNode>,
    collapsed: Set<Int>,
): List<AstDisplayNode> {
    val result = mutableListOf<AstDisplayNode>()
    var skipUntilDepth = Int.MAX_VALUE
    for (node in flattenedNodes) {
        if (node.depth > skipUntilDepth) continue
        skipUntilDepth = Int.MAX_VALUE
        result += node
        if (node.id in collapsed && node.hasChildren) {
            skipUntilDepth = node.depth
        }
    }
    return result
}

private fun findDeepestNodeAtOffset(
    nodes: List<AstDisplayNode>,
    offset: Int,
): Int? {
    var bestIndex: Int? = null
    var bestSize = Int.MAX_VALUE
    nodes.forEachIndexed { index, node ->
        if (offset in node.cssRange) {
            val size = node.cssRange.last - node.cssRange.first
            if (size < bestSize) {
                bestSize = size
                bestIndex = index
            }
        }
    }
    return bestIndex
}

private val DEFAULT_CSS =
    """
/* KSS Demo - CSS AST Explorer */

:root {
    --primary: #569cd6;
    --bg: #1e1e1e;
}

body {
    margin: 0;
    padding: 16px;
    font-family: sans-serif;
    background-color: var(--bg);
    color: #d4d4d4;
}

.container {
    max-width: 960px;
    margin: 0 auto;
}

h1, h2, h3 {
    color: var(--primary);
    font-weight: 600;
}

a:hover {
    text-decoration: underline;
    color: #9cdcfe;
}

.input-text {
    border: 1px solid #3e3e42;
    padding: 8px 12px;
    border-radius: 4px;
}

@media (max-width: 768px) {
    .container {
        padding: 0 16px;
    }
}
    """.trimIndent()
