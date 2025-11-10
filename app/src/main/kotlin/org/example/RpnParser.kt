package org.example

class RpnParser {
    private val tokenizer = Tokenizer()
    private val tokenProcessor = TokenProcessor()

    fun parse(expresion: String): List<String> {
        return tokenProcessor.processTokens(tokenizer.tokenize(expresion))
    }
}