package dev.tonholo.kss.demo.theme

import androidx.compose.ui.graphics.Color
import dev.tonholo.kss.lexer.css.CssTokenKind

/**
 * VS Code-inspired dark color palette for CSS syntax highlighting.
 *
 * Maps [CssTokenKind] values to [Color]s used by the [SyntaxHighlighter][dev.tonholo.kss.demo.ui.SyntaxHighlighter].
 */
object SyntaxColors {
    val Ident = Color(0xFFD7BA7D)
    val Selector = Color(0xFF9CDCFE)
    val Number = Color(0xFFB5CEA8)
    val StringLiteral = Color(0xFFCE9178)
    val AtKeyword = Color(0xFFC586C0)
    val Function = Color(0xFFDCDCAA)
    val Comment = Color(0xFF6A9955)
    val Bang = Color(0xFFFF0000)
    val Structural = Color(0xFFD4D4D4)
    val Default = Color(0xFFD4D4D4)

    /**
     * Returns the highlight [Color] for the given [CssTokenKind].
     */
    fun colorFor(kind: CssTokenKind): Color =
        when (kind) {
            CssTokenKind.Ident -> Ident

            CssTokenKind.Hash,
            CssTokenKind.Dot,
            CssTokenKind.Asterisk,
                -> Selector

            CssTokenKind.Number,
            CssTokenKind.Dimension,
            CssTokenKind.Percentage,
            CssTokenKind.HexDigit,
                -> Number

            CssTokenKind.String,
            CssTokenKind.Quote,
            CssTokenKind.DoubleQuote,
                -> StringLiteral

            CssTokenKind.AtKeyword -> AtKeyword

            CssTokenKind.Function,
            CssTokenKind.Url,
                -> Function

            CssTokenKind.Comment -> Comment

            CssTokenKind.Bang -> Bang

            CssTokenKind.OpenCurlyBrace,
            CssTokenKind.CloseCurlyBrace,
            CssTokenKind.OpenSquareBracket,
            CssTokenKind.CloseSquareBracket,
            CssTokenKind.OpenParenthesis,
            CssTokenKind.CloseParenthesis,
            CssTokenKind.Colon,
            CssTokenKind.Semicolon,
            CssTokenKind.Comma,
            CssTokenKind.Greater,
            CssTokenKind.Tilde,
            CssTokenKind.Plus,
                -> Structural

            else -> Default
        }
}
