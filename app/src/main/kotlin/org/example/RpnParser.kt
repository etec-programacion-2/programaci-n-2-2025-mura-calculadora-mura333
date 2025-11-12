package org.example

/**
 * Clase responsable de orquestar la conversión de una expresión de cadena
 * a una lista de tokens válidos para la evaluación RPN.
 */
class RpnParser {
    // Estas clases deben existir para que RpnParser compile correctamente
    private val tokenizer = Tokenizer()
    private val tokenProcessor = TokenProcessor()

    fun parse(expresion: String): List<String> {
        return tokenProcessor.processTokens(tokenizer.tokenize(expresion))
    }
}