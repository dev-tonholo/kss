package dev.tonholo.kss.demo.model

import androidx.compose.runtime.Immutable
import dev.tonholo.kss.parser.ast.css.syntax.AstParserException

/**
 * Structured representation of a parse or tokenize error.
 *
 * Holds both the human-readable [message] and an optional [errorRange] pointing to the
 * offending character range in the CSS source. When the error originates from an
 * [AstParserException], the range is extracted directly from the exception's offset properties.
 *
 * @property message The error description.
 * @property errorRange The character offset range of the offending token, or `null` if unavailable.
 */
@Immutable
data class ParseErrorInfo(
    val message: String,
    val errorRange: IntRange?,
) {
    companion object {
        /**
         * Creates a [ParseErrorInfo] from the given [exception].
         *
         * If the exception is an [AstParserException] with valid offsets, the error range
         * is populated from [AstParserException.startOffset] and [AstParserException.endOffset].
         */
        fun from(exception: Exception): ParseErrorInfo {
            val range =
                if (exception is AstParserException && exception.startOffset >= 0) {
                    exception.startOffset..<exception.endOffset
                } else {
                    null
                }
            return ParseErrorInfo(
                message = exception.message ?: "Unknown error",
                errorRange = range
            )
        }
    }
}
