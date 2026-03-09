package dev.tonholo.kss.lexer.css.token.consumer

import dev.tonholo.kss.extensions.EMPTY
import dev.tonholo.kss.lexer.Token
import dev.tonholo.kss.lexer.TokenIterator
import dev.tonholo.kss.lexer.css.CssTokenKind

class HashTokenConsumer(
    iterator: TokenIterator<CssTokenKind>,
) : TokenConsumer(iterator) {
    override val supportedTokenKinds: Set<CssTokenKind> =
        setOf(
            CssTokenKind.Hash
        )

    override fun consume(kind: CssTokenKind): List<Token<out CssTokenKind>> {
        val next = iterator.peek(1)
        return if (next != Char.EMPTY && next.isHexDigit() && isInValueContext()) {
            listOf(
                Token(CssTokenKind.Hash, iterator.offset, iterator.nextOffset()),
                handleHexDigit(start = iterator.offset)
            )
        } else {
            listOf(Token(CssTokenKind.Hash, iterator.offset, iterator.nextOffset()))
        }
    }

    /**
     * Checks whether the `#` appears in a CSS value context (i.e., after a `:`
     * somewhere earlier on the same line/declaration). This handles cases like
     * `color: #f0f` as well as `border: 1px solid #3e3e42` where there are
     * multiple tokens between the colon and the hash.
     */
    private fun isInValueContext(): Boolean {
        var lookback = -1
        while (true) {
            val prev = iterator.peek(lookback)
            if (prev == Char.EMPTY) return false
            if (prev in CssTokenKind.Colon) return true
            // Stop searching at block boundaries — we've left the declaration
            if (prev in CssTokenKind.OpenCurlyBrace ||
                prev in CssTokenKind.CloseCurlyBrace ||
                prev in CssTokenKind.Semicolon
            ) {
                return false
            }
            lookback--
        }
    }

    private fun handleHexDigit(start: Int): Token<CssTokenKind> {
        while (iterator.hasNext()) {
            val char = iterator.get()
            when (char.lowercaseChar()) {
                in '0'..'9',
                in 'a'..'f',
                    -> {
                        iterator.nextOffset()
                    }

                else -> {
                    break
                }
            }
        }

        return Token(CssTokenKind.HexDigit, start, iterator.offset)
    }

    private fun Char.isHexDigit(): Boolean = this in '0'..'9' || this in 'a'..'f' || this in 'A'..'F'
}
