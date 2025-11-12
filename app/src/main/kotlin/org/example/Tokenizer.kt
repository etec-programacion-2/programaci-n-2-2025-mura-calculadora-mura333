package org.example

class Tokenizer {
    fun tokenize(expresion: String): List<String> {
        // Simplemente separa por espacios.
        return expresion.split(" ")
    }
}