package org.example

class TokenProcessor {
    fun processTokens(rawTokens: List<String>): List<String> {
        return rawTokens.filter { it.isNotBlank() }.map { it.trim() }
    }
}